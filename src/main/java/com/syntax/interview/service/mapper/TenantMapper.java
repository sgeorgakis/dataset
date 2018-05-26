package com.syntax.interview.service.mapper;

import com.syntax.interview.domain.Tenant;
import com.syntax.interview.service.dto.TenantDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface TenantMapper {

    Tenant toEntity(TenantDTO dto);

    TenantDTO toDto(Tenant entity);

    List<Tenant> toEntity(List<TenantDTO> dto);

    List<TenantDTO> toDto(List<Tenant> entity);
}
