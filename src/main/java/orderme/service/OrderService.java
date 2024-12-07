package orderme.service;

import orderme.service.dto.OrderDto;
import orderme.service.dto.OrderRequestDto;
import orderme.service.dto.OrderUpdateDto;

import java.util.List;
import java.util.Map;

public interface OrderService {

    List<OrderDto> getPedingOrdersByTable(Integer tableNumber);
    List<OrderDto> getServedOrdersByTable(String userName, Integer tableNumber);
    long countPendingOrdersByTable(Integer tableNumber);
    List<OrderDto> createOrder(OrderRequestDto orderRequestDto);
    Map<Integer, Long> countPendingOrdersByUserTables();
    void changeStatus(OrderUpdateDto orderUpdateDto);
    void changeToPaid(List<Integer> ordersToPaid);
}
