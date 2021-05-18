package com.example.demo.api;

import com.example.demo.entities.Contract;
import com.example.demo.entities.Customer;
import com.example.demo.services.ContractService;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/contracts")
public class ContractApiController {

    @Autowired
    private ContractService contractService;

//    @RequestMapping
//    public ResponseEntity<List<Contract>> getAllContractByCustomerId(int id){
//        try{
//            return new ResponseEntity<>(contractService.getAllContractByCustomerId(), HttpStatus.OK);
//        } catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

}
