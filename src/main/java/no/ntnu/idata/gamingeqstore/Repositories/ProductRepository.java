package no.ntnu.idata.gamingeqstore.Repositories;


import no.ntnu.idata.gamingeqstore.Entities.Product;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Integer countByid(Integer id);


    @Query(value = "SELECT * FROM products WHERE product_name = ?", nativeQuery = true)
    Optional<Product> findAllByProductName(String name);

    @Query(value = "SELECT * FROM products WHERE product_category = ?", nativeQuery = true)
    Product findAllByProductCategory(String category);
}

=======
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
@Component
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
>>>>>>> 3a7bd0e5d51f7fd913a71d84efb9576bedf043fc
