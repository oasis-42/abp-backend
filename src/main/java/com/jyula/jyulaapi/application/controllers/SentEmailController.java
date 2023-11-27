package com.jyula.jyulaapi.application.controllers;

import com.jyula.jyulaapi.core.enterprise.BussinessException;
import com.jyula.jyulaapi.core.entities.Campaign;
import com.jyula.jyulaapi.core.entities.SentEmail;
import com.jyula.jyulaapi.core.repositories.SentEmailRepository;
import com.jyula.jyulaapi.core.representations.CampaignRepresentation;
import com.jyula.jyulaapi.core.representations.SentEmailRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sent-emails")
public class SentEmailController extends AbstractController {
    private final ModelMapper mapper;
    private final SentEmailRepository sentEmailRepository;

    public SentEmailController(ModelMapper mapper, SentEmailRepository sentEmailRepository) {
        this.mapper = mapper;
        this.sentEmailRepository = sentEmailRepository;
    }

    @GetMapping
    public ResponseEntity<PageImpl<SentEmailRepresentation.SentEmailResponse>> findAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<SentEmail> sentEmails = sentEmailRepository.findAll(filter, SentEmail.class, PageRequest.of(page, size));

        List<SentEmailRepresentation.SentEmailResponse> responses = sentEmails.stream()
                .map((sentEmail -> SentEmailRepresentation.SentEmailResponse.from(mapper, sentEmail))).toList();

        PageImpl<SentEmailRepresentation.SentEmailResponse> responsesPage = new PageImpl<>(
                responses, sentEmails.getPageable(), sentEmails.getTotalElements());

        return ResponseEntity.ok(responsesPage);
    }

    @GetMapping("{id}")
    public ResponseEntity<SentEmailRepresentation.SentEmailResponse> findById(@PathVariable("id") Long id) {
        SentEmail sentEmail = sentEmailRepository.findById(id)
                .orElseThrow(() -> new BussinessException("SentEmail with id = " + id + " not found"));

        SentEmailRepresentation.SentEmailResponse response = SentEmailRepresentation.SentEmailResponse.from(mapper, sentEmail);
        return ResponseEntity.ok(response);
    }
}
