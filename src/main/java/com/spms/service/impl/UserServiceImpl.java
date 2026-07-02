package com.spms.service.impl;

import com.spms.dto.request.UserRequestDTO;
import com.spms.dto.response.UserResponseDTO;
import com.spms.entity.Role;
import com.spms.entity.User;
import com.spms.exception.EmailAlreadyExistsException;
import com.spms.exception.PhoneNumberAlreadyExistsException;
import com.spms.exception.RoleNotFoundException;
import com.spms.exception.UserNotFoundException;
import com.spms.exception.UsernameAlreadyExistsException;
import com.spms.mapper.UserMapper;
import com.spms.repository.RoleRepository;
import com.spms.repository.UserRepository;
import com.spms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO createUser(UserRequestDTO requestDTO) {

        // Check username
        if (userRepository.existsByUsername(requestDTO.getUsername())) {
            throw new UsernameAlreadyExistsException(requestDTO.getUsername());
        }

        // Check email
        if (userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new EmailAlreadyExistsException(requestDTO.getEmail());
        }

        // Check phone number
        if (requestDTO.getPhoneNumber() != null
                && userRepository.existsByPhoneNumber(requestDTO.getPhoneNumber())) {

            throw new PhoneNumberAlreadyExistsException(requestDTO.getPhoneNumber());
        }

        // Find role
        Role role = roleRepository.findById(requestDTO.getRoleId())
                .orElseThrow(() ->
                        new RoleNotFoundException(
                                "Role not found with id: " + requestDTO.getRoleId()));

        // Convert DTO to Entity
        User user = userMapper.toEntity(requestDTO);

        // Assign role
        user.setRole(role);

        // Encode password before saving
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));

        // Save user
        User savedUser = userRepository.save(user);

        // Return response
        return userMapper.toResponseDTO(savedUser);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {

        // Find user by ID
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: " + id));

        // Return response DTO
        return userMapper.toResponseDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO) {

        throw new UnsupportedOperationException("Update user will be implemented later.");
    }

    @Override
    public void deleteUser(Long id) {

        // Find user by ID
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: " + id));

        // Delete user
        userRepository.delete(user);
    }
}