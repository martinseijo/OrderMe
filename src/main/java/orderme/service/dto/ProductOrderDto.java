package orderme.service.dto;

import lombok.Data;

@Data
public class ProductOrderDto {
    private Integer productId;
    private Integer quantity;
    private String observations;
}
