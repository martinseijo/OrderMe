package orderme.service.mapper;

import orderme.entities.Product;
import orderme.service.dto.ProductDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto productToProductDto(Product entity);
    List<ProductDto> productsToProductDtos(List<Product> entities);

    Product productDtoToProduct(ProductDto dto);
    List<Product> productsDtosToProducts(List<ProductDto> dtos);
}
