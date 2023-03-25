package com.innowise.model.mapper;

import com.innowise.model.User;
import com.innowise.model.dto.UserDTO;
import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserMapper userMapperInstance = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);

    User toUser(UserDTO userDTO);

    List<UserDTO> toDTOs(List<User> users);

    User updateUserFromDTO(UserDTO userDTO, @MappingTarget User user);

    @Condition
    default boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }
}
