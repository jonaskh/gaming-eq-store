package no.ntnu.idata.gamingeqstore.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import no.ntnu.idata.gamingeqstore.Entities.Product;
import no.ntnu.idata.gamingeqstore.Exceptions.ProductNotFoundException;
import no.ntnu.idata.gamingeqstore.Repositories.ProductRepository;
import no.ntnu.idata.gamingeqstore.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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



    @Operation(summary = "Returns a list of all products", description = "Returns a list of all the products in the database. Can be sorted into categories and search by name")
    @GetMapping("/products")
    public List<Product> getAllProducts() {

        Iterable<Product> products = productRepository.findAll();
        List<Product> productList = StreamSupport
                .stream(products.spliterator(), false)
                .collect(Collectors.toList());
        return productList;
    }

    @Operation(summary = "Return a list of random products", description = "Return a list of 'featured' products taken randomly from the database")
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
    @Operation(summary = "Return a list of random products to use in single product page", description = "Return a list of similar products taken  from the database on the single product page")
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
    @Operation(summary = "Return one product", description = "Return one product by given product id")
    @GetMapping("/products/{productID}")
    public Optional<Product> getSelectedProduct(@PathVariable("productID") Integer productID){

        Optional<Product> product = productRepository.findById(productID);
        return product;
    }


    @Operation(summary = "Deletes one product", description = "Deletes a product by the given product id")
    @DeleteMapping ("/products/delete/{productID}")
    public void deleteSelectedProduct(@PathVariable("productID") Integer productID) throws ProductNotFoundException {
        productService.delete(productID);
    }

    @Operation(summary = "Updates one product", description = "Updates a product price and/or name by the given product id")
    @PutMapping("/products/update/{productID}/{productName}/{productPrice}")
    public void updateSelectedProduct(@PathVariable("productID") Integer productID, @PathVariable("productName") String productName, @PathVariable("productPrice") Integer productPrice) throws ProductNotFoundException {
        Product updatedProduct = productService.get(productID);
        System.out.println(updatedProduct.toString());
        updatedProduct.setProductName(productName);
        updatedProduct.setPrice(productPrice);
        productService.save(updatedProduct);
        System.out.println(productService.get(updatedProduct.getProduct_id()).toString());
    }

    @PostMapping("/products/create/{productName}/{productPrice}/{productDesc}/{productCategory}")
    public void createNewProduct(@PathVariable("productName") String productName,
                                 @PathVariable("productPrice") Integer productPrice,
                                 @PathVariable("productDesc") String productDesc,
                                 @PathVariable("productCategory") String productCategory) {
        Product newProduct = new Product();

        if (productCategory == "Mouse"){
            newProduct.setProductImage("Product 2.png");
        } else if (productCategory == "Headsets") {
            newProduct.setProductImage("Product 1.png");
        } else {
            newProduct.setProductImage("Product 3.png");
        }

        newProduct.setProductName(productName);
        newProduct.setPrice(productPrice);
        newProduct.setProductDesc(productDesc);
        productService.addCategory(newProduct, productCategory);
        productService.save(newProduct);
    }

    @Operation(summary = "Return a list of products by category", description = "Return all products with the given category")
    @GetMapping("/products/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable("category") String category){

        List<Product> products = productService.findProductsByCategory(category);
        return products;

    }
}
