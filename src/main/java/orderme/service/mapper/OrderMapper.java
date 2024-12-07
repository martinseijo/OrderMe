package orderme.service.mapper;

import orderme.entities.Order;
import orderme.service.dto.OrderDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto orderToOrderDto(Order entity);
    List<OrderDto> ordersToOrderDtos(List<Order> entities);

    Order orderDtoToOrder(OrderDto dto);
    List<Order> orderDtosToOrders(List<OrderDto> dtos);
}
