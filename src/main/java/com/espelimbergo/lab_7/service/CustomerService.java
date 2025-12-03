package com.espelimbergo.lab_7.service;

import com.espelimbergo.lab_7.entity.Customer;
import com.espelimbergo.lab_7.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get customer by ID
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    // Create new customer
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Update existing customer
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer customer = getCustomerById(id);
        customer.setFname(updatedCustomer.getFname());
        customer.setLname(updatedCustomer.getLname());
        return customerRepository.save(customer);
    }

    // Delete customer
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found");
        }
        customerRepository.deleteById(id);
    }
}
