package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.request.ProductRequest;
import org.example.response.ProductResponse;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long Id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;

    public ProductResponse toResponse() {
        return new ProductResponse(this.Id,this.name,this.price);
    }

    public Product toUpdate(ProductRequest productRequest) {
        return new Product(this.Id, productRequest.getName(), productRequest.getPrice());
    }
}
