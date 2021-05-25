package com.example.demo.services;


import com.example.demo.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Page<Customer> getCustomerPaginate(Pageable pageable);

    List<Customer> searchCustomer(String querySearch);

    Optional<Customer> getCustomerById(int id);

    void save(Customer customer);

    void deleteCustomer(int id);
}
