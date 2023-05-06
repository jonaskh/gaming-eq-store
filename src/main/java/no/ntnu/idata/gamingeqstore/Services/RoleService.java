package no.ntnu.idata.gamingeqstore.Services;

import no.ntnu.idata.gamingeqstore.Entities.Role;
import no.ntnu.idata.gamingeqstore.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role not found: " + name));
    }

    public void initializeRoles() {
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");

        roleRepository.save(adminRole);
        roleRepository.save(userRole);
    }
}
