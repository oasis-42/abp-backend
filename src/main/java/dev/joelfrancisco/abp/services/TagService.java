package dev.joelfrancisco.abp.services;

import dev.joelfrancisco.abp.entities.Tag;
import dev.joelfrancisco.abp.exceptions.NotFoundException;
import dev.joelfrancisco.abp.repositories.TagRepository;
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

    public void update(Tag tag) throws NotFoundException {
        if (repository.existsById(tag.getId())) {
            repository.save(tag);
        } else {
            throw new NotFoundException(tag);
        }
    }

    public void removeById(UUID id) {
        // validação AQUI
        repository.deleteById(id);
    }
}
