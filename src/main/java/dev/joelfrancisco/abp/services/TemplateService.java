package dev.joelfrancisco.abp.services;

import dev.joelfrancisco.abp.repositories.TemplateRepository;
import dev.joelfrancisco.abp.entities.Template;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TemplateService {
    private TemplateRepository repository;

    public TemplateService(TemplateRepository repository) {
        this.repository = repository;
    }

    public void salvar(Template template) {
        repository.save(template);
    }

    public Iterable<Template> buscarTodos(Template template) {
        return repository.findAll();
    }

    public Optional<Template> buscarPorId(UUID id) {
        return repository.findById(id);
    }

    public void remover(Template template) {
        repository.delete(template);
    }
}
