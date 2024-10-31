package io.github.modenadev.userproject.services;

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

    public UserServices(UserRepository repository) {
        this.repository = repository;
    }



//    public UserVO update(Long id) {
//
//        if (user == null) throw new ResourceNotFoundException("User not found!");
//
//        logger.info("Updating one person!");
//
//        var entity = repository.findById(user.getId())
//                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
//
//        entity.setPassword(entity.getPassword());
//
//        return user;
//    }

    @Transactional
    public User create(UserVO userVO) {
        User user = userMapper.toEntity(userVO);
        // Não atribua ID ao usuário aqui
        return repository.save(user);
    }
}