package orderme.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import orderme.service.OrderService;
import orderme.service.dto.OrderDto;
import orderme.service.dto.OrderRequestDto;
import orderme.service.dto.OrderUpdateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/pending/{tableNumber}")
    public ResponseEntity<List<OrderDto>> getPendingOrdersByTable(@PathVariable Integer tableNumber) {
        try {
            List<OrderDto> response = orderService.getPedingOrdersByTable(tableNumber);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.emptyList());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }

    @GetMapping("/pending/count/{tableNumber}")
    public ResponseEntity<Long> countPendingOrdersByTable(@PathVariable Integer tableNumber) {
        try {
            long count = orderService.countPendingOrdersByTable(tableNumber);
            return ResponseEntity.ok(count);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(0L);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(0L);
        }
    }

    @GetMapping("/served/{userName}/{tableNumber}")
    public ResponseEntity<List<OrderDto>> servedOrdersByTable(@PathVariable String userName, @PathVariable Integer tableNumber) {
        try {
            List<OrderDto> response = orderService.getServedOrdersByTable(userName, tableNumber);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.emptyList());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }

    @GetMapping("/pending/count")
    public ResponseEntity<Map<Integer, Long>> countPendingOrdersByUserTables() {
        try {
            Map<Integer, Long> result = orderService.countPendingOrdersByUserTables();
            return ResponseEntity.ok(result);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.emptyMap());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyMap());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<List<OrderDto>> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        try {
            List<OrderDto> createdOrder = orderService.createOrder(orderRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Void> changeStatus(@RequestBody OrderUpdateDto orderUpdateDto) {
        try {
            orderService.changeStatus(orderUpdateDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/public/paid")
    public ResponseEntity<Void> publicPaid(@RequestBody List<Integer> ordersToPaid) {
        try {
            orderService.changeToPaid(ordersToPaid);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
