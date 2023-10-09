package dev.joelfrancisco.abp.repositories;

import dev.joelfrancisco.abp.entities.EmailCredentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmailCredentialsRepository extends CrudRepository<EmailCredentials, UUID> {
}
