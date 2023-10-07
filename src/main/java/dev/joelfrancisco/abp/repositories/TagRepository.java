package dev.joelfrancisco.abp.repositories;

import dev.joelfrancisco.abp.entities.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TagRepository extends CrudRepository<Tag, UUID> {
}
