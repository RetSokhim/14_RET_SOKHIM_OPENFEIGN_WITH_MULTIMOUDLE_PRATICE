package org.example.service.serviceimpl;

import org.example.client.CustomerServiceClient;
import org.example.client.ProductServiceClient;
import org.example.model.Order;
import org.example.model.request.OrderRequest;
import org.example.repository.OrderRepository;
import org.example.response.CustomerResponse;
import org.example.response.OrderResponse;
import org.example.response.ProductResponse;
import org.example.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductServiceClient productServiceClient;
    private final CustomerServiceClient customerServiceClient;

    public OrderServiceImpl(OrderRepository orderRepository, ProductServiceClient productServiceClient, CustomerServiceClient customerServiceClient) {
        this.orderRepository = orderRepository;
        this.productServiceClient = productServiceClient;
        this.customerServiceClient = customerServiceClient;
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = orderRequest.toEntity();
        CustomerResponse customerResponse = Objects.requireNonNull(customerServiceClient.getCustomerById(orderRequest.getCustomerId()).getBody()).getPayload();
        List<ProductResponse> productResponses = orderRequest.getProductId().stream()
                .map(productId -> Objects.requireNonNull(productServiceClient.getProductById(productId).getBody()).getPayload()
                ).toList();
        orderRepository.save(order);
        return  new OrderResponse(order.getOrderId(),customerResponse,productResponses);
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        CustomerResponse customerResponse = Objects.requireNonNull(customerServiceClient.getCustomerById(order.getCustomerId()).getBody()).getPayload();
        List<ProductResponse> productResponses = order.getProductId()
                .stream().map(productId -> Objects.requireNonNull(productServiceClient.getProductById(productId).getBody()).getPayload()).toList();
        return new OrderResponse(orderId,customerResponse,productResponses);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> {
                    OrderResponse orderResponse = order.toResponse();
                    CustomerResponse customerResponse = Objects.requireNonNull(customerServiceClient.getCustomerById(order.getCustomerId()).getBody()).getPayload();
                    List<ProductResponse> productResponses = order.getProductId()
                            .stream().map(productId -> Objects.requireNonNull(productServiceClient.getProductById(productId).getBody()).getPayload()).toList();
                    orderResponse.setProductResponses(productResponses);
                    orderResponse.setCustomerResponse(customerResponse);
                    orderResponse.setOrderId(order.getOrderId());
                    return orderResponse;
                }).toList();
    }

    @Override
    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public OrderResponse updateOrderById(OrderRequest orderRequest, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setOrderDate(orderRequest.getOrderDate());
        order.setCustomerId(orderRequest.getCustomerId());
        order.setProductId(orderRequest.getProductId());
        orderRepository.save(order);
        CustomerResponse customerResponse = Objects.requireNonNull(
                customerServiceClient.getCustomerById(order.getCustomerId()).getBody()
        ).getPayload();
        List<ProductResponse> productResponses = order.getProductId().stream()
                .map(productId -> Objects.requireNonNull(productServiceClient.getProductById(productId).getBody()).getPayload())
                .toList();
        return new OrderResponse(orderId, customerResponse, productResponses);
    }

}
