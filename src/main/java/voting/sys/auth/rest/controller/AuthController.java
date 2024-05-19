package voting.sys.auth.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import voting.sys.auth.application.dto.request.AuthRequestDto;
import voting.sys.auth.rest.security.JwtService;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequestDto authRequest) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authRequest.getIdnp(),
                                authRequest.getPassword()
                        )
                );

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getIdnp());
        } else {
            throw new UsernameNotFoundException("Invalid user request.");
        }
    }

}