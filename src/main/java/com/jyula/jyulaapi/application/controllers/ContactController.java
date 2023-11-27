package com.jyula.jyulaapi.application.controllers;

import com.jyula.jyulaapi.core.enterprise.BussinessException;
import com.jyula.jyulaapi.core.entities.Contact;
import com.jyula.jyulaapi.core.representations.ContactRepresentation;
import com.jyula.jyulaapi.core.services.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController extends AbstractController {
    private final ModelMapper mapper;
    private final ContactService service;

    public ContactController(ModelMapper mapper, ContactService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ContactRepresentation.ContactResponse> create(
            @RequestBody @Valid ContactRepresentation.CreateOrUpdateContact representation) {

        Contact contact = service.save(representation);
        ContactRepresentation.ContactResponse response = ContactRepresentation.ContactResponse.from(mapper, contact);
        return ResponseEntity.created(URI.create("/api/v1/contacts/" + contact.getId())).body(response);
    }

    @GetMapping
    public ResponseEntity<PageImpl<ContactRepresentation.ContactResponse>> findAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Contact> contacts = service.findAll(filter, PageRequest.of(page, size));

        List<ContactRepresentation.ContactResponse> responses = contacts.stream()
                .map((contact -> ContactRepresentation.ContactResponse.from(mapper, contact))).toList();

        PageImpl<ContactRepresentation.ContactResponse> responsesPage = new PageImpl<>(
                responses, contacts.getPageable(), contacts.getTotalElements());

        return ResponseEntity.ok(responsesPage);
    }

    @GetMapping("{id}")
    public ResponseEntity<ContactRepresentation.ContactResponse> findById(@PathVariable("id") Long id) {
        Contact contact = service.findById(id)
                .orElseThrow(() -> new BussinessException("Contact with id = " + id + " not found"));

        ContactRepresentation.ContactResponse response = ContactRepresentation.ContactResponse.from(mapper, contact);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> remove(@PathVariable("id") Long id) {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ContactRepresentation.ContactResponse> update(@PathVariable("id") Long id,
                                                                        @RequestBody ContactRepresentation.CreateOrUpdateContact representation) {

        Contact updatedContact = service.update(id, representation);
        ContactRepresentation.ContactResponse response = ContactRepresentation.ContactResponse.from(mapper, updatedContact);
        return ResponseEntity.ok(response);
    }
}
