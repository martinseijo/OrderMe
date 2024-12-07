package orderme.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {

    private Integer tableId;
    private List<ProductOrderDto> products;
}
