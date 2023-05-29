
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

        Product p = new Product("Ironseries VoidSet", 849, "Product 1.png", "Headset for office and gaming description", 1);
        Product p1 = new Product("ElitePulse", 699, "Product 2.png", "Gaming mouse description", 1);
        Product p2 = new Product("Hot gaming headset", 1199, "Product 3.png", "Hot gaming headset description", 1);
        Product p3 = new Product("Ironseries GameSonic", 849, "Product 12.png", "Headset for office and gaming description", 1);
        Product p4 = new Product("The Quantum", 1499, "Product 13.png", "This new tpo of the line gaming head set will give you a competitive advantage", 1);
        Product p5 = new Product("IronSeries Apex", 1249, "Product 9.png", "A headset to suit your every need, whether its for gaming or the office", 1);
        Product p6 = new Product("Office mouse", 399, "Product 7.png", "This mouse will ensure pin-point precision for both office and gaming use", 1);
        Product p7 = new Product("PulseFire", 499, "Product 8.png", "Never miss again with the PulseFire mouse!", 1);
        Product p8 = new Product("Razor SwiftPrecision", 199, "Product 10.png", "A cheaper alternative while still providing excellent accuracy", 1);
        Product p9 = new Product("Razor ProTrack", 199, "gaming_keyboard.png", "This keyboard offers high quality support for both office use and gaming.", 1);
        Product p10 = new Product("ASOS BlazeScroll", 149, "black_keyboard.png", "A keyboard optimized for gaming", 1);
        Product p11 = new Product("ASOS PowerScreen", 299, "monitor.png", "A monitor designed for high precision images in both the office and at home", 1);
        Product p12 = new Product("Legitech OfficeMaster", 199, "white_keyboard.png", "A great keyboard designed for office use", 1);

        productService.addCategory(p, "Headsets");
        productService.addCategory(p1, "Mouse");
        productService.addCategory(p2, "Headsets");
        productService.addCategory(p3, "Headsets");
        productService.addCategory(p4, "Headsets");
        productService.addCategory(p5, "Headsets");
        productService.addCategory(p6, "Mouse");
        productService.addCategory(p7, "Mouse");
        productService.addCategory(p8, "Mouse");
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
        productService.addCategory(p9, "Gaming");
        productService.addCategory(p9, "Office");
        productService.addCategory(p9, "Keyboards");
        productService.addCategory(p10, "Gaming");
        productService.addCategory(p10, "Keyboards");
        productService.addCategory(p11, "Monitors");
        productService.addCategory(p11, "Office");
        productService.addCategory(p11, "Gaming");
        productService.addCategory(p12, "Keyboards");
        productService.addCategory(p12, "Office");


        products.add(p);
        products.add(p1);

        products.add(p2);
        products.add(p3);

        products.add(p4);
        products.add(p5);

        products.add(p6);
        products.add(p7);

        products.add(p8);
        products.add(p9);
        products.add(p10);
        products.add(p11);
        products.add(p12);


        // Insert the products into the database
        productService.saveAll(products);

    }
}