package orderme.service.dto;

import lombok.Data;

import java.util.Map;

@Data
public class OrderRequestDto {

    private Integer tableId;
    private Map<Integer, Integer> products;
    private String observations;
}
