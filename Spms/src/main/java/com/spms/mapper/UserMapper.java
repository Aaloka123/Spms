package com.spms.mapper;

import com.spms.dto.request.UserRequestDTO;
import com.spms.dto.response.UserResponseDTO;
import com.spms.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // Convert Request DTO to User Entity
    public User toEntity(UserRequestDTO requestDTO) {

        User user = new User();

        user.setFirstName(requestDTO.getFirstName());
        user.setLastName(requestDTO.getLastName());
        user.setUsername(requestDTO.getUsername());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(requestDTO.getPassword());
        user.setPhoneNumber(requestDTO.getPhoneNumber());
        user.setAddress(requestDTO.getAddress());

        return user;
    }

    // Convert User Entity to Response DTO
    public UserResponseDTO toResponseDTO(User user) {

        UserResponseDTO responseDTO = new UserResponseDTO();

        responseDTO.setId(user.getId());
        responseDTO.setFirstName(user.getFirstName());
        responseDTO.setLastName(user.getLastName());
        responseDTO.setUsername(user.getUsername());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setPhoneNumber(user.getPhoneNumber());
        responseDTO.setAddress(user.getAddress());
        responseDTO.setEnabled(user.getEnabled());

        // Prevent NullPointerException
        if (user.getRole() != null) {
            responseDTO.setRoleName(user.getRole().getRoleName());
        }

        responseDTO.setCreatedAt(user.getCreatedAt());
        responseDTO.setUpdatedAt(user.getUpdatedAt());

        return responseDTO;
    }

}