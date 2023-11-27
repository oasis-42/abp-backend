package com.jyula.jyulaapi.application.controllers;

import com.jyula.jyulaapi.core.enterprise.BussinessException;
import com.jyula.jyulaapi.core.entities.Campaign;
import com.jyula.jyulaapi.core.entities.Contact;
import com.jyula.jyulaapi.core.representations.CampaignRepresentation;
import com.jyula.jyulaapi.core.representations.ContactRepresentation;
import com.jyula.jyulaapi.core.services.CampaignService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/campaigns")
public class CampaignController extends AbstractController {
    private final ModelMapper mapper;
    private final CampaignService service;

    public CampaignController(ModelMapper mapper, CampaignService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping("/send")
    public ResponseEntity<?> send(
            @RequestBody @Valid CampaignRepresentation.CreateCampaign representation) {

        service.send(representation);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<PageImpl<CampaignRepresentation.CampaignResponse>> findAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Campaign> campaigns = service.findAll(filter, PageRequest.of(page, size));

        List<CampaignRepresentation.CampaignResponse> responses = campaigns.stream()
                .map((campaign -> CampaignRepresentation.CampaignResponse.from(mapper, campaign))).toList();

        PageImpl<CampaignRepresentation.CampaignResponse> responsesPage = new PageImpl<>(
                responses, campaigns.getPageable(), campaigns.getTotalElements());

        return ResponseEntity.ok(responsesPage);
    }

    @GetMapping("{id}")
    public ResponseEntity<CampaignRepresentation.CampaignResponse> findById(@PathVariable("id") Long id) {
        Campaign campaign = service.findById(id)
                .orElseThrow(() -> new BussinessException("Campaign with id = " + id + " not found"));

        CampaignRepresentation.CampaignResponse response = CampaignRepresentation.CampaignResponse.from(mapper, campaign);
        return ResponseEntity.ok(response);
    }
}
