package com.jyula.jyulaapi.core.entities;

import com.jyula.jyulaapi.core.entities.security.User;
import lombok.*;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "template_contents")
public class TemplateContent extends BaseEntity {
    @Column(name = "version")
    private Integer version;

    @Column(name = "about")
    private String about;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "is_latest")
    private boolean isLatest;

    @ManyToOne
    @JoinColumn(name = "template_id", referencedColumnName = "id")
    private Template template;
}
