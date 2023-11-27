package com.jyula.jyulaapi.core.services;

import com.jyula.jyulaapi.core.enterprise.BussinessException;
import com.jyula.jyulaapi.core.entities.*;
import com.jyula.jyulaapi.core.providers.MailSenderProvider;
import com.jyula.jyulaapi.core.repositories.*;
import com.jyula.jyulaapi.core.representations.CampaignRepresentation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CampaignService {
    private final MailSenderProvider mailSenderProvider;
    private final TemplateContentRepository templateContentRepository;
    private final ContactRepository contactRepository;
    private final SegmentRepository segmentRepository;
    private final CampaignRepository campaignRepository;
    private final SentEmailRepository sentEmailRepository;

    public CampaignService(MailSenderProvider mailSenderProvider,
                           TemplateContentRepository templateContentRepository,
                           ContactRepository contactRepository,
                           SegmentRepository segmentRepository,
                           CampaignRepository campaignRepository,
                           SentEmailRepository sentEmailRepository) {
        this.mailSenderProvider = mailSenderProvider;
        this.templateContentRepository = templateContentRepository;
        this.contactRepository = contactRepository;
        this.segmentRepository = segmentRepository;
        this.campaignRepository = campaignRepository;
        this.sentEmailRepository = sentEmailRepository;
    }

    public void send(CampaignRepresentation.CreateCampaign representation) {
        TemplateContent template = templateContentRepository.findLatest(representation.getTemplateId());
        Set<Contact> contacts = new HashSet<>();

        Campaign campaign = new Campaign();
        campaign.setName(representation.getName());
        campaign.setTemplate(template.getTemplate());

        if (representation.getSendTo().getContacts() != null) {
            Set<Contact> onlyContacts = representation.getSendTo().getContacts().stream()
                    .map(contactId -> contactRepository.findById(contactId).orElseThrow(
                            () -> new BussinessException("Contact with id = " + contactId + " not found")))
                    .collect(Collectors.toSet());

            contacts.addAll(onlyContacts);
            campaign.setContacts(onlyContacts);
        }

        if (representation.getSendTo().getSegments() != null) {
            Set<Segment> segments = representation.getSendTo().getSegments().stream()
                    .map(segmentId -> segmentRepository.findById(segmentId).orElseThrow(
                            () -> new BussinessException("Segment with id = " + segmentId + " not found")))
                    .collect(Collectors.toSet());

            segments.forEach(segment -> {
                contacts.addAll(segment.getContacts());
            });

            campaign.setSegments(segments);
        }

        contacts.forEach(contact -> {
            MailSenderProvider.SendMailRequest sendMailRequest = MailSenderProvider.SendMailRequest.builder()
                    .from("onboarding@resend.dev")
                    .content(template.getContent())
                    .to(contact.getEmail())
                    .subject(template.getAbout())
                    .build();

            SentEmail sentEmail = SentEmail.builder()
                    .status(SentEmail.EmailStatus.PROCESSING)
                    .campaign(campaign)
                    .contact(contact)
                    .build();

            SentEmail savedEmail = sentEmailRepository.save(sentEmail);

            try {
                mailSenderProvider.send(sendMailRequest);
                savedEmail.setStatus(SentEmail.EmailStatus.SENT);
                sentEmailRepository.save(sentEmail);
            } catch (MailSenderProvider.SendMailException ex) {
                savedEmail.setStatus(SentEmail.EmailStatus.FAILED);
                sentEmailRepository.save(sentEmail);
            }
        });

        campaignRepository.save(campaign);
    }

    public Optional<Campaign> findById(Long id) {
        return campaignRepository.findById(id);
    }

    public List<Campaign> findAll(String filter) {
        return campaignRepository.findAll(filter, Campaign.class);
    }

    public Page<Campaign> findAll(String filter, Pageable pageable) {
        return campaignRepository.findAll(filter, Campaign.class, pageable);
    }
}
