package com.syntax.interview.service.dto.changeset;

import java.util.Objects;

public class ReferenceVersion {

    private String flavor;

    private String name;

    private String identityKey;

    private String identityValue;

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityKey() {
        return identityKey;
    }

    public void setIdentityKey(String identityKey) {
        this.identityKey = identityKey;
    }

    public String getIdentityValue() {
        return identityValue;
    }

    public void setIdentityValue(String identityValue) {
        this.identityValue = identityValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReferenceVersion version = (ReferenceVersion) o;
        return Objects.equals(flavor, version.flavor)
                && Objects.equals(name, version.name)
                && Objects.equals(identityKey, version.identityKey)
                && Objects.equals(identityValue, version.identityValue);
    }

    @Override
    public int hashCode() {

        return Objects.hash(flavor, name, identityKey, identityValue);
    }

    @Override
    public String toString() {
        return "ReferenceVersion{"
                + "flavor='" + flavor + '\''
                + ", name='" + name + '\''
                + ", identityKey='" + identityKey + '\''
                + ", identityValue='" + identityValue + '\''
                + '}';
    }
}
