package voting.sys.auth.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import voting.sys.auth.domain.entity.Users;
import voting.sys.auth.domain.repository.UsersRepository;

import java.util.List;
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

    @Override
    public Users saveUser(Users dmUsers) {
        return usersRepository.save(dmUsers);
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Optional<Users> getUserById(Long id) {
        return usersRepository.getUsersById(id);
    }


}
