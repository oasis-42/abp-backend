package dev.joelfrancisco.abp.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Recipient extends BaseEntity {
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipients_tags",
            joinColumns = @JoinColumn(name = "recipient_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private final Set<Tag> tags = new HashSet<>();

    public Recipient(String email, String name) {
        super();
        setEmail(email);
        setName(name);
    }

    protected Recipient() {
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
