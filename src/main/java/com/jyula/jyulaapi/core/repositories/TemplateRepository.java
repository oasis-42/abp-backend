package com.jyula.jyulaapi.core.repositories;

import com.jyula.jyulaapi.core.enterprise.CustomQuerydslPredicateExecutor;
import com.jyula.jyulaapi.core.entities.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long>, CustomQuerydslPredicateExecutor<Template> {
}
