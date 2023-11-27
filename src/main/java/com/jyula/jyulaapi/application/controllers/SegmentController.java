package com.jyula.jyulaapi.application.controllers;

import com.jyula.jyulaapi.application.security.jwt.JwtUtils;
import com.jyula.jyulaapi.core.enterprise.BussinessException;
import com.jyula.jyulaapi.core.entities.Segment;
import com.jyula.jyulaapi.core.representations.ContactRepresentation;
import com.jyula.jyulaapi.core.representations.SegmentRepresentation;
import com.jyula.jyulaapi.core.services.SegmentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/segments")
public class SegmentController extends AbstractController {
    private final ModelMapper mapper;
    private final SegmentService service;

    private final JwtUtils jwtUtils;

    public SegmentController(ModelMapper mapper, SegmentService service, JwtUtils jwtUtils) {
        this.mapper = mapper;
        this.service = service;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping
    public ResponseEntity<SegmentRepresentation.SegmentResponse> create(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody @Valid SegmentRepresentation.CreateOrUpdateSegment representation) {

        String token = authHeader.substring(7);
        String username = jwtUtils.getUserNameFromJwtToken(token);

        representation.setUsername(username);

        Segment segment = service.save(representation);

        Set<ContactRepresentation.ContactResponse> contacts = segment.getContacts().stream()
                .map((contact -> ContactRepresentation.ContactResponse.from(mapper, contact)))
                .collect(Collectors.toSet());

        SegmentRepresentation.SegmentResponse response = SegmentRepresentation.SegmentResponse.builder()
                .segmentId(segment.getId())
                .contacts(contacts)
                .name(segment.getName())
                .build();

        return ResponseEntity.created(URI.create("/api/v1/segments/" + segment.getId())).body(response);
    }

    @GetMapping
    public ResponseEntity<PageImpl<SegmentRepresentation.SegmentResponse>> findAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Segment> segments = service.findAll(filter, PageRequest.of(page, size));

        List<SegmentRepresentation.SegmentResponse> responses = segments.stream()
                .map((segment -> SegmentRepresentation.SegmentResponse.from(mapper, segment)))
                .toList();

        PageImpl<SegmentRepresentation.SegmentResponse> responsesPage = new PageImpl<>(
                responses, segments.getPageable(), segments.getTotalElements());

        return ResponseEntity.ok(responsesPage);
    }

    @GetMapping("{id}")
    public ResponseEntity<SegmentRepresentation.SegmentResponse> findById(@PathVariable("id") Long id) {
        Segment segment = service.findById(id)
                .orElseThrow(() -> new BussinessException("Segments with id = " + id + " not found"));

        Set<ContactRepresentation.ContactResponse> contacts = segment.getContacts().stream()
                .map((contact -> ContactRepresentation.ContactResponse.from(mapper, contact)))
                .collect(Collectors.toSet());

        SegmentRepresentation.SegmentResponse response = SegmentRepresentation.SegmentResponse.builder()
                .segmentId(segment.getId())
                .contacts(contacts)
                .name(segment.getName())
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> remove(@PathVariable("id") Long id) {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<SegmentRepresentation.SegmentResponse> update(@PathVariable("id") Long id,
                                                                        @RequestBody SegmentRepresentation.CreateOrUpdateSegment representation) {

        Segment updatedSegment = service.update(id, representation);
        SegmentRepresentation.SegmentResponse response = SegmentRepresentation.SegmentResponse.from(mapper, updatedSegment);
        return ResponseEntity.ok(response);
    }

    @PostMapping("{id}")
    public ResponseEntity<SegmentRepresentation.SegmentResponse> addContacts(
            @PathVariable("id") Long id,
            @RequestBody @Valid SegmentRepresentation.AddContactsToSegment representation) {

        Segment segment = service.addContacts(id, representation.getContacts());
        SegmentRepresentation.SegmentResponse response = SegmentRepresentation.SegmentResponse.from(mapper, segment);
        return ResponseEntity.ok(response);
    }
}
