package org.example.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProductResponse {
    private Long Id;
    private String name;
    private Double price;
}
