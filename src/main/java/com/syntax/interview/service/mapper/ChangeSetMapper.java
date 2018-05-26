package com.syntax.interview.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntax.interview.domain.ChangeSet;
import com.syntax.interview.service.dto.changeset.ChangeSetDTO;
import com.syntax.interview.service.dto.changeset.ChangeSetData;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.io.IOException;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ChangeSetMapper {

    @Mapping(target = "data", ignore = true)
    ChangeSet toEntity(ChangeSetDTO dto);

    @Mapping(target = "data", ignore = true)
    ChangeSetDTO toDto(ChangeSet entity);

    List<ChangeSet> toEntity(List<ChangeSetDTO> dto);

    List<ChangeSetDTO> toDto(List<ChangeSet> entity);

    @AfterMapping
    default void mapDataToEntity(ChangeSetDTO dto, @MappingTarget ChangeSet entity) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            entity.setData(mapper.writeValueAsString(dto.getData()));
        } catch (JsonProcessingException e) {
            return;
        }
    }

    @AfterMapping
    default void mapDataToDto(ChangeSet entity, @MappingTarget ChangeSetDTO dto) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            dto.setData(mapper.readValue(entity.getData(), ChangeSetData.class));
        } catch (IOException e) {
            return;
        }
    }
}
