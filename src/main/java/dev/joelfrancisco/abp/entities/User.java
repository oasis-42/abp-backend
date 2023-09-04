package dev.joelfrancisco.abp.entities;

import dev.joelfrancisco.abp.exceptions.UserCreationException;
import dev.joelfrancisco.abp.valueObjects.Email;
import dev.joelfrancisco.abp.valueObjects.UserPassword;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User extends BaseEntity {
    private String name;
    private String username;
    private UserPassword passwordHash;
    private Email defaultEmail;
    private Set<EmailCredentials> emailCredentials = new HashSet<>();

    private static User newUser(String username, UserPassword passwordHash, String name, Set<EmailCredentials> credentials) throws UserCreationException {
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(passwordHash);
        user.setName(name);

        if (credentials.isEmpty()) {
            throw new UserCreationException();
        }

        user.setEmailCredentials(credentials);
        return user;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "name should not be null");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = Objects.requireNonNull(username, "username should not be null");
    }

    public UserPassword getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(UserPassword passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Email getDefaultEmail() {
        return defaultEmail;
    }

    public void setDefaultEmail(Email defaultEmail) {
        this.defaultEmail = defaultEmail;
    }

    public Set<EmailCredentials> getEmailCredentials() {
        return Set.copyOf(emailCredentials);
    }

    public void setEmailCredentials(Set<EmailCredentials> emailCredentials) {
        this.emailCredentials = emailCredentials;
    }
}
