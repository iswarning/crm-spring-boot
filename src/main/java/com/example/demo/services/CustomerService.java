package com.example.demo.services;


import com.example.demo.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getAllCustomers();

    List<Customer> searchCustomer(String querySearch);

    Customer getCustomerById(int id);

    void save(Customer customer);

    void deleteCustomer(int id);
}
