package orderme.service.dto;

import lombok.Data;

@Data
public class OrderRequestDto {

    private Integer tableNumber;
    private Integer productId;
    private Integer quantity;
    private String observations;
}
