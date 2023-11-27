package com.jyula.jyulaapi.core.services;

import com.jyula.jyulaapi.core.enterprise.BussinessException;
import com.jyula.jyulaapi.core.entities.Contact;
import com.jyula.jyulaapi.core.entities.security.User;
import com.jyula.jyulaapi.core.repositories.ContactRepository;
import com.jyula.jyulaapi.core.representations.ContactRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContactService {
    private final ModelMapper mapper;
    private final ContactRepository repository;

    public ContactService(ModelMapper mapper, ContactRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public Contact save(ContactRepresentation.CreateOrUpdateContact representation) {
        Contact contact = mapper.map(representation, Contact.class);
        return repository.save(contact);
    }

    public List<Contact> findAll(String filter) {
        return repository.findAll(filter, Contact.class);
    }

    public Page<Contact> findAll(String filter, Pageable pageable) {
        return repository.findAll(filter, Contact.class, pageable);
    }

    public Optional<Contact> findById(Long id) {
        return repository.findById(id);
    }

    public Contact update(Long id, ContactRepresentation.CreateOrUpdateContact representation) {
        Contact foundEntity = repository.findById(id)
                .orElseThrow(() -> new BussinessException(
                        "Contact with id = " + id + " not found"));

        mapper.map(representation, foundEntity);
        foundEntity.setUpdatedAt(LocalDateTime.now());

        return repository.save(foundEntity);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }
}
