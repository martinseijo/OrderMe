package orderme.service;

import orderme.service.dto.ProductTypeDto;

import java.util.List;

public interface ProductTypesService {

    List<ProductTypeDto> findAll();
}
