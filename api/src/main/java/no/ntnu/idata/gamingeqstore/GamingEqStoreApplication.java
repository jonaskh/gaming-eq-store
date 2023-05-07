package no.ntnu.idata.gamingeqstore;

import no.ntnu.idata.gamingeqstore.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class GamingEqStoreApplication implements CommandLineRunner {

	@Autowired
	private RoleService roleService;

	public static void main(String[] args) {
		SpringApplication.run(GamingEqStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		roleService.initializeRoles();
	}
}
