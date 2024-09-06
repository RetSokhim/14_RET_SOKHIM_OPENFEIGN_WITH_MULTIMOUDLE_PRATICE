package org.example.service;

import org.example.model.request.CustomerRequest;
import org.example.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);
    CustomerResponse getCustomerById(Long customerId);
    List<CustomerResponse> getAllCustomer();
    CustomerResponse updateCustomerById(Long customerId, CustomerRequest customerRequest);
    void deleteCustomerById(Long customerId);
}
