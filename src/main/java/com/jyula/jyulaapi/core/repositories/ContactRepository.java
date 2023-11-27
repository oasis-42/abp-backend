package com.jyula.jyulaapi.core.repositories;

import com.jyula.jyulaapi.core.enterprise.CustomQuerydslPredicateExecutor;
import com.jyula.jyulaapi.core.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>, CustomQuerydslPredicateExecutor<Contact> {
    Optional<Contact> findByEmail(String email);
}
