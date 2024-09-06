package org.example.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.Product;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductRequest {
    private String name;
    private Double price;

    public Product toEntity(){
        return new Product(null,this.name,this.price);
    }
}
