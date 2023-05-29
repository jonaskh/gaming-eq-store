package no.ntnu.idata.gamingeqstore.Services;


import jakarta.transaction.Transactional;
import no.ntnu.idata.gamingeqstore.Entities.Product;
import no.ntnu.idata.gamingeqstore.Entities.ProductCategory;
import no.ntnu.idata.gamingeqstore.Exceptions.ProductNotFoundException;
import no.ntnu.idata.gamingeqstore.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public List<Product> listAll() {
        return(List<Product>) productRepository.findAll();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public Product get(Integer id) {
        Optional<Product> result = productRepository.findById(id);

        return result.orElse(null);
        }

    public void delete(Integer id) throws ProductNotFoundException {
        Optional<Product> count = productRepository.findById(id);
        if (count.isEmpty()) {
            throw new NullPointerException("Could not find any users with ID " + id);
        } else {
            productRepository.deleteById(id);
        }
    }

    //add one or more of the categories to the product
    public void addCategory(Product product, String categoryName) {
        Optional<ProductCategory> cat = categoryService.findByName(categoryName);
        if (cat.isPresent()) {
            product.addCategory(cat.get());
        } else {
            ProductCategory newCategory = new ProductCategory(categoryName);
            product.addCategory(categoryService.save(newCategory));
        }
    }

    public List<Product> findProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    public long count() {
        return productRepository.count();
    }

    @Transactional
    public void increaseAmount(int productId, int amount) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            int update = product.getProductAmount() + amount;
            product.setProductAmount(update);
            productRepository.save(product);
        }
    }

    public Optional<Product> findByName(String name) {
        return productRepository.findByProductName(name);
    }

    @Transactional
    public void decreaseAmount(int productId, int amount) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (product.getProductAmount() <= 0) throw new ProductNotFoundException("Product out of stock");

            int update = product.getProductAmount() - amount;
            product.setProductAmount(update);
            productRepository.save(product);
        }
    }

    @Transactional
    public void saveAll(List<Product> products) {
        productRepository.saveAll(products);
    }
}
