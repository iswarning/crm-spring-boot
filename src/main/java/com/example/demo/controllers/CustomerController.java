package com.example.demo.controllers;

import com.example.demo.entities.Customer;
import com.example.demo.services.ContractService;
import com.example.demo.services.CustomerService;
import com.example.demo.validator.DateValidator;
import com.example.demo.validator.DateValidatorUsingLocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ContractService contractService;

    @GetMapping("/list")
    public String index(Model model){
        Iterable<Customer> customers = this.customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customer/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute(new Customer());
        return "customer/add";
    }

    @PostMapping("/create")
    public String store(@Valid Customer customer, BindingResult result, Model model){

//        if(result.hasErrors()) return "customer/add";

        this.customerService.save(customer);
        return "redirect:/customers/list";
    }

    @GetMapping("/edit/{id}")
    public String show(Model model, @PathVariable("id") int id){
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("contractsByCustomer", contractService.getAllContractByCustomerId(id));
        model.addAttribute("customer",customer);
        return "customer/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") int id, @Valid Customer customer, BindingResult result){
        if(result.hasErrors()){
            customer.setId(id);
            return "customer/edit";
        }
        customerService.save(customer);
        return "redirect:/customers/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        customerService.deleteCustomer(id);
        return "redirect:/customers/list";
    }
}
