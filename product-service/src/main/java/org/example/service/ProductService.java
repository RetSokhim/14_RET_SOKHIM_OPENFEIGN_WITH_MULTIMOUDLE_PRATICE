package org.example.service;

import org.example.model.request.ProductRequest;
import org.example.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse getProductById(Long productId);
    List<ProductResponse> getAllProducts();
    ProductResponse updateProductById(Long customerId, ProductRequest productRequest);
    void deleteProductById(Long productId);
}
