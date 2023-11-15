package dev.joelfrancisco.abp.entities;

import dev.joelfrancisco.abp.exceptions.UserCreationException;
import dev.joelfrancisco.abp.valueObjects.UserPassword;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class User extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String username;
    @Column(name = "password_hash")
    @Convert(converter = UserPassword.UserPasswordConverter.class)
    private UserPassword passwordHash;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "default_email_id")
//    private EmailCredentials defaultEmail;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<EmailCredentials> emailCredentials = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<SentEmail> sentEmails = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Template> templates = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Group> groups = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Report> reports = new HashSet<>();

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

//    public EmailCredentials getDefaultEmail() {
//        return defaultEmail;
//    }
//
//    public void setDefaultEmail(EmailCredentials defaultEmail) {
//        this.defaultEmail = defaultEmail;
//    }

    public Set<EmailCredentials> getEmailCredentials() {
        return Set.copyOf(emailCredentials);
    }

    public void setEmailCredentials(Set<EmailCredentials> emailCredentials) {
        this.emailCredentials = emailCredentials;
    }
}
