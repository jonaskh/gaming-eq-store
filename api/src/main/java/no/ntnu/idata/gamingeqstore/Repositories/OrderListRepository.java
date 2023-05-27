package no.ntnu.idata.gamingeqstore.Repositories;

import no.ntnu.idata.gamingeqstore.Entities.OrderList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderListRepository extends CrudRepository<OrderList, Integer> {
}
