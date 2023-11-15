package dev.joelfrancisco.abp.services;

import dev.joelfrancisco.abp.entities.Group;
import dev.joelfrancisco.abp.entities.Report;
import dev.joelfrancisco.abp.entities.Template;
import dev.joelfrancisco.abp.exceptions.NotFoundException;
import dev.joelfrancisco.abp.repositories.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ReportService {
    private final ReportRepository repository;

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }

    public Report save(Group group) {
        Report report = new Report(group);

        // get all emails sent on this week

        // generates a pdf report

        // send pdf report to user's inbox

        repository.save(report);
        return report;
    }
}
