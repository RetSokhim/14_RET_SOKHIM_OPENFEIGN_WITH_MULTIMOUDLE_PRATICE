package org.example.client;

import org.example.response.ApiResponse;
import org.example.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service",url = "http://localhost:8083/api/v1/product/")
public interface ProductServiceClient {
    @GetMapping("get-product/{productId}")
    ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable Long productId);
}
