package dev.joelfrancisco.abp.repositories;

import dev.joelfrancisco.abp.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RecipientRepository extends CrudRepository<User, UUID> {
}
