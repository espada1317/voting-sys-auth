package voting.sys.auth.application.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import voting.sys.auth.application.dto.request.UsersRequestDto;
import voting.sys.auth.application.exception.ResourceNotFoundException;
import voting.sys.auth.application.exception.ValidationCustomException;
import voting.sys.auth.domain.entity.Users;
import voting.sys.auth.domain.service.UsersService;

import java.util.Optional;

import static voting.sys.auth.util.ValidatorConstant.ALREADY_EXIST_MESSAGE;
import static voting.sys.auth.util.ValidatorConstant.NOT_EXIST_MESSAGE;
import static voting.sys.auth.util.ValidatorConstant.USER_VALIDATOR_IDNP_FIELD;
import static voting.sys.auth.util.ValidatorConstant.USER_VALIDATOR_USER_WITH_IDNP_MESSAGE;

@Component
@RequiredArgsConstructor
public class UsersValidator {

    private final UsersService usersService;

    public void validate(UsersRequestDto dmUsersRequestDto,
                         BindingResult bindingResult) {
        Optional<Users> userByUsername = usersService.getEnabledUserByIdnp(dmUsersRequestDto.getIdnp());
        if (userByUsername.isPresent()) {
            bindingResult.rejectValue(USER_VALIDATOR_USER_WITH_IDNP_MESSAGE, "",
                    USER_VALIDATOR_IDNP_FIELD
                            + dmUsersRequestDto.getIdnp()
                            + ALREADY_EXIST_MESSAGE);
        }

        if (bindingResult.hasErrors()) {
            throw new ValidationCustomException(bindingResult);
        }
    }

    public void validate(Long id) {
        Optional<Users> userById = usersService.getUserById(id);
        if (userById.isEmpty()) {
            throw new ResourceNotFoundException(
                    USER_VALIDATOR_USER_WITH_IDNP_MESSAGE
                            + id
                            + NOT_EXIST_MESSAGE
            );
        }
    }

    public void validate(String idnp) {
        Optional<Users> usersByUsername = usersService.getEnabledUserByIdnp(idnp);
        if (usersByUsername.isEmpty()) {
            throw new ResourceNotFoundException(
                    USER_VALIDATOR_USER_WITH_IDNP_MESSAGE
                            + idnp
                            + NOT_EXIST_MESSAGE
            );
        }
    }

}

