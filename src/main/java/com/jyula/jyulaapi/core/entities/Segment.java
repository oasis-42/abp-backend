package com.jyula.jyulaapi.core.entities;

import com.jyula.jyulaapi.core.entities.security.User;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "segments")
public class Segment extends BaseEntity {
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "segments_contacts",
            joinColumns = @JoinColumn(name = "segment_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id"))
    @ToString.Exclude
    private Set<Contact> contacts = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public void addContact(Contact contact) {
       contacts.add(contact);
    }
}