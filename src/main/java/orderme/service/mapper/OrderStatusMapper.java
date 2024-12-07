package orderme.service.mapper;

import orderme.entities.OrderStatus;
import orderme.service.dto.OrderStatusDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderStatusMapper {

    OrderStatusDto orderStatusToOrderStatusDto(OrderStatus entity);
    List<OrderStatusDto> orderStatusesToOrderStatusDtos(List<OrderStatus> entities);

    OrderStatus orderStatusDtoToOrderStatus(OrderStatusDto dto);
    List<OrderStatus> orderStatusDtosToOrderStatuses(List<OrderStatusDto> dtos);
}
