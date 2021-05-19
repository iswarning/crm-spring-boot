package com.example.demo.repositories;

import com.example.demo.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT c FROM customers c WHERE c.name LIKE %:query%")
    List<Customer> searchCustomer(@Param("query") String querySearch);
}
