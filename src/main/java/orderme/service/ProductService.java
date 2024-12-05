package orderme.service;

import orderme.service.dto.ProductDto;
import orderme.service.dto.PublicRequestDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getProductsByUserName(PublicRequestDto request);
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(Integer productId, ProductDto productDto);
    void deleteProduct(Integer productId);
}
