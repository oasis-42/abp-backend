package dev.joelfrancisco.abp.controllers;

import dev.joelfrancisco.abp.services.RecipientService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipientController {
    private RecipientService service;

    public RecipientController(RecipientService service) {
        this.service = service;
    }
}
