package no.ntnu.idata.gamingeqstore;

import io.github.cdimascio.dotenv.Dotenv;
import no.ntnu.idata.gamingeqstore.Entities.Product;
import no.ntnu.idata.gamingeqstore.Entities.ProductCategory;
import no.ntnu.idata.gamingeqstore.Services.CategoryService;
import no.ntnu.idata.gamingeqstore.Services.ProductService;
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

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().directory("./").load();
		System.setProperty("jwt_secret", dotenv.get("jwt_secret"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		SpringApplication.run(GamingEqStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		roleService.initializeRoles();
	}
}