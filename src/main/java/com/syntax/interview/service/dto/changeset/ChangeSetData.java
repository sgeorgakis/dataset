package com.syntax.interview.service.dto.changeset;

import java.util.Objects;

public class ChangeSetData {

    private Integer id;

    private String type;

    private Version version;

    private Reference reference;

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

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
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
        ChangeSetData that = (ChangeSetData) o;
        return Objects.equals(id, that.id)
                && Objects.equals(type, that.type)
                && Objects.equals(version, that.version)
                && Objects.equals(reference, that.reference);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, version, reference);
    }
}
