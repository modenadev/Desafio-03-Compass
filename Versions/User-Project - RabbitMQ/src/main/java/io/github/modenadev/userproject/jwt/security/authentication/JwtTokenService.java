package io.github.modenadev.userproject.jwt.security.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.github.modenadev.userproject.jwt.security.authentication.userdetails.UserDetailsImpl;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class JwtTokenService {

    private static final String SECRET_KEY = "umaChaveMuitoSeguraDePeloMenos32Caracteres";
    private static final String ISSUER = "modena-api";
    private static final long EXPIRATION_HOURS = 1000;

    private final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    public String generateToken(UserDetailsImpl user) {
        try {

            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(creationDate())
                    .withExpiresAt(expirationDate())
                    .withSubject(user.getUsername())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Error generating token", exception);

        }

    }

    public String getSubjectFromToken(String token) {
        try {
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("The token is invalid or expired", exception);
        }
    }

    private Date creationDate() {
        return Date.from(ZonedDateTime.now(ZoneId.of("America/Recife")).toInstant());
    }

    private Date expirationDate() {
        return Date.from(ZonedDateTime.now(ZoneId.of("America/Recife")).plusHours(EXPIRATION_HOURS).toInstant());
    }
}
