package no.ntnu.idata.gamingeqstore.Controllers;

import no.ntnu.idata.gamingeqstore.Entities.Product;
import no.ntnu.idata.gamingeqstore.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/products")
    public List<Product> getAllProducts() {

        Iterable<Product> products = productRepository.findAll();
        List<Product> productList = StreamSupport
                .stream(products.spliterator(), false)
                .collect(Collectors.toList());
        return productList;


        /*
        return Arrays.asList(new Product("Gaming mouse", "Mice", 599, "Product 1.png", "Very nice mouse", 1),
                new Product("Gaming headset", "Headsets", 899, "Product 2.png", "Very nice headset", 1)
                );

         */
    }
}
