package dev.joelfrancisco.abp.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Group extends BaseEntity {
    @OneToMany
    @Column(name = "group_id")
    private UUID groupId;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<Recipient> recipients = new HashSet<>();
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<Report> reports = new HashSet<>();
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<SentEmail> sentEmails = new HashSet<>();

    public Group(String name) {
        super();
        setName(name);
    }

    protected Group() {
    }

    public UUID getGroupId() {
        return groupId;
    }

    public void setGroupId(UUID groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(Set<Recipient> recipients) {
        this.recipients = recipients;
    }
}
