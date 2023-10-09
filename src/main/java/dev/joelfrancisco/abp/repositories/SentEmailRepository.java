package dev.joelfrancisco.abp.repositories;

import dev.joelfrancisco.abp.entities.SentEmail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SentEmailRepository extends CrudRepository<SentEmail, UUID> {
}
