package io.github.modenadev.userproject.services;

import java.util.logging.Logger;

import io.github.modenadev.userproject.data.vo.v1.UserVO;

import io.github.modenadev.userproject.exceptions.NotFoundExcepetion;
import io.github.modenadev.userproject.mapper.UserMapper;
import io.github.modenadev.userproject.model.User;
import io.github.modenadev.userproject.model.dto.UserRequestDTO;
import io.github.modenadev.userproject.model.dto.UserResponseDTO;
import io.github.modenadev.userproject.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;


@Service
public class UserServices  {

    private Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    UserRepository repository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;

    public UserServices(UserRepository repository) {
        this.repository = repository;
    }



//    @Transactional
//    public UserVO update(UserVO userVO, Long id, UserRequestDTO userRequestDTO) {
//
//    }

    public User update( long id, UserRequestDTO userRequestDTO) {
        User user = userMapper.toEntity(userRequestDTO);

        userRepository.findById(id)
                .ifPresentOrElse(existingVehicle -> {
                            user.setId(id);
                            userRepository.save(user);
                        },
                        () -> { throw new NotFoundExcepetion("Id not found with id: " + id);});
        return userRepository.save(user);

    }


    public User updatePassword(Long id, String oldPassword, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        // Verifique se a senha antiga está correta
        if (!user.getPassword().equals(oldPassword)) {
            throw new ResourceNotFoundException("Old password is incorrect");
        }

        user.setPassword(newPassword); // Atualiza a senha para a nova
        return userRepository.save(user);
    }


//    @Transactional
//    public User create(UserVO userVO) {
//        User user = userMapper.toEntity(userVO);
//
//        return repository.save(user);
//    }
}