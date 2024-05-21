package voting.sys.auth.application.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import voting.sys.auth.application.dto.request.UsersRequestDto;
import voting.sys.auth.application.dto.response.UserRolesResponseDto;
import voting.sys.auth.application.dto.response.UsersResponseDto;
import voting.sys.auth.application.mapper.UserRolesMapper;
import voting.sys.auth.application.mapper.UsersMapper;
import voting.sys.auth.application.validator.UsersValidator;
import voting.sys.auth.domain.entity.UserRoles;
import voting.sys.auth.domain.entity.Users;
import voting.sys.auth.domain.service.UserRolesService;
import voting.sys.auth.domain.service.UsersService;
import voting.sys.auth.util.ValidatorConstant;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public UsersResponseDto addUser(UsersRequestDto usersRequestDto,
                                    BindingResult bindingResult) {
        usersValidator.validate(usersRequestDto, bindingResult);

        Users users = userMapper.usersDtoRequestToUsers(usersRequestDto);

        users.setUserRoles(
                usersRequestDto
                        .getRoles()
                        .stream()
                        .map(userRolesService::getUserRoleByName)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet())
        );

        Users response = usersService.saveUser(users);

        return userMapper.userToUserDtoResponse(response);
    }

    @Override
    public List<UsersResponseDto> getAllUsers() {
        List<Users> users = usersService.getAllUsers();

        return users.stream()
                .map(userMapper::userToUserDtoResponse)
                .sorted(Comparator.comparingLong(UsersResponseDto::getId))
                .toList();
    }

    @Override
    public UsersResponseDto modifyEnablement(Long id) {
        usersValidator.validate(id);

        Optional<Users> optionalUsers = usersService.getUserById(id);
        Users response = null;

        if (optionalUsers.isPresent()) {
            Users users = optionalUsers.get();
            users.setIsEnabled(!users.getIsEnabled());
            response = usersService.saveUser(users);
        }

        return userMapper.userToUserDtoResponse(response);
    }

    @Override
    public UsersResponseDto resetPassword(Long id) {
        usersValidator.validate(id);

        Optional<Users> optionalUsers = usersService.getUserById(id);
        Users response = null;

        if (optionalUsers.isPresent()) {
            Users users = optionalUsers.get();
            users.setPassword(ValidatorConstant.USER_DEFAULT_PASSWORD);
            response = usersService.saveUser(users);
        }

        return userMapper.userToUserDtoResponse(response);
    }

}