package no.ntnu.idata.gamingeqstore.Repositories;

import no.ntnu.idata.gamingeqstore.Entities.OrderProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends CrudRepository<OrderProduct, Integer> {
}
