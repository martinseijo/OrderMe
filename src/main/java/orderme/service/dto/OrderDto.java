package orderme.service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {

    private Integer id;
    private TablesDto table;
    private ProductDto product;
    private Integer quantity;
    private String observations;
    private LocalDateTime requestTime;
    private OrderStatusDto status;
}
