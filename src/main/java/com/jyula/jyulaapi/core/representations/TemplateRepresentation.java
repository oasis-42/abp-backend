package com.jyula.jyulaapi.core.representations;

import com.jyula.jyulaapi.core.entities.Template;
import com.jyula.jyulaapi.core.entities.TemplateContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public interface TemplateRepresentation {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class CreateOrUpdateTemplate {
        @NotEmpty(message = "name cannot be empty")
        @NotNull(message = "name cannot be null")
        private String name;

        @NotEmpty(message = "about cannot be empty")
        @NotNull(message = "about cannot be null")
        private String about;

        @NotEmpty(message = "content cannot be empty")
        @NotNull(message = "content cannot be null")
        private String content;

        private String username;
        private boolean isFavorite = false;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class TemplateResponse {
        private Long templateId;
        private String name;
        private boolean isFavorite;

        public static TemplateResponse from(ModelMapper mapper, Template template) {
            return mapper.map(template, TemplateResponse.class);
        }
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class TemplateContentResponse {
        private Long templateId;
        private String name;
        private String about;
        private String content;
        private boolean isFavorite;

        public static TemplateContentResponse from(ModelMapper mapper, TemplateContent templateContent) {
            return mapper.map(templateContent, TemplateContentResponse.class);
        }
    }
}
