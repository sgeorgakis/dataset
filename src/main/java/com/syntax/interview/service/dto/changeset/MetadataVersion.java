package com.syntax.interview.service.dto.changeset;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetadataVersion {

    private Integer id;

    private String key;

    private String value;

    private Boolean isSensitive;

    private Set<String> tags;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getIsSensitive() {
        return isSensitive;
    }

    public void setIsSensitive(Boolean sensitive) {
        isSensitive = sensitive;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MetadataVersion that = (MetadataVersion) o;
        return Objects.equals(key, that.key)
                && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, key, value, isSensitive, tags);
    }

    @Override
    public String toString() {
        return "MetadataVersion{"
                + "id=" + id
                + ", key='" + key + '\''
                + ", value='" + value + '\''
                + ", isSensitive=" + isSensitive
                + ", tags=" + tags
                + '}';
    }
}
