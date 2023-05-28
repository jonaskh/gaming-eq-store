package no.ntnu.idata.gamingeqstore.Repositories;

import no.ntnu.idata.gamingeqstore.Entities.CartProduct;
import no.ntnu.idata.gamingeqstore.Entities.OrderList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderListRepository extends CrudRepository<OrderList, Integer> {
    @Query("SELECT p FROM OrderList p WHERE p.user.id = :userId")
    List<OrderList> findByUserId(@Param("userId") Integer userId);
}
