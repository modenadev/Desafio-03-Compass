package io.github.modenadev.userproject.services;


import io.github.modenadev.userproject.data.vo.v1.LoginUserDto;
import io.github.modenadev.userproject.data.vo.v1.RecoveryJwtTokenDto;
import io.github.modenadev.userproject.exceptions.InvalidUsernamePasswordException;
import io.github.modenadev.userproject.jwt.security.authentication.JwtTokenService;
import io.github.modenadev.userproject.jwt.security.authentication.userdetails.UserDetailsImpl;
import io.github.modenadev.userproject.mapper.UserMapper;
import io.github.modenadev.userproject.model.User;
import io.github.modenadev.userproject.model.dto.AddressResponse;
import io.github.modenadev.userproject.model.dto.UserRequestDTO;
import io.github.modenadev.userproject.model.dto.UserResponseDTO;
import io.github.modenadev.userproject.repositories.UserRepository;
import io.github.modenadev.userproject.repositories.ViaCepClient;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices  {


    @Autowired
    private UserRepository repository;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService; // Injetando o JwtTokenService

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private ViaCepClient viaCepClient; //

    public UserServices(UserRepository repository, UserMapper mapper) {

        this.repository = repository;
        this.userMapper = mapper;
    }

    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        // Cria um objeto de autenticação com o email e a senha do usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.username(), loginUserDto.password());

        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Obtém o objeto UserDetails do usuário autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Gera um token JWT para o usuário autenticado
        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }


    public UserResponseDTO update(String username, String oldPassword, String newPassword) throws BadRequestException {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new BadRequestException("User not found with username " + username));

        System.out.println("Received username: " + username);

        if (!isPasswordValid(oldPassword, user.getPassword())) {
            System.out.println("Provided old password: " + oldPassword);
            System.out.println("Stored password: " + user.getPassword());
            throw new BadRequestException("Old password is incorrect");
        }

        // Codifique a nova senha antes de salvar
        user.setPassword(passwordEncoder.encode(newPassword)); // Use o passwordEncoder
        User updatedUser = repository.save(user);

        return userMapper.toResponseDTO(updatedUser);
    }


    private boolean isPasswordValid(String rawPassword, String storedPassword) {
        return rawPassword.equals(storedPassword);
    }


    @Transactional
    public User create(UserRequestDTO userVO) throws InvalidUsernamePasswordException {
        User user = userMapper.toEntity(userVO);

        // Buscar o endereço usando o CEP
        AddressResponse address = viaCepClient.searchZipCode(user.getZipCode().toString());
        user.setAddress(address); // Certifique-se de que o método setAddress aceita AddressResponse

        // Verificar se o usuário já existe
        if (repository.findByUsername(user.getUsername()).isPresent()) {
            throw new InvalidUsernamePasswordException("User already exists in database");
        }

        // Verificar se o email já existe
        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new InvalidUsernamePasswordException("Email already exists in database, please do login with your user");
        }

        // Codificar a senha
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Salvar o usuário no repositório
        return repository.save(user);
    }



}