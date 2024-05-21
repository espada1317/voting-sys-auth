package voting.sys.auth.application.facade;

import org.springframework.validation.BindingResult;
import voting.sys.auth.application.dto.request.UsersRequestDto;
import voting.sys.auth.application.dto.response.UserRolesResponseDto;
import voting.sys.auth.application.dto.response.UsersResponseDto;

import java.util.List;

public interface UsersFacade {

    UsersResponseDto getEnabledUserByIdnp(String idnp);

    List<UserRolesResponseDto> getAllRoles();

    UsersResponseDto addUser(UsersRequestDto dmUsersRequestDto, BindingResult bindingResult);

    List<UsersResponseDto> getAllUsers();

    UsersResponseDto modifyEnablement(Long id);

    UsersResponseDto resetPassword(Long id);

}
