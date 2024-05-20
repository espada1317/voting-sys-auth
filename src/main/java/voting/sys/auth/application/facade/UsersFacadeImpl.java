package voting.sys.auth.application.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import voting.sys.auth.application.dto.response.UserRolesResponseDto;
import voting.sys.auth.application.dto.response.UsersResponseDto;
import voting.sys.auth.application.mapper.UserRolesMapper;
import voting.sys.auth.application.mapper.UsersMapper;
import voting.sys.auth.application.validator.UsersValidator;
import voting.sys.auth.domain.entity.UserRoles;
import voting.sys.auth.domain.entity.Users;
import voting.sys.auth.domain.service.UserRolesService;
import voting.sys.auth.domain.service.UsersService;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsersFacadeImpl implements UsersFacade {

    private final UsersService usersService;

    private final UserRolesService userRolesService;

    private final UsersValidator usersValidator;

    private final UsersMapper userMapper;

    private final UserRolesMapper userRolesMapper;

    @Override
    public UsersResponseDto getEnabledUserByIdnp(String idnp) {
        usersValidator.validate(idnp);

        Optional<Users> user = usersService.getEnabledUserByIdnp(idnp);
        return user.map(userMapper::userToUserDtoResponse).orElse(null);
    }

    @Override
    public List<UserRolesResponseDto> getAllRoles() {
        List<UserRoles> userRoles = userRolesService.getAllUserRoles();

        return userRoles.stream()
                .map(userRolesMapper::userRolesToUserRolesDtoResponse)
                .toList();
    }

}