package com.jyula.jyulaapi.core.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "campaigns")
public class Campaign extends BaseEntity {
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "template_id", referencedColumnName = "id")
    private Template template;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "campaigns_contacts",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id"))
    @ToString.Exclude
    private Set<Contact> contacts;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "campaigns_segments",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "segment_id"))
    @ToString.Exclude
    private Set<Segment> segments;
}
