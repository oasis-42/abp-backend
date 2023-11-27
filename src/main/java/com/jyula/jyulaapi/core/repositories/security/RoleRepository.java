package com.jyula.jyulaapi.core.repositories.security;

import com.jyula.jyulaapi.core.entities.security.ERole;
import com.jyula.jyulaapi.core.entities.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}


