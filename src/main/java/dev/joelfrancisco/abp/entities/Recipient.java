package dev.joelfrancisco.abp.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Recipient extends BaseEntity {
    private String email;
    private String name;
    private final Set<Tag> tags = new HashSet<>();

    public Recipient(String email, String name) {
        super();
        setEmail(email);
        setName(name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = Objects.requireNonNull(email, "email should not be null");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "name should not be null");
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public Set<Tag> getTags() {
        return Set.copyOf(tags);
    }
}
