package com.syntax.interview.service.dto.report;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.syntax.interview.service.dto.changeset.Version;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Diff {

    private Integer id;

    private String type;

    private Version version;

    private Version previousVersion;

    private Status status;

    private DiffReference reference;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public Version getPreviousVersion() {
        return previousVersion;
    }

    public void setPreviousVersion(Version previousVersion) {
        this.previousVersion = previousVersion;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public DiffReference getReference() {
        return reference;
    }

    public void setReference(DiffReference reference) {
        this.reference = reference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Diff diff = (Diff) o;
        return Objects.equals(id, diff.id)
                && Objects.equals(type, diff.type)
                && Objects.equals(version, diff.version)
                && Objects.equals(previousVersion, diff.previousVersion)
                && status == diff.status
                && Objects.equals(reference, diff.reference);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, version, previousVersion, status, reference);
    }

    @Override
    public String toString() {
        return "Diff{"
                + "id=" + id
                + ", type='" + type + '\''
                + ", version=" + version
                + ", previousVersion=" + previousVersion
                + ", status=" + status
                + ", reference=" + reference
                + '}';
    }
}
