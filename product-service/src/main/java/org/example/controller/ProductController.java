package org.example.controller;

import org.example.model.request.ProductRequest;
import org.example.response.ApiResponse;
import org.example.response.ProductResponse;
import org.example.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("create-product")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productService.createProduct(productRequest);
        return new ResponseEntity<>(ApiResponse.builder()
                .message("Product saved successfully")
                .status(HttpStatus.CREATED)
                .payload(productResponse)
                .code(HttpStatus.CREATED.value())
                .time(LocalDateTime.now())
                .build(),HttpStatus.CREATED
        );
    }

    @GetMapping("get-product/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId){
        ProductResponse productResponse = productService.getProductById(productId);
        return new ResponseEntity<>(ApiResponse.builder()
                .message("Get Product by ID successfully")
                .status(HttpStatus.OK)
                .payload(productResponse)
                .code(HttpStatus.OK.value())
                .time(LocalDateTime.now())
                .build(),HttpStatus.OK
        );
    }

    @GetMapping("get-products")
    public ResponseEntity<?> getAllProducts(){
        List<ProductResponse> productResponses = productService.getAllProducts();
        return new ResponseEntity<>(ApiResponse.builder()
                .message("Get all products successfully")
                .status(HttpStatus.OK)
                .payload(productResponses)
                .code(HttpStatus.OK.value())
                .time(LocalDateTime.now())
                .build(),HttpStatus.OK
        );
    }

    @DeleteMapping("delete-product/{productId}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long productId){
        productService.deleteProductById(productId);
        return new ResponseEntity<>(ApiResponse.builder()
                .message("Delete product by ID successfully")
                .status(HttpStatus.OK)
                .payload(null)
                .code(HttpStatus.OK.value())
                .time(LocalDateTime.now())
                .build(),HttpStatus.OK
        );
    }

    @PutMapping("update-product/{productId}")
    public ResponseEntity<?> updateProductById(@PathVariable Long productId,@RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productService.updateProductById(productId,productRequest);
        return new ResponseEntity<>(ApiResponse.builder()
                .message("Updated product by ID successfully")
                .status(HttpStatus.OK)
                .payload(productResponse)
                .code(HttpStatus.OK.value())
                .time(LocalDateTime.now())
                .build(),HttpStatus.OK
        );
    }
}
