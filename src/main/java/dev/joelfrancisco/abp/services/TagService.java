package dev.joelfrancisco.abp.services;

import dev.joelfrancisco.abp.entities.Recipient;
import dev.joelfrancisco.abp.entities.Tag;
import dev.joelfrancisco.abp.exceptions.EntityNotFoundException;
import dev.joelfrancisco.abp.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TagService {
    private final TagRepository repository;

    public TagService(TagRepository repository) {
        this.repository = repository;
    }

    public void save(Tag tag) {
        // validação AQUI
        repository.save(tag);
    }

    public Iterable<Tag> findAll() {
        // validação AQUI
        return repository.findAll();
    }

    public Optional<Tag> findById(UUID id) {
        // validação AQUI
        return repository.findById(id);
    }

    public void update(Tag tag) throws EntityNotFoundException {
        if (repository.existsById(tag.getId())) {
            repository.save(tag);
        } else {
            throw new EntityNotFoundException(tag);
        }
    }

    public void removeById(UUID id) {
        // validação AQUI
        repository.deleteById(id);
    }
}
