package dev.joelfrancisco.abp.services;

import dev.joelfrancisco.abp.entities.Recipient;
import dev.joelfrancisco.abp.repositories.RecipientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RecipientService {
    private RecipientRepository repository;

    public RecipientService(RecipientRepository repository) {
        this.repository = repository;
    }

    public void salvar(Recipient recipient){
        repository.save(recipient);
    }

    public Iterable<Recipient> buscarTodos(Recipient recipient) {
        return repository.findAll();
    }

    public Optional<Recipient> buscarPorId(UUID id) {
        return repository.findById(id);
    }

    public void remover(Recipient recipient) {
        repository.delete(recipient);
    }
}
