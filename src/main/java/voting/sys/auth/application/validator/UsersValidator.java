package voting.sys.auth.application.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import voting.sys.auth.application.exception.ResourceNotFoundException;
import voting.sys.auth.domain.entity.Users;
import voting.sys.auth.domain.service.UsersService;

import java.util.Optional;

import static voting.sys.auth.util.ValidatorConstant.NOT_EXIST_MESSAGE;
import static voting.sys.auth.util.ValidatorConstant.PROJECT_VALIDATOR_USER_WITH_IDNP_MESSAGE;

@Component
@RequiredArgsConstructor
public class UsersValidator {

    private final UsersService usersService;

    public void validate(String idnp) {
        Optional<Users> usersByUsername = usersService.getEnabledUserByIdnp(idnp);
        if (usersByUsername.isEmpty()) {
            throw new ResourceNotFoundException(
                    PROJECT_VALIDATOR_USER_WITH_IDNP_MESSAGE
                            + idnp
                            + NOT_EXIST_MESSAGE
            );
        }
    }

}

