package dev.joelfrancisco.abp.services;

import dev.joelfrancisco.abp.entities.Tag;
import dev.joelfrancisco.abp.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TagService {
    private TagRepository repository;

    public TagService(TagRepository repository) {
        this.repository = repository;
    }

    public void salvar(Tag tag) {
        // validação AQUI
        repository.save(tag);
    }

    public Iterable<Tag> buscarTodos(Tag tag) {
        // validação AQUI
        return repository.findAll();
    }

    public Optional<Tag> buscarPorId(UUID id) {
        // validação AQUI
        return repository.findById(id);
    }

    public void remover(Tag tag) {
        // validação AQUI
        repository.delete(tag);
    }
}
