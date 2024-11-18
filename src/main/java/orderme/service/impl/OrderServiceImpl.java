package orderme.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import orderme.entities.OrderStatus;
import orderme.entities.Tables;
import orderme.repository.OrderRepository;
import orderme.repository.OrderStatusRepository;
import orderme.repository.TablesRepository;
import orderme.service.OrderService;
import orderme.service.dto.OrderDto;
import orderme.service.enums.OrderStatusEnum;
import orderme.service.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final TablesRepository tablesRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDto> getPedingOrdersByTable(Integer tableNumber) {
        Tables table = tablesRepository.findByNumber(tableNumber).orElseThrow(() -> new EntityNotFoundException("Table not found"));
        OrderStatus orderStatus = orderStatusRepository.findByName(OrderStatusEnum.PENDING.getName()).orElseThrow(() -> new EntityNotFoundException("Order status not found"));
        return orderMapper.ordersToOrderDtos(orderRepository.findByTableAndStatus(table, orderStatus));
    }

    @Override
    public long countPendingOrdersByTable(Integer tableNumber) {
        Tables table = tablesRepository.findByNumber(tableNumber).orElseThrow(() -> new EntityNotFoundException("Table not found"));
        OrderStatus orderStatus = orderStatusRepository.findByName(OrderStatusEnum.PENDING.getName()).orElseThrow(() -> new EntityNotFoundException("Order status not found"));
        return orderRepository.countByTableAndStatus(table, orderStatus);
    }
}
