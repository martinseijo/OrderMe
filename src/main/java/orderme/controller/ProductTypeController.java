package orderme.controller;

import lombok.RequiredArgsConstructor;
import orderme.service.ProductTypesService;
import orderme.service.dto.ProductTypeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/productTypes")
@RequiredArgsConstructor
public class ProductTypeController {

    private final ProductTypesService productTypeService;

    @GetMapping()
    public ResponseEntity<List<ProductTypeDto>> findAll(){
        try {
            List<ProductTypeDto> response = productTypeService.findAll();
            return ResponseEntity.ok(response);
        }catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.emptyList());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }
}
