package dev.joelfrancisco.abp.services;

import dev.joelfrancisco.abp.exceptions.EntityNotFoundException;
import dev.joelfrancisco.abp.repositories.TemplateRepository;
import dev.joelfrancisco.abp.entities.Template;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TemplateService {
    private final TemplateRepository repository;

    public TemplateService(TemplateRepository repository) {
        this.repository = repository;
    }

    public void save(Template template) {
        repository.save(template);
    }

    public Iterable<Template> findAll() {
        return repository.findAll();
    }

    public Optional<Template> findById(UUID id) {
        return repository.findById(id);
    }

    public void update(Template template) throws EntityNotFoundException {
        if (repository.existsById(template.getId())) {
            repository.save(template);
        } else {
            throw new EntityNotFoundException(template);
        }
    }

    public void removeById(UUID id) {
        repository.deleteById(id);
    }
}
