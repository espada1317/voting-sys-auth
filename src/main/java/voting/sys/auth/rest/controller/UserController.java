package voting.sys.auth.rest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import voting.sys.auth.application.dto.request.UsersRequestDto;
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

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<UsersResponseDto>> retrieveListOfAllUsers() {
        return new ResponseEntity<>(usersFacade.getAllUsers(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<UsersResponseDto> saveUser(@RequestBody @Valid UsersRequestDto dmUsersRequestDto,
                                                     BindingResult bindingResult) {
        return new ResponseEntity<>(usersFacade.addUser(dmUsersRequestDto, bindingResult), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/enablement/{id}")
    public ResponseEntity<UsersResponseDto> setUserEnablement(@PathVariable("id") Long id) {
        return new ResponseEntity<>(usersFacade.modifyEnablement(id), HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/resetPassword/{id}")
    public ResponseEntity<UsersResponseDto> resetUserPassword(@PathVariable("id") Long id) {
        return new ResponseEntity<>(usersFacade.resetPassword(id), HttpStatus.NO_CONTENT);
    }

}
