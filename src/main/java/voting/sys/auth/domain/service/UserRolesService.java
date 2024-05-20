package voting.sys.auth.domain.service;

import voting.sys.auth.domain.entity.UserRoles;

import java.util.List;

public interface UserRolesService {

    List<UserRoles> getAllUserRoles();

    UserRoles getUserRoleByName(String name);

}