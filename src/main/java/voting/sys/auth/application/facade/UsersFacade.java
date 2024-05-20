package voting.sys.auth.application.facade;

import voting.sys.auth.application.dto.response.UserRolesResponseDto;
import voting.sys.auth.application.dto.response.UsersResponseDto;

import java.util.List;

public interface UsersFacade {

    UsersResponseDto getEnabledUserByIdnp(String idnp);

    List<UserRolesResponseDto> getAllRoles();

}
