package com.jyula.jyulaapi.core.representations;

import com.jyula.jyulaapi.core.entities.Segment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

public interface SegmentRepresentation {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class CreateOrUpdateSegment {
        @NotEmpty(message = "name cannot be empty")
        @NotNull(message = "name cannot be null")
        private String name;

        private String username;
        private Set<String> contacts;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class AddContactsToSegment {
        private Set<String> contacts;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class SegmentResponse {
        private Long segmentId;
        private String name;
        private Set<ContactRepresentation.ContactResponse> contacts;

        public static SegmentResponse from(ModelMapper mapper, Segment segment) {
            Set<ContactRepresentation.ContactResponse> contacts = segment.getContacts().stream()
                    .map((contact -> ContactRepresentation.ContactResponse.from(mapper, contact)))
                    .collect(Collectors.toSet());

            return SegmentResponse.builder()
                    .segmentId(segment.getId())
                    .name(segment.getName())
                    .contacts(contacts)
                    .build();
        }
    }
}
