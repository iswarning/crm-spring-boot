package com.example.demo.services;

import com.example.demo.entities.Contract;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Juridical;
import com.example.demo.entities.Payment;
import com.example.demo.repositories.ContractRepository;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.JuridicalRepository;
import com.example.demo.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService{

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private JuridicalRepository juridicalRepository;

    @Override
    public List<Contract> getAllContractByCustomerId(int customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        return customer.getContracts();
    }

    @Override
    public Contract getContract(int id) {
        return contractRepository.findById(id).get();
    }

    @Override
    public void save(Contract contract){
        contractRepository.save(contract);
    }

    @Override
    public void deleteContract(int id) {
        contractRepository.deleteById(id);
    }
}
