package dev.joelfrancisco.abp.controllers;

import dev.joelfrancisco.abp.services.CampaignService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CampaignController {
    private CampaignService service;
    public CampaignController(CampaignService service) {
        this.service = service;
    }
}
