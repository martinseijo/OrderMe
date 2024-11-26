package orderme.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import orderme.entities.*;
import orderme.repository.*;
import orderme.service.OrderService;
import orderme.service.UserService;
import orderme.service.dto.OrderDto;
import orderme.service.dto.OrderRequestDto;
import orderme.service.dto.OrderUpdateDto;
import orderme.service.enums.OrderStatusEnum;
import orderme.service.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final TablesRepository tablesRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public List<OrderDto> getPedingOrdersByTable(Integer tableNumber) {
        User user = userRepository.findByUsername(userService.getAuthenticatedUsername()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Tables table = tablesRepository.findByUserIdAndTableNumber(user.getId(), tableNumber).orElseThrow(() -> new EntityNotFoundException("Table not found"));
        OrderStatus orderStatus = orderStatusRepository.findByName(OrderStatusEnum.PENDING.getName()).orElseThrow(() -> new EntityNotFoundException("Order status not found"));
        return orderMapper.ordersToOrderDtos(orderRepository.findByTableAndStatus(table, orderStatus));
    }

    @Override
    public long countPendingOrdersByTable(Integer tableNumber) {
        User user = userRepository.findByUsername(userService.getAuthenticatedUsername()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Tables table = tablesRepository.findByUserIdAndTableNumber(user.getId(), tableNumber).orElseThrow(() -> new EntityNotFoundException("Table not found"));
        OrderStatus orderStatus = orderStatusRepository.findByName(OrderStatusEnum.PENDING.getName()).orElseThrow(() -> new EntityNotFoundException("Order status not found"));
        return orderRepository.countByTableAndStatus(table, orderStatus);
    }

    @Override
    public Map<Integer, Long> countPendingOrdersByUserTables() {
        User user = userRepository.findByUsername(userService.getAuthenticatedUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        OrderStatus orderStatus = orderStatusRepository.findByName(OrderStatusEnum.PENDING.getName())
                .orElseThrow(() -> new EntityNotFoundException("Order status not found"));

        return user.getTables().stream()
                .collect(Collectors.toMap(
                        Tables::getNumber,
                        table -> orderRepository.countByTableAndStatus(table, orderStatus)
                ));
    }

    @Override
    public void changeStatus(OrderUpdateDto orderUpdateDto) {
        Order order = orderRepository.findById(orderUpdateDto.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        OrderStatus orderStatus = orderStatusRepository.findByName(orderUpdateDto.getStatus())
                .orElseThrow(() -> new EntityNotFoundException("Order status not found"));

        order.setStatus(orderStatus);
        orderRepository.save(order);
    }

    @Override
    public List<OrderDto> createOrder(OrderRequestDto orderRequestDto) {
        Tables table = tablesRepository.findById(orderRequestDto.getTableId())
                .orElseThrow(() -> new EntityNotFoundException("Table not found"));

        OrderStatus orderStatus = orderStatusRepository.findByName(OrderStatusEnum.PENDING.getName())
                .orElseThrow(() -> new EntityNotFoundException("Order status not found"));

        List<Order> orders = orderRequestDto.getProducts().stream().map(productOrderDto -> {
            Product product = productRepository.findById(productOrderDto.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));

            return Order.builder()
                    .table(table)
                    .product(product)
                    .quantity(productOrderDto.getQuantity())
                    .observations(productOrderDto.getObservations())
                    .requestTime(LocalDateTime.now())
                    .status(orderStatus)
                    .build();
        }).collect(Collectors.toList());

        List<Order> savedOrders = orderRepository.saveAll(orders);
        return orderMapper.ordersToOrderDtos(savedOrders);
    }
}
