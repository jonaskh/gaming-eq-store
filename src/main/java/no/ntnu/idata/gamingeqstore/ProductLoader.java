
package no.ntnu.idata.gamingeqstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import no.ntnu.idata.gamingeqstore.Entities.Product;
import no.ntnu.idata.gamingeqstore.Repositories.ProductRepository;

import java.util.Arrays;

@Component
@Order(value = 1)
public class ProductLoader implements CommandLineRunner {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create an array of products to insert into the database
        Product[] products = {
            new Product("Headset for office and gaming", "Headsets", 850, "Product 1.png", "Headset for office and gaming description", 1),
            new Product("Gaming mouse", "Mice", 700, "Product 2.png", "Gaming mouse description", 1),
            new Product("Hot gaming headset", "Headsets", 1200, "Product 3.png", "Hot gaming headset description", 1),
            new Product("Xbox gaming console + controller", "Console", 6800, "Product 4.jpg", "Experience the ultimate gaming experience with the Xbox console!", 1),
            new Product("Xbox controller", "Controller", 499, "Product 5.jpg", "The Xbox controller is the ultimate gaming controller.", 1),
            new Product("4x AA batteries", "Batteries", 80, "Product 6.jpg", "The 4x AA batteries are perfect for your controller!", 1)
        };

        // Insert the products into the database
        productRepository.saveAll(Arrays.asList(products));
    }
}