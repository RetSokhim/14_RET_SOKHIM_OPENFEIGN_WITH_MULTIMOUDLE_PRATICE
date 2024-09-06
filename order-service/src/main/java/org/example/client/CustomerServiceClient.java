package org.example.client;

import org.example.response.ApiResponse;
import org.example.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service",url = "http://localhost:8081/api/v1/customer/")
public interface CustomerServiceClient {
    @GetMapping("get-customer/{customerId}")
    ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById(@PathVariable Long customerId);
}
