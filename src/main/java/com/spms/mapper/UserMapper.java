package com.spms.mapper;

import com.spms.dto.request.UserRequestDTO;
import com.spms.dto.response.UserResponseDTO;
import com.spms.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//Mapper for converting between User Entity and DTOs
@Mapper(componentModel = "spring")
public interface UserMapper {

    // Convert UserRequestDTO to User Entity
    User toEntity(UserRequestDTO requestDTO);

    // Convert User Entity to UserResponseDTO
    @Mapping(target = "roleName", source = "role.roleName")
    UserResponseDTO toResponseDTO(User user);

}