package com.syntax.interview.service.dto.changeset;

import java.util.Objects;
import java.util.Set;

public class Reference {

    private Integer id;

    private String type;

    private ReferenceVersion version;

    private Set<Node> nodes;

    private Set<Metadata> metadata;

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

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public Set<Metadata> getMetadata() {
        return metadata;
    }

    public void setMetadata(Set<Metadata> metadata) {
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
        Reference reference = (Reference) o;
        return Objects.equals(id, reference.id)
                && Objects.equals(type, reference.type)
                && Objects.equals(version, reference.version)
                && Objects.equals(nodes, reference.nodes)
                && Objects.equals(metadata, reference.metadata);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, version, nodes, metadata);
    }

    @Override
    public String toString() {
        return "Reference{"
                + "id=" + id
                + ", type='" + type + '\''
                + ", version=" + version
                + ", nodes=" + nodes
                + ", metadata=" + metadata
                + '}';
    }
}
