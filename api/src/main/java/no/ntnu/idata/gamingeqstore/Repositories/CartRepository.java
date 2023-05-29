package no.ntnu.idata.gamingeqstore.Repositories;

import no.ntnu.idata.gamingeqstore.Entities.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
    @Override
    Optional<Cart> findById(Integer id);
}
