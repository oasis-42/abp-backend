package com.jyula.jyulaapi.application.controllers;

import com.jyula.jyulaapi.application.security.jwt.JwtUtils;
import com.jyula.jyulaapi.core.enterprise.BussinessException;
import com.jyula.jyulaapi.core.entities.Template;
import com.jyula.jyulaapi.core.entities.TemplateContent;
import com.jyula.jyulaapi.core.representations.TemplateRepresentation;
import com.jyula.jyulaapi.core.services.TemplateService;
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
@RequestMapping("/api/v1/templates")
public class TemplateController extends AbstractController {
    private final ModelMapper mapper;
    private final TemplateService service;
    private final JwtUtils jwtUtils;

    public TemplateController(ModelMapper mapper, TemplateService service, JwtUtils jwtUtils) {
        this.mapper = mapper;
        this.service = service;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping
    public ResponseEntity<TemplateRepresentation.TemplateResponse> create(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody @Valid TemplateRepresentation.CreateOrUpdateTemplate representation) {

        String token = authHeader.substring(7);
        String username = jwtUtils.getUserNameFromJwtToken(token);

        representation.setUsername(username);

        Template template = service.save(representation);
        TemplateRepresentation.TemplateResponse response = TemplateRepresentation.TemplateResponse.from(mapper, template);
        return ResponseEntity.created(URI.create("/api/v1/templates/" + template.getId())).body(response);
    }

    @GetMapping
    public ResponseEntity<PageImpl<TemplateRepresentation.TemplateResponse>> findAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Template> templates = service.findAll(filter, PageRequest.of(page, size));

        List<TemplateRepresentation.TemplateResponse> responses = templates.stream()
                .map((template) -> TemplateRepresentation.TemplateResponse.from(mapper, template)).toList();

        PageImpl<TemplateRepresentation.TemplateResponse> responsesPage = new PageImpl<>(
                responses, templates.getPageable(), templates.getTotalElements());

        return ResponseEntity.ok(responsesPage);
    }

    @GetMapping("{id}")
    public ResponseEntity<TemplateRepresentation.TemplateContentResponse> findById(@PathVariable("id") Long id) {
        Template template = service.findById(id)
                .orElseThrow(() -> new BussinessException("Template with id = " + id + " not found"));

        TemplateContent latestContent = service.getLatestContent(id);

        TemplateRepresentation.TemplateContentResponse response = TemplateRepresentation.TemplateContentResponse.builder()
                .templateId(template.getId())
                .name(template.getName())
                .isFavorite(template.isFavorite())
                .about(latestContent.getAbout())
                .content(latestContent.getContent())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("{version}/{id}")
    public ResponseEntity<TemplateRepresentation.TemplateContentResponse> findVersionById(
            @PathVariable("version") Long version, @PathVariable("id") Long id) {

        TemplateContent contentByVersion = service.getContentByVersion(id, version);

        TemplateRepresentation.TemplateContentResponse response = TemplateRepresentation.TemplateContentResponse.builder()
                .templateId(contentByVersion.getTemplate().getId())
                .name(contentByVersion.getTemplate().getName())
                .isFavorite(contentByVersion.getTemplate().isFavorite())
                .about(contentByVersion.getAbout())
                .content(contentByVersion.getContent())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}/number-of-versions")
    public ResponseEntity<Long> findNumberOfVersionsById(
            @PathVariable("id") Long id) {

        Long numberOfVersions = service.getNumberOfVersions(id);
        return ResponseEntity.ok(numberOfVersions);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> remove(@PathVariable("id") Long id) {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<TemplateRepresentation.TemplateResponse> update(@PathVariable("id") Long id,
                                                                        @RequestBody TemplateRepresentation.CreateOrUpdateTemplate representation) {

        Template updatedTemplate = service.update(id, representation);
        TemplateRepresentation.TemplateResponse response = TemplateRepresentation.TemplateResponse.from(mapper, updatedTemplate);
        return ResponseEntity.ok(response);
    }
}
