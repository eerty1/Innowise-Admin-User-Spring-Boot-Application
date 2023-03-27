package com.innowise.model.mapper;

import com.innowise.model.User;
import com.innowise.model.dto.UserDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserMapper userMapperInstance = Mappers.getMapper(UserMapper.class);

    @Named("userDTOWithoutPassword")
    @Mapping(target = "password", ignore = true)
    UserDTO toDTO(User user);

//    User toUser(UserDTO userDTO);

    @IterableMapping(qualifiedByName = "userDTOWithoutPassword")
    List<UserDTO> toDTOs(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    User updateUserFromDTO(UserDTO userDTO, @MappingTarget User user);

    @Condition
    default boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }
}
