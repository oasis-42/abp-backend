package com.jyula.jyulaapi.core.repositories;

import com.jyula.jyulaapi.core.enterprise.CustomQuerydslPredicateExecutor;
import com.jyula.jyulaapi.core.entities.TemplateContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateContentRepository extends JpaRepository<TemplateContent, Long>, CustomQuerydslPredicateExecutor<TemplateContent> {
    @Modifying
    @Query("update TemplateContent t set t.isLatest = false where t.isLatest = true and t.template.id = ?1")
    void unsetOldLatest(Long templateId);

    @Query("select t from TemplateContent t where t.template.id = ?1 and t.isLatest = true")
    TemplateContent findLatest(Long templateId);

    @Query("select t from TemplateContent t where t.template.id = ?1 and t.version = ?2")
    TemplateContent findByVersion(Long templateId, Long version);

    Long countByTemplateId(Long templateId);
}
