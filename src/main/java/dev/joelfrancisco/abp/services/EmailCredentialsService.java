package dev.joelfrancisco.abp.services;


import dev.joelfrancisco.abp.entities.EmailCredentials;
import dev.joelfrancisco.abp.exceptions.NotFoundException;
import dev.joelfrancisco.abp.repositories.EmailCredentialsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EmailCredentialsService {
    private final EmailCredentialsRepository repository;

    public EmailCredentialsService(EmailCredentialsRepository repository) {
        this.repository = repository;
    }

    public void save(EmailCredentials emailCredentials) {
        // validação AQUI
        repository.save(emailCredentials);
    }

    public Iterable<EmailCredentials> findAll() {
        // validação AQUI
        return repository.findAll();
    }

    public Optional<EmailCredentials> findById(UUID id) {
        // validação AQUI
        return repository.findById(id);
    }

    public void update(EmailCredentials emailCredentials) throws NotFoundException {
        if (repository.existsById(emailCredentials.getId())) {
            repository.save(emailCredentials);
        } else {
            throw new NotFoundException(emailCredentials);
        }
    }

    public void removeById(UUID id) {
        // validação AQUI
        repository.deleteById(id);
    }
}
