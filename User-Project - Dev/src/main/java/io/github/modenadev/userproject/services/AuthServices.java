package io.github.modenadev.userproject.services;

import io.github.modenadev.userproject.data.vo.v1.AccountCredentialsVO;
import io.github.modenadev.userproject.data.vo.v1.TokenVO;
import io.github.modenadev.userproject.jwt.security.JwtTokenProvider;
import io.github.modenadev.userproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity signin(AccountCredentialsVO data){

        try {

            var username = data.getUsername();
            var password = data.getPassword();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            var user = userRepository.findByUsername(username);

            var tokenResponse = new TokenVO();
            if(user != null){

                tokenResponse = jwtTokenProvider.createAccessToken(username);

            }
            else {
                throw new UsernameNotFoundException("Username " + username + " not found");
            }

            return ResponseEntity.ok(tokenResponse);

        }
        catch(Exception e){
            throw new BadCredentialsException("Invalid username or password");
        }
    }
    public ResponseEntity refreshToken(String username, String refreshToken){



            var user = userRepository.findByUsername(username);

            var tokenResponse = new TokenVO();
            if(user != null){
                tokenResponse = jwtTokenProvider.refreshToken(refreshToken);
            }
            else {
                throw new UsernameNotFoundException("Username " + username + " not found");
            }
            return ResponseEntity.ok(tokenResponse);


    }
}
