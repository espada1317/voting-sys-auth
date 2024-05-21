package voting.sys.auth.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import voting.sys.auth.application.dto.request.UsersRequestDto;
import voting.sys.auth.application.dto.response.UsersResponseDto;
import voting.sys.auth.domain.entity.Users;

@Mapper
public interface UsersMapper {

    UsersResponseDto userToUserDtoResponse(Users dmUsers);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userRoles", ignore = true)
    Users usersDtoRequestToUsers(UsersRequestDto userDtoRequest);

}
