package orderme.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import orderme.entities.User;
import orderme.repository.TablesRepository;
import orderme.repository.UserRepository;
import orderme.service.ProductService;
import orderme.service.dto.ProductDto;
import orderme.service.dto.PublicRequestDto;
import orderme.service.mapper.ProductMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final UserRepository userRepository;
    private final ProductMapper productMapper;
    private final TablesRepository tablesRepository;

    @Override
    @Transactional
    public List<ProductDto> getProductsByUserName(PublicRequestDto request) {

        final User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return productMapper.productsToProductDtos(user.getProducts().stream().toList());
    }
}
