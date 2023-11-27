package com.jyula.jyulaapi.core.representations;

import com.jyula.jyulaapi.core.entities.Campaign;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

public interface CampaignRepresentation {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class CreateCampaign {
        @NotEmpty(message = "name cannot be empty")
        @NotNull(message = "name cannot be null")
        private String name;

        @NotNull(message = "templateId cannot be null")
        private Long templateId;

        private SendTo sendTo;

        @Builder
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class SendTo {
            private Set<Long> contacts;
            private Set<Long> segments;
        }
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class CampaignResponse {
        private Long campaignId;
        private String name;
        private Set<ContactRepresentation.ContactResponse> contacts;
        private Set<SegmentRepresentation.SegmentResponse> segments;
        public static CampaignResponse from(ModelMapper mapper, Campaign campaign) {
            Set<ContactRepresentation.ContactResponse> contacts = campaign.getContacts().stream()
                    .map((contact -> ContactRepresentation.ContactResponse.from(mapper, contact)))
                    .collect(Collectors.toSet());

            Set<SegmentRepresentation.SegmentResponse> segments = campaign.getSegments().stream()
                    .map((segment -> SegmentRepresentation.SegmentResponse.from(mapper, segment)))
                    .collect(Collectors.toSet());

            return CampaignResponse.builder()
                    .campaignId(campaign.getId())
                    .name(campaign.getName())
                    .contacts(contacts)
                    .segments(segments)
                    .build();
        }
    }
}
