package com.jyula.jyulaapi.core.entities;

import com.jyula.jyulaapi.core.entities.security.User;
import lombok.*;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "sent_emails")
public class SentEmail extends BaseEntity {
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EmailStatus status;

    @ManyToOne
    @JoinColumn(name = "campaign_id", referencedColumnName = "id")
    private Campaign campaign;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Contact contact;

    public enum EmailStatus {
        SENT,
        FAILED,
        PROCESSING,
    }
}
