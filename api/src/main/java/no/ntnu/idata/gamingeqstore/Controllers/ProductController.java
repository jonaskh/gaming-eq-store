package no.ntnu.idata.gamingeqstore.Controllers;

import no.ntnu.idata.gamingeqstore.Entities.Product;
import no.ntnu.idata.gamingeqstore.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin(origins = "http://frontend:3000")
@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/products")
    public List<Product> getAllProducts() {

        Iterable<Product> products = productRepository.findAll();
        List<Product> productList = StreamSupport
                .stream(products.spliterator(), false)
                .collect(Collectors.toList());
        return productList;
    }

    @GetMapping("/products/random")
    public List<Product> getRandomProducts() {
        Iterable<Product> products = productRepository.findAll();
        List<Product> productList = StreamSupport
                .stream(products.spliterator(), false)
                .collect(Collectors.toList());
        Collections.shuffle(productList);
        return productList.stream().limit(3).collect(Collectors.toList());
    }

    @GetMapping("/products/{productID}")
    public Optional<Product> getSelectedProduct(@PathVariable("productID") Integer productID){

        Optional<Product> product = productRepository.findById(productID);
        return product;
    }
}
