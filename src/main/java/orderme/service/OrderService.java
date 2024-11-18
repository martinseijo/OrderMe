package orderme.service;

import orderme.service.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> getPedingOrdersByTable(Integer tableNumber);
    long countPendingOrdersByTable(Integer tableNumber);
}
