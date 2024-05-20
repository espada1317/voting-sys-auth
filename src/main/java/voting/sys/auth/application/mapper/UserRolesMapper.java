package voting.sys.auth.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import voting.sys.auth.application.dto.response.UserRolesResponseDto;
import voting.sys.auth.domain.entity.UserRoles;

@Mapper
public interface UserRolesMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "id", source = "id")
    UserRolesResponseDto userRolesToUserRolesDtoResponse(UserRoles dmUserRoles);

}
