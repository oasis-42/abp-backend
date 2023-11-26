package dev.joelfrancisco.abp.repositories;

import dev.joelfrancisco.abp.enterprise.CustomQuerydslPredicateExecutor;
import dev.joelfrancisco.abp.entities.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReportRepository extends CrudRepository<Report, UUID>, CustomQuerydslPredicateExecutor<Report> {
}
