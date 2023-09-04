package dev.joelfrancisco.abp.entities;

import java.util.Objects;

public class EmailCredentials extends BaseEntity {
    private String clientId;
    private String clientSecret;

    public EmailCredentials(String clientId, String clientSecret) {
        super();
        setClientId(clientId);
        setClientSecret(clientSecret);
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
