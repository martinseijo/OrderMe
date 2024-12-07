package orderme.repository;

import orderme.entities.Order;
import orderme.entities.OrderStatus;
import orderme.entities.Tables;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByTableAndStatus(Tables table, OrderStatus status);
    long countByTableAndStatus(Tables table, OrderStatus status);
}
