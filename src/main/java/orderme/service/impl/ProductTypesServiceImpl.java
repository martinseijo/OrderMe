package orderme.service.impl;

import lombok.RequiredArgsConstructor;
import orderme.repository.ProductTypeRepository;
import orderme.service.ProductTypesService;
import orderme.service.dto.ProductTypeDto;
import orderme.service.mapper.ProductTypeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductTypesServiceImpl implements ProductTypesService {

    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductTypeDto> findAll() {
        return productTypeMapper.productTypeListToProductTypeDtos(productTypeRepository.findAll());
    }
}
