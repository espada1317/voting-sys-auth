package voting.sys.auth.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import voting.sys.auth.application.dto.request.AuthRequestDto;
import voting.sys.auth.rest.security.JwtService;

import java.util.stream.Collectors;

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
            String roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));
            return jwtService.generateToken(authRequest.getIdnp(), roles);
        } else {
            throw new UsernameNotFoundException("Invalid user request.");
        }
    }

}