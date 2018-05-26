package com.syntax.interview.service.impl;

import com.syntax.interview.service.DiffReportService;
import com.syntax.interview.service.dto.changeset.ChangeSetData;
import com.syntax.interview.service.dto.changeset.Metadata;
import com.syntax.interview.service.dto.changeset.Node;
import com.syntax.interview.service.dto.changeset.Reference;
import com.syntax.interview.service.dto.changeset.ReferenceVersion;
import com.syntax.interview.service.dto.report.Diff;
import com.syntax.interview.service.dto.report.DiffMetadata;
import com.syntax.interview.service.dto.report.DiffNode;
import com.syntax.interview.service.dto.report.DiffReference;
import com.syntax.interview.service.dto.report.Status;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DiffReportServiceImpl implements DiffReportService {

    @Override
    public Diff calculateReport(ChangeSetData previous, ChangeSetData current) {
        Diff diff = new Diff();
        diff.setId(previous.getId());
        diff.setStatus(Status.UNMODIFIED);
        diff.setType(previous.getType());
        diff.setVersion(current.getVersion());
        diff.setPreviousVersion(previous.getVersion());
        diff.setReference(calculateDiffReference(previous.getReference(), current.getReference()));

        return diff;
    }

    private DiffReference calculateDiffReference(Reference previousRef, Reference currentRef) {
        DiffReference diffReference = new DiffReference();
        diffReference.setId(previousRef.getId());
        diffReference.setType(previousRef.getType());
        diffReference.setStatus(Status.UNMODIFIED);
        diffReference.setVersion(currentRef.getVersion());
        diffReference.setPreviousVersion(previousRef.getVersion());
        diffReference.setMetadata(calculateDiffMetaData(previousRef.getMetadata(), currentRef.getMetadata()));
        diffReference.setNodes(calculateDiffNodes(previousRef.getNodes(), currentRef.getNodes()));

        return diffReference;
    }

    private Set<DiffNode> calculateDiffNodes(Set<Node> previousNodes, Set<Node> currentNodes) {
        if (previousNodes == null) {
            previousNodes = new HashSet<>();
        }
        if (currentNodes == null) {
            currentNodes = new HashSet<>();
        }

        Map<Integer, Node> currentNodesMap = currentNodes.stream()
                .collect(Collectors.toMap(Node::getId, Function.identity()));

        Set<DiffNode> diffNodeSet = new HashSet<>();
        for (Node node: previousNodes) {
            DiffNode diffNode = new DiffNode();
            diffNode.setId(node.getId());
            diffNode.setType(node.getType());
            if (!currentNodesMap.containsKey(node.getId())) {
                diffNode.setPreviousVersion(node.getVersion());
                diffNode.setStatus(Status.DELETED);
                diffNode.setMetadata(deleteMetadata(node.getMetadata()));
                diffNode.setNodes(deleteNodes(node.getNodes()));
            } else {
                diffNode.setStatus(Status.UNMODIFIED);
                ReferenceVersion version = currentNodesMap.get(node.getId()).getVersion();
                diffNode.setVersion(version);
                Set<Metadata> currentMetada = currentNodesMap.get(node.getId()).getMetadata();
                diffNode.setMetadata(calculateDiffMetaData(node.getMetadata(), currentMetada));
                Set<Node> currentSubNodes = currentNodesMap.get(node.getId()).getNodes();
                diffNode.setNodes(calculateDiffNodes(node.getNodes(), currentSubNodes));
            }
            diffNodeSet.add(diffNode);
        }

        Set<Node> createdNodes = new HashSet<>(currentNodes);
        createdNodes.removeAll(previousNodes);
        diffNodeSet.addAll(createNodes(createdNodes));

        return diffNodeSet;
    }

    private Set<DiffMetadata> deleteMetadata(Set<Metadata> metadata) {
        Set<DiffMetadata> diffMetadataSet = new HashSet<>();
        if (metadata == null) {
            metadata = new HashSet<>();
        }
        metadata.forEach(m -> {
            DiffMetadata diffMetadata = new DiffMetadata();
            diffMetadata.setId(m.getId());
            diffMetadata.setStatus(Status.DELETED);
            diffMetadata.setType(m.getType());
            diffMetadata.setPreviousVersion(m.getVersion());
            diffMetadataSet.add(diffMetadata);
        });

        return  diffMetadataSet;
    }

    private Set<DiffNode> deleteNodes(Set<Node> nodes) {
        Set<DiffNode> diffNodeSet = new HashSet<>();
        if (nodes == null) {
            nodes = new HashSet<>();
        }
        nodes.forEach(n -> {
            DiffNode diffNode = new DiffNode();
            diffNode.setId(n.getId());
            diffNode.setStatus(Status.DELETED);
            diffNode.setType(n.getType());
            diffNode.setPreviousVersion(n.getVersion());
            diffNode.setNodes(deleteNodes(n.getNodes()));
            diffNode.setMetadata(deleteMetadata(n.getMetadata()));
            diffNodeSet.add(diffNode);
        });

        return diffNodeSet;
    }

    private Set<DiffMetadata> calculateDiffMetaData(Set<Metadata> previous, Set<Metadata> current) {
        if (previous == null) {
            previous = new HashSet<>();
        }
        if (current == null) {
            current = new HashSet<>();
        }
        Map<String, Metadata> currentMetadaMap = current.stream()
                    .collect(Collectors.toMap(m -> m.getVersion().getKey(), Function.identity()));

        Set<DiffMetadata> diffMetadataSet = new HashSet<>();
        for (Metadata metadata : previous) {
            DiffMetadata diffMetadata = new DiffMetadata();
            diffMetadata.setId(metadata.getId());
            diffMetadata.setType(metadata.getType());
            if (!currentMetadaMap.containsKey(metadata.getVersion().getKey())) {
                diffMetadata.setStatus(Status.DELETED);
                diffMetadata.setPreviousVersion(metadata.getVersion());
            } else {
                Metadata currentMetadata = currentMetadaMap.get(metadata.getVersion().getKey());
                if (!metadata.getVersion().getValue().equals(currentMetadata.getVersion().getValue())) {
                    diffMetadata.setStatus(Status.MODIFIED);
                    diffMetadata.setVersion(currentMetadata.getVersion());
                    diffMetadata.setPreviousVersion(metadata.getVersion());
                } else  {
                    diffMetadata.setStatus(Status.UNMODIFIED);
                    diffMetadata.setPreviousVersion(metadata.getVersion());
                }
            }
            diffMetadataSet.add(diffMetadata);
        }

        Set<Metadata> createdMetaData = new HashSet<>(current);
        createdMetaData.removeAll(previous);
        createdMetaData.forEach(m -> {
            DiffMetadata diffMetadata = new DiffMetadata();
            diffMetadata.setId(m.getId());
            diffMetadata.setType(m.getType());
            diffMetadata.setStatus(Status.CREATED);
            diffMetadata.setVersion(m.getVersion());
            diffMetadataSet.add(diffMetadata);
        });

        return diffMetadataSet;
    }

    private Set<DiffMetadata> createMetadata(Set<Metadata> metadata) {
        Set<DiffMetadata> diffMetadataSet = new HashSet<>();
        if (metadata == null) {
            metadata = new HashSet<>();
        }
        metadata.forEach(m -> {
            DiffMetadata diffMetadata = new DiffMetadata();
            diffMetadata.setId(m.getId());
            diffMetadata.setVersion(m.getVersion());
            diffMetadata.setType(m.getType());
            diffMetadata.setStatus(Status.CREATED);
            diffMetadataSet.add(diffMetadata);
        });

        return diffMetadataSet;
    }

    private Set<DiffNode> createNodes(Set<Node> nodes) {
        Set<DiffNode> createdNodes = new HashSet<>();
        if (nodes == null) {
            nodes = new HashSet<>();
        }
        nodes.forEach(n -> {
            DiffNode diffNode = new DiffNode();
            diffNode.setId(n.getId());
            diffNode.setVersion(n.getVersion());
            diffNode.setType(n.getType());
            diffNode.setStatus(Status.CREATED);
            diffNode.setMetadata(createMetadata(n.getMetadata()));
            diffNode.setNodes(createNodes(n.getNodes()));
            createdNodes.add(diffNode);
        });
        return createdNodes;
    }
}
