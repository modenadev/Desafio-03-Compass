package io.github.modenadev.userproject.controllers;


import io.github.modenadev.userproject.data.vo.v1.LoginUserDto;
import io.github.modenadev.userproject.data.vo.v1.RecoveryJwtTokenDto;
import io.github.modenadev.userproject.exceptions.InvalidUsernamePasswordException;
import io.github.modenadev.userproject.model.User;
import io.github.modenadev.userproject.model.dto.UserRequestDTO;
import io.github.modenadev.userproject.model.dto.UserResponseDTO;
import io.github.modenadev.userproject.services.UserServices;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServices userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRequestDTO userRequestDTO) throws InvalidUsernamePasswordException {
        User savedUser = userService.create(userRequestDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/update-password")
    public ResponseEntity<Void> updatePassword(@RequestBody UserRequestDTO request) throws BadRequestException {
        System.out.println("Username: " + request.getUsername());
        System.out.println("Old Password: " + request.getOldPassword());
        System.out.println("New Password: " + request.getNewPassword());
        userService.update(request.getUsername(), request.getOldPassword(), request.getNewPassword());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
        RecoveryJwtTokenDto token = userService.authenticateUser(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> getAuthenticationTest() {
        return new ResponseEntity<>("Authenticated succesfuly", HttpStatus.OK);
    }
}
