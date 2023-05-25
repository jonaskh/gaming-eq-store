
package no.ntnu.idata.gamingeqstore;

import no.ntnu.idata.gamingeqstore.Services.CategoryService;
import no.ntnu.idata.gamingeqstore.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import no.ntnu.idata.gamingeqstore.Entities.Product;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(value = 1)
public class ProductLoader implements CommandLineRunner {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Override
    public void run(String... args) throws Exception {
        // Create an array of products to insert into the database
        categoryService.addCategories();

        List<Product> products = new ArrayList<>();

        Product p = new Product("Headset for office and gaming", 850, "Product 1.png", "Headset for office and gaming description", 1);
        Product p1 = new Product("Gaming mouse", 700, "Product 2.png", "Gaming mouse description", 1);
        Product p2 = new Product("Hot gaming headset", 1200, "Product 3.png", "Hot gaming headset description", 1);
        Product p3 = new Product("Steelseries GameSonic", 850, "Product 12.png", "Headset for office and gaming description", 1);
        Product p4 = new Product("The Quantum Headset", 1499, "Product 13.png", "This new tpo of the line gaming head set will give you a competitive advantage", 1);
        Product p5 = new Product("Steelseries Apex", 1249, "Product 9.png", "A headset to suit your every need, whether its for gaming or the office", 1);
        Product p6 = new Product("Office mouse", 399, "Product 7.png", "This mouse will ensure pin-point precision for both office and gaming use", 1);
        Product p7 = new Product("PulseFire", 499, "Product 8.png", "Never miss again with the PulseFire mouse!", 1);
        Product p8 = new Product("Cheap Office mouse", 199, "Product 10.png", "A cheaper alternative while still providing excellent accuracy", 1);

        //adds one or more categories to each product
        productService.addCategory(p, "Headsets");
        productService.addCategory(p1, "Mice");
        productService.addCategory(p2, "Headsets");
        productService.addCategory(p3, "Headsets");
        productService.addCategory(p4, "Headsets");
        productService.addCategory(p5, "Headsets");
        productService.addCategory(p6, "Mice");
        productService.addCategory(p7, "Mice");
        productService.addCategory(p8, "Mice");
        productService.addCategory(p, "Office");
        productService.addCategory(p, "Gaming");
        productService.addCategory(p1, "Gaming");
        productService.addCategory(p2, "Gaming");
        productService.addCategory(p3, "Gaming");
        productService.addCategory(p4, "Gaming");
        productService.addCategory(p5, "Office");
        productService.addCategory(p5, "Gaming");
        productService.addCategory(p6, "Office");
        productService.addCategory(p7, "Gaming");
        productService.addCategory(p8, "Office");

        //add products to list

        products.add(p);
        products.add(p1);

        products.add(p2);
        products.add(p3);

        products.add(p4);
        products.add(p5);

        products.add(p6);
        products.add(p7);

        products.add(p8);

        // Insert the products into the database
        productService.saveAll(products);

    }
}