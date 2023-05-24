
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
            new Product("Headset for office and gaming", "Headset", 850, "Product 1.png", "This headset is the perfect all-around choice for those who are looking for\n" +
                    "comfortable, practical sound quality. Its active noise cancellation feature and grey color make it an ideal choice for an office environment. The headset is designed to be comfortable and practical, with a lightweight and ergonomic design that fits snugly on your head. It also has adjustable ear cups and a padded headband for maximum comfort. The active noise cancellation feature ensures that you hear only what you want to hear, blocking out any unwanted distractions. With this headset, you can expect the highest quality of sound for all your audio needs.", 1),
            new Product("Gaming mouse", "Mouse", 700, "Product 2.png", "This gaming mouse is packed with features that make it the perfect tool for any\n" +
                    "gaming session. Its convenient wire location keeps your gaming area clutter-free, while its stylish red light ensures your gaming setup looks great. Comfort and accuracy are guaranteed with its ergonomic design and precision optical tracking. Plus, with its adjustable weight system, you can fine-tune the mouse to your liking. And the best part? The cat isn’t included!", 1),
            new Product("Hot gaming headset", "Headset", 1200, "Product 3.png", "Are you looking for the ultimate gaming headset? Look no further than the Flexible\n" +
                    "Gaming Headset! With top-notch sound quality and stylish lights on the sides, you'll be ready for your next gaming session. Plus, its flexible design is sure to make it comfortable for long hours of gaming. But watch out - it's so hot, your keyboard may melt! Get the Flexible Gaming Headset today and take your gaming to the next level.", 1),
            new Product("Xbox gaming console + controller", "Console", 6800, "Product 4.jpg", "Experience the ultimate gaming experience with the Xbox console!", 1),
            new Product("Xbox controller", "Controller", 499, "Product 5.jpg", "The Xbox controller is the ultimate gaming controller.", 1),
            new Product("4x AA batteries", "Batteries", 80, "Product 6.jpg", "The 4x AA batteries are perfect for your controller!", 1)
        };

        // Insert the products into the database
        productRepository.saveAll(Arrays.asList(products));
    }
}