package no.ntnu.idata.gamingeqstore.Repositories;


import no.ntnu.idata.gamingeqstore.Entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
@Component
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
