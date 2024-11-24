package orderme.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import orderme.entities.Order;
import orderme.entities.OrderStatus;
import orderme.entities.Product;
import orderme.entities.Tables;
import orderme.repository.OrderRepository;
import orderme.repository.OrderStatusRepository;
import orderme.repository.ProductRepository;
import orderme.repository.TablesRepository;
import orderme.service.OrderService;
import orderme.service.dto.OrderDto;
import orderme.service.dto.OrderRequestDto;
import orderme.service.enums.OrderStatusEnum;
import orderme.service.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final TablesRepository tablesRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;

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

    @Override
    public List<OrderDto> createOrder(OrderRequestDto orderRequestDto) {
        Tables table = tablesRepository.findById(orderRequestDto.getTableId())
                .orElseThrow(() -> new EntityNotFoundException("Table not found"));

        OrderStatus orderStatus = orderStatusRepository.findByName(OrderStatusEnum.PENDING.getName())
                .orElseThrow(() -> new EntityNotFoundException("Order status not found"));

        List<Order> orders = orderRequestDto.getProducts().entrySet().stream().map(entry -> {
            Integer productId = entry.getKey();
            Integer quantity = entry.getValue();
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));

            return Order.builder()
                    .table(table)
                    .product(product)
                    .quantity(quantity)
                    .observations(orderRequestDto.getObservations())
                    .requestTime(LocalDateTime.now())
                    .status(orderStatus)
                    .build();
        }).collect(Collectors.toList());

        List<Order> savedOrders = orderRepository.saveAll(orders);
        return orderMapper.ordersToOrderDtos(savedOrders);
    }

//    @Override
//    public OrderDto createOrder(OrderRequestDto orderRequestDto) {
//        Tables table = tablesRepository.findByNumber(orderRequestDto.getTableNumber())
//                .orElseThrow(() -> new EntityNotFoundException("Table not found"));
//        Product product = productRepository.findById(orderRequestDto.getProductId())
//                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
//        OrderStatus orderStatus = orderStatusRepository.findByName(OrderStatusEnum.PENDING.getName())
//                .orElseThrow(() -> new EntityNotFoundException("Order status not found"));
//
//        Order order = Order.builder()
//                .table(table)
//                .product(product)
//                .quantity(orderRequestDto.getQuantity())
//                .observations(orderRequestDto.getObservations())
//                .requestTime(LocalDateTime.now())
//                .status(orderStatus)
//                .build();
//
//        Order savedOrder = orderRepository.save(order);
//        return orderMapper.orderToOrderDto(savedOrder);
//    }
}
