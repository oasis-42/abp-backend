package dev.joelfrancisco.abp.services;

import dev.joelfrancisco.abp.repositories.CampaignRepository;
import org.springframework.stereotype.Service;

@Service
public class CampaignService {
    private final CampaignRepository repository;

    public CampaignService(CampaignRepository repository) {
        this.repository = repository;
    }
}
