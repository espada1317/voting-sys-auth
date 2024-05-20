package voting.sys.auth.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import voting.sys.auth.domain.entity.Users;
import voting.sys.auth.domain.repository.UsersRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public Optional<Users> getEnabledUserByIdnp(String idnp) {
        return usersRepository
                .getUsersByIdnp(idnp)
                .filter(Users::getIsEnabled);
    }

}
