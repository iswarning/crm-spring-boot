package com.example.demo.services;


import com.example.demo.entities.Contract;
import com.example.demo.entities.Juridical;
import com.example.demo.entities.Payment;

import java.util.List;
import java.util.Optional;

public interface ContractService {
    List<Contract> getAllContractByCustomerId(int id);

    Contract getContract(int id);

    void save(Contract contract);

    void deleteContract(int id);
}
