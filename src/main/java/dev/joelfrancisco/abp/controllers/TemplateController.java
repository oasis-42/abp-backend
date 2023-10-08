package dev.joelfrancisco.abp.controllers;

import dev.joelfrancisco.abp.services.TemplateService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateController {
    private TemplateService service;

    public TemplateController(TemplateService service) {
        this.service = service;
    }
}
