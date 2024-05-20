package voting.sys.auth.application.mapper;

import org.mapstruct.Mapper;
import voting.sys.auth.application.dto.response.UsersResponseDto;
import voting.sys.auth.domain.entity.Users;

@Mapper
public interface UsersMapper {

    UsersResponseDto userToUserDtoResponse(Users dmUsers);

}
