package com.example.demo.controllers;

import com.example.demo.entities.Customer;
import com.example.demo.export.CustomerExcelExport;
import com.example.demo.export.CustomerPdfExport;
import com.example.demo.services.ContractService;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ContractService contractService;

    @GetMapping("/list")
    public String index(Model model,
                        @RequestParam(value = "page",required = false, defaultValue = "1") Integer page,
                        @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
                        @RequestParam(value = "sort", required = false, defaultValue = "ASC") String sort
                        ) {

        Sort sortable = null;

        if(sort.equals("ASC")) sortable = Sort.by("id").ascending();

        if(sort.equals("DESC")) sortable = Sort.by("id").descending();

        assert sortable != null;
        Pageable pageable = PageRequest.of(page, size, sortable);

        model.addAttribute("customers", customerService.getCustomerPaginate(pageable));

        model.addAttribute("query", "");
        return "customer/index";
    }

    @GetMapping("/search")
    public String search(Model model,@RequestParam(name = "query", required = false) String query){
        List<Customer> customers = null;
        if(StringUtils.hasText(query)){
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
    public String show(Model model, @PathVariable("id") String id){
        int parseStringToInt = 0;
        Customer customer;

        try {
            parseStringToInt = Integer.parseInt(id);
            customer = customerService.getCustomerById(parseStringToInt).get();
        } catch (Exception e){
            e.printStackTrace();
            model.addAttribute("message", "Not found customer with id: " + id);
            return "404";
        }

        model.addAttribute("contractsByCustomer", contractService.getAllContractByCustomerId(parseStringToInt));
        model.addAttribute("customer",customer);
        return "customer/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        customerService.deleteCustomer(id);
        return "redirect:/customers/list";
    }

    @GetMapping("/export/excel")
    public void exportToExcel(@RequestParam(name = "query", required = false) String query,
                               HttpServletResponse response ) throws IOException {

        List<Customer> customers;

        if(StringUtils.hasText(query))
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

    @GetMapping("/export/pdf")
    public void exportToPdf(@RequestParam(name = "query", required = false) String query,
                                HttpServletResponse response ) throws IOException {

        List<Customer> customers;

        if(StringUtils.hasText(query))
            customers = customerService.searchCustomer(query);
        else
            customers = customerService.getAllCustomers();

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=customers_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        CustomerPdfExport customerPdfExport = new CustomerPdfExport(customers);

        customerPdfExport.export(response);
    }
}
