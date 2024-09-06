package org.example.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.Order;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {
    private Long customerId;
    private List<Long> productId;
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private LocalDate orderDate;

    public Order toEntity(){
        return new Order(null,this.customerId,this.productId,this.orderDate);
    }
}
