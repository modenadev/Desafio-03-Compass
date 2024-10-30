package io.github.modenadev.userproject.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import io.github.modenadev.userproject.data.vo.v1.UserVO;

import io.github.modenadev.userproject.exceptions.BadRequestException;
import io.github.modenadev.userproject.mapper.UserMapper;
import io.github.modenadev.userproject.model.User;
import io.github.modenadev.userproject.model.dto.UserResponseDTO;
import io.github.modenadev.userproject.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices  {

    private Logger logger = Logger.getLogger(UserServices.class.getName());

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository repository;
    @Autowired
    private UserMapper userMapper;

    public UserServices(UserRepository repository, UserMapper mapper) {

        this.repository = repository;
        this.userMapper = mapper;
    }


    public UserResponseDTO update(Long id, String username, String oldPassword, String newPassword) throws BadRequestException {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new BadRequestException("User not found with username " + username));

        System.out.println("Received username: " + username);

        if (!isPasswordValid(oldPassword, user.getPassword())) {
            System.out.println("Provided old password: " + oldPassword);
            System.out.println("Stored password: " + user.getPassword());
            throw new BadRequestException("Old password is incorrect");
        }

        user.setPassword(newPassword);
        User updatedUser = repository.save(user);

        return userMapper.toResponseDTO(updatedUser);
    }


    private boolean isPasswordValid(String rawPassword, String storedPassword) {
        return rawPassword.equals(storedPassword);
    }


    @Transactional
    public User create(UserVO userVO) throws BadRequestException {
        User user = userMapper.toEntity(userVO);

        if (repository.findByUsername(userVO.getUserName()).isPresent()) {
            throw new BadRequestException("User already exists in database");
        }

        if (repository.findByEmail(userVO.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists in database, please do login with your user");
        }
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }


    public void delete (Long id) {
        repository.deleteById(id);

    }

    public User findById(Long id) {
        Optional<User> optionalUser = repository.findById(id);
        return optionalUser.orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }



}