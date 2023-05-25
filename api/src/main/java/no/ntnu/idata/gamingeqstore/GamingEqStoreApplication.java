package no.ntnu.idata.gamingeqstore;

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
		SpringApplication.run(GamingEqStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		roleService.initializeRoles();
//		Product product = new Product("Gaming Mouse", 199, "Product 1.png","A mouse", 1);
//		ProductCategory cat1 = new ProductCategory("Mice");
//		ProductCategory cat2 = new ProductCategory("Keyboard");
//		categoryService.save(cat1);
//		categoryService.save(cat2);
//
//		productService.addCategory(product, cat1.getCategoryName());
//		productService.addCategory(product, cat2.getCategoryName());
//		productService.save(product);
//
//		System.out.println("before: " + productService.findByName("Gaming Mouse"));
//
//		System.out.println(productService.findProductsByCategory("Keyboard"));
//		productService.findByName("Gaming Mouse").get().addCategory(cat1);
//		System.out.println("after : " + productService.findByName("Gaming Mouse"));



	}
}
