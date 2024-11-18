package orderme.service.mapper;

import orderme.entities.ProductType;
import orderme.service.dto.ProductTypeDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductTypeMapper {

    ProductTypeDto productTypeToProductTypeDto(ProductType entity);
    List<ProductTypeDto> productTypeListToProductTypeDtos(List<ProductType> entities);

    ProductType productTypeDtoToProductType(ProductTypeDto dto);
    List<ProductType> productTypeDtosListToProductTypeList(List<ProductTypeDto> dtos);
}
