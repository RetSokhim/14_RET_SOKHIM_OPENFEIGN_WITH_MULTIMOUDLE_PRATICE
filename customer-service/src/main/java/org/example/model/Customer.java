package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.request.CustomerRequest;
import org.example.response.CustomerResponse;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "customer_tb")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long Id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true,nullable = false)
    private String email;

    public CustomerResponse toResponse() {
        return new CustomerResponse(this.Id,this.name,this.email);
    }

    public Customer toUpdate(CustomerRequest customerRequest) {
        return new Customer(this.Id, customerRequest.getName(), customerRequest.getEmail());
    }
}
