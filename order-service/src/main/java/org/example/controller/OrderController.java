package org.example.controller;

import org.example.model.Order;
import org.example.model.request.OrderRequest;
import org.example.response.ApiResponse;
import org.example.response.CustomerResponse;
import org.example.response.OrderResponse;
import org.example.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("create-order")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest){
        OrderResponse customerResponse = orderService.createOrder(orderRequest);
        return new ResponseEntity<>(ApiResponse.builder()
                .message("Order saved successfully")
                .status(HttpStatus.CREATED)
                .payload(customerResponse)
                .code(HttpStatus.CREATED.value())
                .time(LocalDateTime.now())
                .build(),HttpStatus.CREATED
        );
    }

    @GetMapping("get-order/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId){
        OrderResponse orderResponse = orderService.getOrderById(orderId);
        return new ResponseEntity<>(ApiResponse.builder()
                .message("Get order by ID successfully")
                .status(HttpStatus.OK)
                .payload(orderResponse)
                .code(HttpStatus.OK.value())
                .time(LocalDateTime.now())
                .build(),HttpStatus.OK
        );
    }

    @GetMapping("get-orders")
    public ResponseEntity<?> getAllOrders(){
        List<OrderResponse> orderResponses = orderService.getAllOrders();
        return new ResponseEntity<>(ApiResponse.builder()
                .message("Get all orders successfully")
                .status(HttpStatus.OK)
                .payload(orderResponses)
                .code(HttpStatus.OK.value())
                .time(LocalDateTime.now())
                .build(),HttpStatus.OK
        );
    }

    @DeleteMapping("delete-order/{orderId}")
    public ResponseEntity<?> deleteOrderById(@PathVariable Long orderId){
        orderService.deleteOrderById(orderId);
        return new ResponseEntity<>(ApiResponse.builder()
                .message("Delete order by ID successfully")
                .status(HttpStatus.OK)
                .payload(null)
                .code(HttpStatus.OK.value())
                .time(LocalDateTime.now())
                .build(),HttpStatus.OK
        );
    }

    @PutMapping("update-order/{orderId}")
    public ResponseEntity<?> updateOrderById(@RequestBody OrderRequest orderRequest,@PathVariable Long orderId){
        OrderResponse orderResponse = orderService.updateOrderById(orderRequest,orderId);
        return new ResponseEntity<>(ApiResponse.builder()
                .message("Updated order by ID successfully")
                .status(HttpStatus.OK)
                .payload(orderResponse)
                .code(HttpStatus.OK.value())
                .time(LocalDateTime.now())
                .build(),HttpStatus.OK
        );
    }
}
