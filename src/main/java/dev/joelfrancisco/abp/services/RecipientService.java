package dev.joelfrancisco.abp.services;

import dev.joelfrancisco.abp.entities.Recipient;
import dev.joelfrancisco.abp.exceptions.EntityNotFoundException;
import dev.joelfrancisco.abp.repositories.RecipientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RecipientService {
    private final RecipientRepository repository;

    public RecipientService(RecipientRepository repository) {
        this.repository = repository;
    }

    public void save(Recipient recipient){
        repository.save(recipient);
    }

    public Iterable<Recipient> findAll(Recipient recipient) {
        return repository.findAll();
    }

    public Optional<Recipient> findById(UUID id) {
        return repository.findById(id);
    }

    public void update(Recipient recipient) throws EntityNotFoundException {
        if (repository.existsById(recipient.getId())) {
           repository.save(recipient);
        } else {
            throw new EntityNotFoundException(recipient);
        }
    }

    public void removeById(UUID id) {
        repository.deleteById(id);
    }
}
