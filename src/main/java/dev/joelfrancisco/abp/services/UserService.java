package dev.joelfrancisco.abp.services;


import dev.joelfrancisco.abp.entities.User;
import dev.joelfrancisco.abp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void salvar(User user) {
        // validação AQUI
        repository.save(user);
    }

    public Iterable<User> buscarTodos(User user) {
        // validação AQUI
        return repository.findAll();
    }

    public Optional<User> buscarPorId(UUID id) {
        // validação AQUI
        return repository.findById(id);
    }

    public void remover(User user) {
        // validação AQUI
        repository.delete(user);
    }


}
