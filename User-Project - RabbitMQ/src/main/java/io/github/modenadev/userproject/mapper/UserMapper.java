package io.github.modenadev.userproject.mapper;

import io.github.modenadev.userproject.model.dto.UserRequestDTO;
import io.github.modenadev.userproject.model.dto.UserResponseDTO;
import io.github.modenadev.userproject.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User toEntity(UserRequestDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    public UserResponseDTO toResponseDTO(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }
}
