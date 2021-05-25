package com.example.demo.services;

import com.example.demo.entities.Customer;
import com.example.demo.repositories.ContractRepository;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Page<Customer> getCustomerPaginate(Pageable pageable) {
        return customerRepository.findAllCustomer(pageable);
    }

    @Override
    public List<Customer> searchCustomer(String querySearch) {
        return customerRepository.searchCustomer(querySearch);
    }

    @Override
    public Optional<Customer> getCustomerById(int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            return customer;
        } else{
            System.out.println("Not found");
            return null;
        }
    }

    public String getCurrentDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    @Override
    public void save(Customer customer){
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}
