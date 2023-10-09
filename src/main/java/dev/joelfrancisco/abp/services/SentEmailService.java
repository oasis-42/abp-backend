package dev.joelfrancisco.abp.services;


import dev.joelfrancisco.abp.entities.SentEmail;
import dev.joelfrancisco.abp.entities.User;
import dev.joelfrancisco.abp.exceptions.EntityNotFoundException;
import dev.joelfrancisco.abp.repositories.SentEmailRepository;
import dev.joelfrancisco.abp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SentEmailService {
    private final SentEmailRepository repository;

    public SentEmailService(SentEmailRepository repository) {
        this.repository = repository;
    }

    public void save(SentEmail sentEmail) {
        // validação AQUI
        repository.save(sentEmail);
    }

    public Iterable<SentEmail> findAll() {
        // validação AQUI
        return repository.findAll();
    }

    public Optional<SentEmail> findById(UUID id) {
        // validação AQUI
        return repository.findById(id);
    }

    public void update(SentEmail sentEmail) throws EntityNotFoundException {
        if (repository.existsById(sentEmail.getId())) {
            repository.save(sentEmail);
        } else {
            throw new EntityNotFoundException(sentEmail);
        }
    }

    public void removeById(UUID id) {
        // validação AQUI
        repository.deleteById(id);
    }
}
