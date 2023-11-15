package dev.joelfrancisco.abp.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Report extends BaseEntity {
    @Column(name = "id_report")
    private UUID reportId;
    @ManyToOne
    @JoinColumn(name = "group_id")
    @Nullable
    private Group group;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Report(@Nullable Group group) {
        super();
        setReportId(UUID.randomUUID());
        setGroup(group);
        setUser(group.getUser());
    }

    public UUID getReportId() {
        return reportId;
    }

    public void setReportId(UUID reportId) {
        this.reportId = reportId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
