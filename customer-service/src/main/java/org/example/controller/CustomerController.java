package org.example.controller;

import org.example.model.request.CustomerRequest;
import org.example.response.ApiResponse;
import org.example.response.CustomerResponse;
import org.example.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("create-customer")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.createCustomer(customerRequest);
        return new ResponseEntity<>(ApiResponse.builder()
                .message("Customer saved successfully")
                .status(HttpStatus.CREATED)
                .payload(customerResponse)
                .code(HttpStatus.CREATED.value())
                .time(LocalDateTime.now())
                .build(),HttpStatus.CREATED
        );
    }

    @GetMapping("get-customer/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long customerId){
        CustomerResponse customerResponse = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(ApiResponse.builder()
                .message("Get customer by ID successfully")
                .status(HttpStatus.OK)
                .payload(customerResponse)
                .code(HttpStatus.OK.value())
                .time(LocalDateTime.now())
                .build(),HttpStatus.OK
        );
    }

    @GetMapping("get-customers")
    public ResponseEntity<?> getAllCustomer(){
        List<CustomerResponse> customerResponses = customerService.getAllCustomer();
        return new ResponseEntity<>(ApiResponse.builder()
                .message("Get all customers successfully")
                .status(HttpStatus.OK)
                .payload(customerResponses)
                .code(HttpStatus.OK.value())
                .time(LocalDateTime.now())
                .build(),HttpStatus.OK
        );
    }

    @DeleteMapping("delete-customer/{customerId}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Long customerId){
        customerService.deleteCustomerById(customerId);
        return new ResponseEntity<>(ApiResponse.builder()
                .message("Delete customer by ID successfully")
                .status(HttpStatus.OK)
                .payload(null)
                .code(HttpStatus.OK.value())
                .time(LocalDateTime.now())
                .build(),HttpStatus.OK
        );
    }

    @PutMapping("update-customer/{customerId}")
    public ResponseEntity<?> updateCustomerById(@PathVariable Long customerId,@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.updateCustomerById(customerId,customerRequest);
        return new ResponseEntity<>(ApiResponse.builder()
                .message("Updated customer by ID successfully")
                .status(HttpStatus.OK)
                .payload(customerResponse)
                .code(HttpStatus.OK.value())
                .time(LocalDateTime.now())
                .build(),HttpStatus.OK
        );
    }
}
