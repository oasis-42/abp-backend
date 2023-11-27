package com.jyula.jyulaapi.core.representations;

import com.jyula.jyulaapi.core.entities.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public interface ContactRepresentation {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class CreateOrUpdateContact {
        @NotEmpty(message = "name cannot be empty")
        @NotNull(message = "name cannot be null")
        private String name;

        @NotEmpty(message = "email cannot be empty")
        @NotNull(message = "email cannot be null")
        @Email
        private String email;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class ContactResponse {
        private Long contactId;
        private String name;
        private String email;

        public static ContactResponse from(ModelMapper mapper, Contact contact) {
            return mapper.map(contact, ContactResponse.class);
        }
    }
}
