package org.example.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.Customer;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomerRequest {
    private String name;
    private String email;

    public Customer toEntity(){
        return new Customer(null,this.name,this.email);
    }
}
