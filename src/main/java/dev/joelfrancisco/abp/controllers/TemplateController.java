package dev.joelfrancisco.abp.controllers;

import dev.joelfrancisco.abp.entities.Template;
import dev.joelfrancisco.abp.services.TemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/templates")
public class TemplateController extends AbstractController {
    private final TemplateService service;

    public TemplateController(TemplateService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Template> create(@RequestBody Template entity) {
        Template template = service.save(entity);
        return ResponseEntity.created(URI.create("/api/templates/" + entity.getId())).body(template);
    }


    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false) String filter) {
        Iterable<Template> templates = service.findAll(filter);
        return ResponseEntity.ok(templates);
    }
    /*
    @PostMapping()
    public favorite() {

    }
     */
}
