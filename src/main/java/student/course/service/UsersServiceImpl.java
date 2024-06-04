package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import student.course.exceptions.UserNameAlreadyExistsException;
import student.course.model.UserAuthority;
import student.course.model.UserRoles;
import student.course.model.Users;
import student.course.repository.UsersRepository;
import student.course.repository.UsersRolesRepository;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService, UserDetailsService {

    private final UsersRepository usersRepository;

    private final UsersRolesRepository usersRolesRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void registration(String username, String password) {
        if (usersRepository.findByUsername(username).isEmpty()) {
            Users users = usersRepository.save(
                    new Users()
                            .setUserId(null)
                            .setUsername(username)
                            .setPassword(passwordEncoder.encode(password))
                            .setLocked(false)
                            .setExpired(false)
                            .setEnabled(true)
            );
            usersRolesRepository.save(new UserRoles(null, UserAuthority.USER, users));
        } else {
            throw new UserNameAlreadyExistsException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
