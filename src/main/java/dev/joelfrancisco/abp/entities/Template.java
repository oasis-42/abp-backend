package dev.joelfrancisco.abp.entities;

import jakarta.persistence.*;

import java.net.URL;
import java.util.*;
@Entity
public class Template extends BaseEntity {
    @Column(name = "id_template")
    private UUID templateId;
    @Column(name = "version")
    private int version;
    @Column(name = "name")
    private String name;
    @Column(name = "about")
    private String about;
    @Column(name = "is_favorite")
    private boolean isFavorite;
    @Column(name = "Link_html")
    private URL linkHTML;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "templates_tags",
            joinColumns = @JoinColumn(name = "template_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private final Set<Tag> tags = new HashSet<>();

    public Template(String name, String about, URL linkHTML, User user) {
        setName(name);
        setAbout(about);
        setLinkHTML(linkHTML);
        setUser(user);
    }

    protected Template() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "name should not be null");
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = Objects.requireNonNull(about, "about should not be null");
    }

    public URL getLinkHTML() {
        return linkHTML;
    }

    public void setLinkHTML(URL linkHTML) {
        this.linkHTML = Objects.requireNonNull(linkHTML, "linkHTML should not be null");
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public Set<Tag> getTags() {
        return Set.copyOf(tags);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = Objects.requireNonNull(user, "user should not be null");
    }

    public UUID getTemplateId() {
        return templateId;
    }

    public void setTemplateId(UUID templateId) {
        this.templateId = templateId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void favorite() {
        this.isFavorite = true;
    }

    public void setFavorite() {
        this.isFavorite = true;
    }

    public void unsetFavorite() {
        this.isFavorite = false;
    }
}
