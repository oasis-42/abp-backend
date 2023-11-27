package com.jyula.jyulaapi.core.services;

import com.jyula.jyulaapi.core.enterprise.BussinessException;
import com.jyula.jyulaapi.core.entities.Contact;
import com.jyula.jyulaapi.core.entities.Segment;
import com.jyula.jyulaapi.core.entities.security.User;
import com.jyula.jyulaapi.core.repositories.ContactRepository;
import com.jyula.jyulaapi.core.repositories.SegmentRepository;
import com.jyula.jyulaapi.core.repositories.security.UserRepository;
import com.jyula.jyulaapi.core.representations.SegmentRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SegmentService {
    private final ModelMapper mapper;
    private final SegmentRepository segmentRepository;
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    public SegmentService(ModelMapper mapper, SegmentRepository segmentRepository,
                          ContactRepository contactRepository, UserRepository userRepository) {
        this.mapper = mapper;
        this.segmentRepository = segmentRepository;
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    public Segment save(SegmentRepresentation.CreateOrUpdateSegment representation) {
        User user = userRepository.findByUsername(representation.getUsername())
                .orElseThrow(() -> new BussinessException(
                        "User with username = " + representation.getUsername() + " not found"));

        Set<Contact> contacts = representation.getContacts().stream()
                .map((email) -> contactRepository.findByEmail(email)
                        .orElseThrow(() -> new BussinessException("Contacts with email = " + email + " not found")))
                .collect(Collectors.toSet());

        Segment segment = Segment.builder()
                .user(user)
                .name(representation.getName())
                .contacts(contacts)
                .build();

        return segmentRepository.save(segment);
    }

    public List<Segment> findAll(String filter) {
        return segmentRepository.findAll(filter, Segment.class);
    }

    public Page<Segment> findAll(String filter, Pageable pageable) {
        return segmentRepository.findAll(filter, Segment.class, pageable);
    }

    public Optional<Segment> findById(Long id) {
        return segmentRepository.findById(id);
    }

    public void remove(Long id) {
        segmentRepository.deleteById(id);
    }

    public Segment update(Long id, SegmentRepresentation.CreateOrUpdateSegment representation) {
        Segment foundEntity = segmentRepository.findById(id)
                .orElseThrow(() -> new BussinessException("Segment with id = " + id + " not found"));

        Set<Contact> contacts = representation.getContacts().stream()
                .map((email) -> contactRepository.findByEmail(email)
                        .orElseThrow(() -> new BussinessException("Contacts with email = " + email + " not found")))
                .collect(Collectors.toSet());

        foundEntity.setName(representation.getName());
        foundEntity.setContacts(contacts);
        foundEntity.setUpdatedAt(LocalDateTime.now());

        return segmentRepository.save(foundEntity);
    }

    public Segment addContacts(Long id, Set<String> emails) {
        Segment foundEntity = segmentRepository.findById(id)
                .orElseThrow(() -> new BussinessException("Segment with id = " + id + " not found"));

        Set<Contact> contacts = emails.stream()
                .map((email) -> contactRepository.findByEmail(email)
                        .orElseThrow(() -> new BussinessException("Contacts with email = " + email + " not found")))
                .collect(Collectors.toSet());

        contacts.forEach((foundEntity::addContact));
        foundEntity.setUpdatedAt(LocalDateTime.now());

        return segmentRepository.save(foundEntity);
    }
}
