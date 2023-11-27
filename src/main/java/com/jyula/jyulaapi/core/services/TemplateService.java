package com.jyula.jyulaapi.core.services;

import com.jyula.jyulaapi.core.enterprise.BussinessException;
import com.jyula.jyulaapi.core.entities.Template;
import com.jyula.jyulaapi.core.entities.TemplateContent;
import com.jyula.jyulaapi.core.entities.security.User;
import com.jyula.jyulaapi.core.repositories.TemplateContentRepository;
import com.jyula.jyulaapi.core.repositories.TemplateRepository;
import com.jyula.jyulaapi.core.repositories.security.UserRepository;
import com.jyula.jyulaapi.core.representations.TemplateRepresentation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TemplateService {
    private final TemplateRepository templateRepository;
    private final TemplateContentRepository templateContentRepository;
    private final UserRepository userRepository;

    public TemplateService(TemplateRepository templateRepository,
                           TemplateContentRepository templateContentRepository, UserRepository userRepository) {
        this.templateRepository = templateRepository;
        this.templateContentRepository = templateContentRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Template save(TemplateRepresentation.CreateOrUpdateTemplate representation) {
        User user = userRepository.findByUsername(representation.getUsername())
                .orElseThrow(() -> new BussinessException(
                        "User with username = " + representation.getUsername() + " not found"));

        Template template = Template.builder()
                .name(representation.getName())
                .isFavorite(representation.isFavorite())
                .user(user)
                .build();

        Template savedTemplate = templateRepository.save(template);

        TemplateContent templateContent = TemplateContent.builder()
                .version(1)
                .about(representation.getAbout())
                .content(representation.getContent())
                .template(template)
                .isLatest(true)
                .build();

        templateContentRepository.save(templateContent);

        return savedTemplate;
    }

    public List<Template> findAll(String filter) {
        return templateRepository.findAll(filter, Template.class);
    }

    public Page<Template> findAll(String filter, Pageable pageable) {
        return templateRepository.findAll(filter, Template.class, pageable);
    }

    public Optional<Template> findById(Long id) {
        return templateRepository.findById(id);
    }

    public void remove(Long id) {
        templateRepository.deleteById(id);
    }

    @Transactional
    public Template update(Long id, TemplateRepresentation.CreateOrUpdateTemplate representation) {
        Template foundEntity = templateRepository.findById(id)
                .orElseThrow(() -> new BussinessException(
                        "Template with id = " + "id" + " not found"));

        foundEntity.setName(representation.getName());
        foundEntity.setUpdatedAt(LocalDateTime.now());

        TemplateContent latest = templateContentRepository.findLatest(foundEntity.getId());

        TemplateContent templateContent = TemplateContent.builder()
                .version(latest.getVersion() + 1)
                .about(representation.getAbout())
                .content(representation.getContent())
                .template(foundEntity)
                .isLatest(true)
                .build();

        templateContentRepository.unsetOldLatest(foundEntity.getId());
        templateContentRepository.save(templateContent);

        return templateRepository.save(foundEntity);
    }

    public TemplateContent getLatestContent(Long templateId) {
        return templateContentRepository.findLatest(templateId);
    }

    public TemplateContent getContentByVersion(Long templateId, Long version) {
        return templateContentRepository.findByVersion(templateId, version);
    }

    public Long getNumberOfVersions(Long templateId) {
        return templateContentRepository.countByTemplateId(templateId);
    }
}
