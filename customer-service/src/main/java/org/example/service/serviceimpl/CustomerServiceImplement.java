package org.example.service.serviceimpl;

import org.example.model.Customer;
import org.example.model.request.CustomerRequest;
import org.example.response.CustomerResponse;
import org.example.repository.CustomerRepository;
import org.example.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImplement implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImplement(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = customerRepository.save(customerRequest.toEntity());
        return customer.toResponse();
    }

    @Override
    public CustomerResponse getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow().toResponse();
    }

    @Override
    public List<CustomerResponse> getAllCustomer() {
        return customerRepository.findAll().stream()
                .map(Customer::toResponse).collect(Collectors.toList());
    }

    @Override
    public CustomerResponse updateCustomerById(Long customerId, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(customerId).orElseThrow().toUpdate(customerRequest);
        customerRepository.save(customer);
        return customer.toResponse();
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
