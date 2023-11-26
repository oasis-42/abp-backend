package dev.joelfrancisco.abp.controllers;

import dev.joelfrancisco.abp.entities.Group;
import dev.joelfrancisco.abp.entities.Report;
import dev.joelfrancisco.abp.entities.Template;
import dev.joelfrancisco.abp.services.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController extends AbstractController {
    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Report> create(@RequestBody Group group) {
        Report report = service.save(group);
        return ResponseEntity.ok().body(report);
    }
}
