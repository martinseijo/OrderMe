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
import org.hibernate.Hibernate;
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
        List<ProductDto> productDtos = productMapper.productsToProductDtos(user.getProducts().stream().toList());
        Hibernate.initialize(user.getProducts());
        Hibernate.initialize(user.getTables());
        List<User> users = new java.util.ArrayList<>(List.of());
        tablesRepository.findAll().forEach(table -> table.getUsers().forEach(userTable -> users.add(user)));


        productDtos.addAll(productMapper.productsToProductDtos(user.getProducts().stream().toList()));
        return productDtos;
    }
}
