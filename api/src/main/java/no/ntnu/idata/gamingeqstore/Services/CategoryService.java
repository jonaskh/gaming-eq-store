package no.ntnu.idata.gamingeqstore.Services;

import no.ntnu.idata.gamingeqstore.Entities.Product;
import no.ntnu.idata.gamingeqstore.Entities.ProductCategory;
import no.ntnu.idata.gamingeqstore.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<ProductCategory> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public ProductCategory save(ProductCategory productCategory) {
        return categoryRepository.save(productCategory);
    }

    public Optional<ProductCategory> findByName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }
    public void addCategories() {
        List<ProductCategory> list = new ArrayList<>();
        list.add(new ProductCategory("Mice"));
        list.add(new ProductCategory("Keyboards"));
        list.add(new ProductCategory("Headsets"));
        list.add(new ProductCategory("Consoles"));
        list.add(new ProductCategory("Controllers"));
        list.add(new ProductCategory("Office"));
        list.add(new ProductCategory("Gaming"));

        categoryRepository.saveAll(list);
    }
}
