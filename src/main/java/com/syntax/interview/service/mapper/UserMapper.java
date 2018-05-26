package com.syntax.interview.service.mapper;

import com.syntax.interview.domain.User;
import com.syntax.interview.service.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ChangeSetMapper.class)
public interface UserMapper {

    User toEntity(UserDTO dto);

    UserDTO toDto(User entity);

    List<User> toEntity(List<UserDTO> dto);

    List<UserDTO> toDto(List<User> entity);
}
