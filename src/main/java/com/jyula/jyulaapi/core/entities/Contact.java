package com.jyula.jyulaapi.core.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "contacts", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email")
})
public class Contact extends BaseEntity {
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;

    //    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(	name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles = new HashSet<>();
}
