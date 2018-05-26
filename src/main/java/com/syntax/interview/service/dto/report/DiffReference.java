package com.syntax.interview.service.dto.report;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.syntax.interview.service.dto.changeset.ReferenceVersion;

import java.util.Objects;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiffReference {

    private Integer id;

    private String type;

    private ReferenceVersion version;

    private ReferenceVersion previousVersion;

    private Status status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<DiffNode> nodes;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<DiffMetadata> metadata;

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

    public ReferenceVersion getVersion() {
        return version;
    }

    public void setVersion(ReferenceVersion version) {
        this.version = version;
    }

    public ReferenceVersion getPreviousVersion() {
        return previousVersion;
    }

    public void setPreviousVersion(ReferenceVersion previousVersion) {
        this.previousVersion = previousVersion;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<DiffNode> getNodes() {
        return nodes;
    }

    public void setNodes(Set<DiffNode> nodes) {
        this.nodes = nodes;
    }

    public Set<DiffMetadata> getMetadata() {
        return metadata;
    }

    public void setMetadata(Set<DiffMetadata> metadata) {
        this.metadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiffReference that = (DiffReference) o;
        return Objects.equals(id, that.id)
                && Objects.equals(type, that.type)
                && Objects.equals(version, that.version)
                && Objects.equals(previousVersion, that.previousVersion)
                && status == that.status
                && nodes.containsAll(that.nodes)
                && metadata.containsAll(that.metadata);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, version, previousVersion, status, nodes, metadata);
    }

    @Override
    public String toString() {
        return "DiffReference{"
                + "id=" + id
                + ", type='" + type + '\''
                + ", version=" + version
                + ", previousVersion=" + previousVersion
                + ", status=" + status
                + ", nodes=" + nodes
                + ", metadata=" + metadata
                + '}';
    }
}
