package com.syntax.interview.service.dto.changeset;

import java.util.Objects;

public class Metadata {

    private Integer id;

    private String type;

    private MetadataVersion version;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Metadata metadata = (Metadata) o;
        return Objects.equals(version, metadata.version);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, version);
    }

    @Override
    public String toString() {
        return "Metadata{"
                + "id=" + id
                + ", type='" + type + '\''
                + ", version=" + version
                + '}';
    }
}
