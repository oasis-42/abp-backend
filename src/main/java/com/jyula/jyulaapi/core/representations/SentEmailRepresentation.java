package com.jyula.jyulaapi.core.representations;

import com.jyula.jyulaapi.core.entities.SentEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

public interface SentEmailRepresentation {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class SentEmailResponse {
        private Long sentEmailId;
        private SentEmail.EmailStatus status;
        private Long campaignId;
        private ContactRepresentation.ContactResponse contact;

        public static SentEmailResponse from(ModelMapper mapper, SentEmail sentEmail) {
            ContactRepresentation.ContactResponse contact = ContactRepresentation.ContactResponse.from(
                    mapper, sentEmail.getContact());

            return SentEmailResponse.builder()
                    .sentEmailId(sentEmail.getId())
                    .status(sentEmail.getStatus())
                    .campaignId(sentEmail.getCampaign().getId())
                    .contact(contact)
                    .build();
        }
    }
}
