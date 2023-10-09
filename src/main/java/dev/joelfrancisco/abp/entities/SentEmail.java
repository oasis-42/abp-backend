package dev.joelfrancisco.abp.entities;

import dev.joelfrancisco.abp.valueObjects.Email;
import dev.joelfrancisco.abp.valueObjects.EmailStatus;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class SentEmail extends BaseEntity {
    @Column(name = "sender_email")
    @Convert(converter = Email.EmailConverter.class)
    private Email senderEmail;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EmailStatus status;
    @ManyToOne
    @JoinColumn(name = "template_id")
    private Template template;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Recipient recipient;

    public Email getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(Email senderEmail) {
        this.senderEmail = senderEmail;
    }

    public EmailStatus getStatus() {
        return status;
    }

    public void setStatus(EmailStatus status) {
        this.status = status;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = Objects.requireNonNull(template, "template should not be null");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = Objects.requireNonNull(user, "user should not be null");
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = Objects.requireNonNull(recipient, "recipient should not be null");
    }

    public SentEmail(Email senderEmail, Template template, User user, Recipient recipient) {
        setSenderEmail(senderEmail);
        setTemplate(template);
        setUser(user);
        setRecipient(recipient);
        setStatus(EmailStatus.PROCESSING);
    }

    public SentEmail() {
    }

    public void read() {
        this.status = EmailStatus.READ;
    }

    public void sent() {
        this.status = EmailStatus.SENT;
    }

    public void fail() {
        this.status = EmailStatus.FAILED;
    }
}
