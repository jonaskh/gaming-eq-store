package no.ntnu.idata.gamingeqstore.Services;


import no.ntnu.idata.gamingeqstore.Entities.Role;
import no.ntnu.idata.gamingeqstore.Entities.User;
import no.ntnu.idata.gamingeqstore.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }



// ...

    public User saveUser(User user) {
        if (user.getRoles().isEmpty()) {
            Role defaultRole = roleService.findByName("USER");
            user.addRole(defaultRole);
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }


}
