package no.ntnu.idata.gamingeqstore.Repositories;

import no.ntnu.idata.gamingeqstore.Entities.CartProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartProductRepository extends CrudRepository<CartProduct, Integer> {
    @Query("SELECT p FROM CartProduct p WHERE p.cart.cartID = :cartId")
    List<CartProduct> findByCartId(@Param("cartId") Integer cartId);

    @Override
    void delete(CartProduct entity);
}
