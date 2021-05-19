package com.example.demo.controllers;

import com.example.demo.entities.Customer;
import com.example.demo.export.CustomerExcelExport;
import com.example.demo.services.ContractService;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ContractService contractService;

    @GetMapping("/list")
    public String index(Model model) {

        List<Customer> customers = this.customerService.getAllCustomers();

        model.addAttribute("customers", customers);

        model.addAttribute("query", "");
        return "customer/index";
    }

    @GetMapping("/search")
    public String search(Model model,@RequestParam(name = "query", required = false) String query){
        List<Customer> customers = null;
        if(query != null && !query.trim().isEmpty()){
            try {
                customers = customerService.searchCustomer(query);
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            customers = customerService.getAllCustomers();
        }
        model.addAttribute("customers", customers);
        model.addAttribute("query", query);
        return "customer/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute(new Customer());
        return "customer/add";
    }

    @PostMapping("/create")
    public String store(@Valid Customer customer, BindingResult result, Model model){
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

    @GetMapping("/export/excel")
    private void exportToExcel(@RequestParam(name = "query", required = false) String query,
                               HttpServletResponse response ) throws IOException {

        List<Customer> customers = null;

        if(query != null && !query.trim().isEmpty())
            customers = customerService.searchCustomer(query);
        else
            customers = customerService.getAllCustomers();

        response.setContentType("application/octet-stream");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=customers_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        CustomerExcelExport customerExcelExport = new CustomerExcelExport(customers);

        customerExcelExport.export(response);
    }
}
