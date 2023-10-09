package dev.joelfrancisco.abp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
public class EmailCredentials extends BaseEntity {
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "client_secret")
    private String clientSecret;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public EmailCredentials(String clientId, String clientSecret) {
        super();
        setClientId(clientId);
        setClientSecret(clientSecret);
    }

    protected EmailCredentials() {
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = Objects.requireNonNull(clientId, "ClientId should not be null");
    }

    public String getClientSecret() {
        return clientSecret;
    }

    private void setClientSecret(String clientSecret) {
        this.clientSecret = Objects.requireNonNull(clientSecret, "Client Secret should not be null");
    }
}
