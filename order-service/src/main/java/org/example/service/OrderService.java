package org.example.service;

import org.example.model.request.OrderRequest;
import org.example.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
    OrderResponse getOrderById(Long orderId);
    List<OrderResponse> getAllOrders();
    void deleteOrderById(Long orderId);
    OrderResponse updateOrderById(OrderRequest orderRequest, Long orderId);
}
