package orderme.service;

import orderme.service.dto.OrderDto;
import orderme.service.dto.OrderRequestDto;

import java.util.List;
import java.util.Map;

public interface OrderService {

    List<OrderDto> getPedingOrdersByTable(Integer tableNumber);
    long countPendingOrdersByTable(Integer tableNumber);
    List<OrderDto> createOrder(OrderRequestDto orderRequestDto);
    Map<Integer, Long> countPendingOrdersByUserTables();
}
