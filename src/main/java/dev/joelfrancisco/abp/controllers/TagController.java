package dev.joelfrancisco.abp.controllers;

import dev.joelfrancisco.abp.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {
    private TagService service;

    public TagController(TagService service) {
        this.service = service;
    }
}
