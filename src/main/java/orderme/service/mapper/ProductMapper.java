package orderme.service.mapper;

import orderme.entities.Product;
import orderme.service.dto.ProductDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto productToProductDto(Product user);
    List<ProductDto> productsToProductDtos(List<Product> users);

    Product productDtoToProduct(ProductDto userDto);
    List<Product> productsDtosToProducts(List<ProductDto> userDtos);
}
