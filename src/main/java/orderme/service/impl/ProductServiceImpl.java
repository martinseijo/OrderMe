package orderme.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import orderme.entities.Product;
import orderme.entities.ProductType;
import orderme.entities.User;
import orderme.repository.ProductRepository;
import orderme.repository.ProductTypeRepository;
import orderme.repository.TablesRepository;
import orderme.repository.UserRepository;
import orderme.service.ProductService;
import orderme.service.UserService;
import orderme.service.dto.ProductDto;
import orderme.service.dto.PublicRequestDto;
import orderme.service.mapper.ProductMapper;
import orderme.service.mapper.ProductTypeMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final UserRepository userRepository;
    private final ProductMapper productMapper;
    private final TablesRepository tablesRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final ProductTypeMapper productTypeMapper;
    private final ProductTypeRepository productTypeRepository;

    @Override
    @Transactional
    public List<ProductDto> getProductsByUserName(PublicRequestDto request) {

        final User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return productMapper.productsToProductDtos(productRepository.findByUserAndEnabledTrue(user).stream().toList());
    }

    @Override
    public List<ProductDto> getProductsByUser() {
        User user = userRepository.findByUsername(userService.getAuthenticatedUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return productMapper.productsToProductDtos(productRepository.findByUserAndEnabledTrue(user).stream().toList());
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        User user = userRepository.findByUsername(userService.getAuthenticatedUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Product product = productMapper.productDtoToProduct(productDto);
        ProductType productType = productTypeRepository.getReferenceById(productDto.getProductType().getId());
        product.setProductType(productType);
        product.setUser(user);
        product.setEnabled(true);
        Product savedProduct = productRepository.save(product);
        return productMapper.productToProductDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(Integer productId, ProductDto productDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(BigDecimal.valueOf(productDto.getPrice()));
        ProductType productType = productTypeRepository.getReferenceById(productDto.getProductType().getId());
        product.setProductType(productType);
        Product updatedProduct = productRepository.save(product);
        return productMapper.productToProductDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        product.setEnabled(false);
        productRepository.save(product);
    }
}
