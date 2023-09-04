package dev.joelfrancisco.abp.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Tag extends BaseEntity {
    private String name;
    private User user;
    private final Set<Recipient> recipients = new HashSet<>();
    private final Set<Template> templates = new HashSet<>();

    public Tag(String name, User user) {
        super();
        setName(name);
        setUser(user);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "name should not be null");
    }

    public void setUser(User user) {
        this.user = Objects.requireNonNull(user, "user should not be null");
    }

    public User getUser() {
        return user;
    }

    public Set<Recipient> getRecipients() {
        return Set.copyOf(recipients);
    }

    public Set<Template> getTemplates() {
        return Set.copyOf(templates);
    }

    public void addTemplate(Template template) {
        this.templates.add(template);
    }

    public void addRecipient(Recipient recipient) {
        this.recipients.add(recipient);
    }
}
