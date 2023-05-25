package no.ntnu.idata.gamingeqstore.Controllers;

import no.ntnu.idata.gamingeqstore.Entities.Product;
import no.ntnu.idata.gamingeqstore.Repositories.ProductRepository;
import no.ntnu.idata.gamingeqstore.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;


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

    /**
     * Get a set of 3 random products, excluding the product that is currently shown on the productPage
     * @param productID The ID of the currently shown product on the productPage
     * @return List of 3 random products.
     */
    @GetMapping("/products/random/{productID}")
    public List<Product> getMoreRandomProducts(@PathVariable("productID") Integer productID) {
        Iterable<Product> products = productRepository.findAll();
        List<Product> productList = StreamSupport
                .stream(products.spliterator(), false)
                .collect(Collectors.toList());
        productList.remove(productRepository.findById(productID).get());
        Collections.shuffle(productList);
        return productList.stream().limit(3).collect(Collectors.toList());
    }

    @GetMapping("/products/{productID}")
    public Optional<Product> getSelectedProduct(@PathVariable("productID") Integer productID){

        Optional<Product> product = productRepository.findById(productID);
        return product;
    }

    @GetMapping("/products/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable("category") String category){

        List<Product> products = productService.findProductsByCategory(category);
        return products;
    }
}
