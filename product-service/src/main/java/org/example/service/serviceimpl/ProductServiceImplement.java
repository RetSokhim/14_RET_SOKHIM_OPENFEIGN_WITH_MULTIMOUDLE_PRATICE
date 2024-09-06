package org.example.service.serviceimpl;

import org.example.model.Product;
import org.example.model.request.ProductRequest;
import org.example.response.ProductResponse;
import org.example.repository.ProductRepository;
import org.example.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplement implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImplement(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productRepository.save(productRequest.toEntity());
        return product.toResponse();
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow().toResponse();
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(Product::toResponse).collect(Collectors.toList());
    }

    @Override
    public ProductResponse updateProductById(Long productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId).orElseThrow().toUpdate(productRequest);
        productRepository.save(product);
        return product.toResponse();
    }

    @Override
    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }
}
