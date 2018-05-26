package com.syntax.interview.service.dto.report;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.syntax.interview.service.dto.changeset.MetadataVersion;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiffMetadata {

    private Integer id;

    private String type;

    private MetadataVersion version;

    private MetadataVersion previousVersion;

    private Status status;

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

    public MetadataVersion getVersion() {
        return version;
    }

    public void setVersion(MetadataVersion version) {
        this.version = version;
    }

    public MetadataVersion getPreviousVersion() {
        return previousVersion;
    }

    public void setPreviousVersion(MetadataVersion previousVersion) {
        this.previousVersion = previousVersion;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiffMetadata that = (DiffMetadata) o;
        return Objects.equals(id, that.id)
                && Objects.equals(type, that.type)
                && Objects.equals(version, that.version)
                && Objects.equals(previousVersion, that.previousVersion)
                && status == that.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, version, previousVersion, status);
    }

    @Override
    public String toString() {
        return "DiffMetadata{"
                + "id=" + id
                + ", type='" + type + '\''
                + ", version=" + version
                + ", previousVersion=" + previousVersion
                + ", status=" + status
                + '}';
    }
}
