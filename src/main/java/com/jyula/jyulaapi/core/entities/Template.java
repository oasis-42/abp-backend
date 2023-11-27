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
@Table(name = "templates")
public class Template extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "is_favorite")
    private boolean isFavorite;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<TemplateContent> contents = new HashSet<>();
}
