package voting.sys.auth.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import voting.sys.auth.application.dto.response.UserRolesResponseDto;
import voting.sys.auth.application.dto.response.UsersResponseDto;
import voting.sys.auth.application.facade.UsersFacade;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UsersFacade usersFacade;

    @PreAuthorize("hasAnyAuthority('Admin', 'User')")
    @GetMapping("{idnp}")
    public ResponseEntity<UsersResponseDto> retrieveUserByUsername(@PathVariable("idnp") String idnp) {
        return new ResponseEntity<>(usersFacade.getEnabledUserByIdnp(idnp), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/roles")
    public ResponseEntity<List<UserRolesResponseDto>> retrieveListOfAllRoles() {
        return new ResponseEntity<>(usersFacade.getAllRoles(), HttpStatus.OK);
    }

}
