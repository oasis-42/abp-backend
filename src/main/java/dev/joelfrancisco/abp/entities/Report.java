package dev.joelfrancisco.abp.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Report extends BaseEntity {
    @Column(name = "id_report")
    private UUID reportId;
    @ManyToOne
    @JoinColumn(name = "grupo_id")
    @Nullable
    private Group grupo;

    public Report(UUID reportId, @Nullable Group grupo) {
        this.reportId = reportId;
        this.grupo = grupo;
    }

    protected Report() {
    }

    public UUID getReportId() {
        return reportId;
    }

    public void setReportId(UUID reportId) {
        this.reportId = reportId;
    }

    public Group getGrupo() {
        return grupo;
    }

    public void setGrupo(Group grupo) {
        this.grupo = grupo;
    }
}
