package io.github.modenadev.userproject.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import io.github.modenadev.userproject.data.vo.v1.UserVO;

import io.github.modenadev.userproject.mapper.UserMapper;
import io.github.modenadev.userproject.model.User;
import io.github.modenadev.userproject.model.dto.UserResponseDTO;
import io.github.modenadev.userproject.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices  {

    private Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    UserRepository repository;
    @Autowired
    private UserMapper userMapper;

    public UserServices(UserRepository repository, UserMapper mapper) {

        this.repository = repository;
        this.userMapper = mapper;
    }


    public UserResponseDTO update(Long id, String userName, String oldPassword, String newPassword) throws BadRequestException {
        User user = repository.findByUsername(userName)
                .orElseThrow(() -> new BadRequestException("User not found with username " + userName));

        System.out.println("Received username: " + userName);

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
    public User create(UserVO userVO) {
        User user = userMapper.toEntity(userVO);

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