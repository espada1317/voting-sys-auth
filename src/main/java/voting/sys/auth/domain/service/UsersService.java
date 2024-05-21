package voting.sys.auth.domain.service;

import voting.sys.auth.domain.entity.Users;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    Optional<Users> getEnabledUserByIdnp(String idnp);

    Users saveUser(Users users);

    List<Users> getAllUsers();

    Optional<Users> getUserById(Long id);

}
