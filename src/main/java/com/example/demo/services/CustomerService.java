package com.example.demo.services;


import com.example.demo.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Iterable<Customer> getAllCustomers();

    Customer getCustomerById(int id);

    void save(Customer customer);

    void deleteCustomer(int id);
}
