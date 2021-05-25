package com.example.demo.api;

import com.example.demo.entities.Customer;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/customers")
public class CustomerApiController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping
    public ResponseEntity<List<Customer>> index(){
        try {
            return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            consumes = { MimeTypeUtils.APPLICATION_JSON_VALUE } )
    public ResponseEntity store(@RequestBody Customer customer){
        try {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE })
    public ResponseEntity<Customer> show(@PathVariable("id") int id){
        try {
            return new ResponseEntity<Customer>(customerService.getCustomerById(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            consumes = { MimeTypeUtils.APPLICATION_JSON_VALUE } )
    public ResponseEntity update(@PathVariable("id") int id, @RequestBody Customer newCustomer){
        try {
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") int id) {
        try {
            customerService.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
