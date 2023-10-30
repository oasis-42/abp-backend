package dev.joelfrancisco.abp.services;

import dev.joelfrancisco.abp.exceptions.NotFoundException;
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

    public Template save(Template template) {
        repository.save(template);
        return template;
    }

    public Iterable<Template> findAll(String filter) {
        return repository.findAll(filter, Template.class);
    }

    public Optional<Template> findById(UUID id) {
        return repository.findById(id);
    }

    public void update(Template template) throws NotFoundException {
        if (repository.existsById(template.getId())) {
            repository.save(template);
        } else {
            throw new NotFoundException(template);
        }
    }

    public void favorite(UUID id) {
        repository.deleteById(id);
    }

    public void removeById(UUID id) {
        repository.deleteById(id);
    }
}
