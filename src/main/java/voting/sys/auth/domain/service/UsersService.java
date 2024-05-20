package voting.sys.auth.domain.service;

import voting.sys.auth.domain.entity.Users;

import java.util.Optional;

public interface UsersService {

    Optional<Users> getEnabledUserByIdnp(String idnp);

}
