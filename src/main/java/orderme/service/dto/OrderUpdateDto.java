package orderme.service.dto;

import lombok.Data;

@Data
public class OrderUpdateDto {

    private Integer orderId;
    private String status;
}
