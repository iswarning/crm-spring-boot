package com.example.demo.controllers;

import com.example.demo.entities.Contract;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Juridical;
import com.example.demo.entities.Payment;
import com.example.demo.enums.BookHolderEnum;
import com.example.demo.enums.ContractInfoEnum;
import com.example.demo.enums.StatusCreatedEnum;
import com.example.demo.enums.StatusEnum;
import com.example.demo.repositories.JuridicalRepository;
import com.example.demo.repositories.PaymentRepository;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.services.ContractService;

import com.example.demo.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/customers/edit/{customerId}/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private JuridicalRepository juridicalRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProjectRepository projectRepository;

    private static Logger logger = LoggerFactory.getLogger(Contract.class);

    @GetMapping("/create")
    public String create(@PathVariable("customerId") int customerId,
                         Model model,
                         HttpServletRequest request ){
        model.addAttribute("contract",new Contract());
        model.addAttribute("juridical", new Juridical());
        this.modelDuplicate(model, customerId);
        return "contract/add";
    }

    @PostMapping("/create")
    public String store(@ModelAttribute("contract") @Valid Contract contract,
                        BindingResult result,
                        @PathVariable("customerId") int customerId,
                        Model model,
                        HttpServletRequest request){

        if(result.hasErrors()){
            this.modelDuplicate(model, customerId);
            model.addAttribute("currentUrl", request.getRequestURL().toString());
            return "contract/add";
        }

        Customer customer = customerService.getCustomerById(customerId).get();
        contract.setCustomer(customer);
        contractService.save(contract);

        // update contract_id for payment
        Payment payment = contract.getPayment();
        payment.setContract(contract);
        paymentRepository.save(payment);

        // update contract_id for juridical
        Juridical juridical = contract.getJuridical();
        juridical.setContract(contract);
        juridicalRepository.save(juridical);

        return "redirect:/customers/edit/" + customerId;
    }

    @GetMapping("/edit/{contractId}")
    public String edit(Model model,
                       @PathVariable("customerId") int customerId,
                       @PathVariable("contractId") int contractId ){

        Contract contract = contractService.getContract(contractId);
        Payment payment = contract.getPayment();
        Juridical juridical = contract.getJuridical();

        model.addAttribute("contract", contract);
        model.addAttribute("payment", payment);
        model.addAttribute("juridical", juridical);

        model.addAttribute("contractId", contractId);

        this.modelDuplicate(model, customerId);

        return "contract/edit";
    }

    private void modelDuplicate(Model model,
                                int customerId ){

        model.addAttribute("projects", projectRepository.findAll());
        model.addAttribute("statusCreated", new StatusCreatedEnum().getList());
        model.addAttribute("status", new StatusEnum().getList());
        model.addAttribute("bookHolder", new BookHolderEnum().getList());
        model.addAttribute("contractInfo", new ContractInfoEnum().getList());
        model.addAttribute("customerId", customerId);
    }

    @PostMapping("/update/{contractId}")
//    @ResponseBody
    public String update(@PathVariable("contractId") int contractId,
                         @PathVariable("customerId") int customerId,
                         @Valid Contract newContract,
                         BindingResult result,
                         Model model ,
                         HttpServletRequest request) {

        if(result.hasErrors()){
            Contract existContract = contractService.getContract(contractId);
            newContract.setId(existContract.getId());
            newContract.setCustomer(existContract.getCustomer());
            newContract.getPayment().setId(existContract.getPayment().getId());
            newContract.getJuridical().setId(existContract.getJuridical().getId());
            model.addAttribute("contract", newContract);
            model.addAttribute("payment", newContract.getPayment());
            model.addAttribute("juridical", newContract.getJuridical());
            model.addAttribute("contractId", contractId);
            this.modelDuplicate(model, customerId);
            return "contract/edit";
        }

        if(newContract != null &&
                newContract.getPayment() != null &&
                newContract.getJuridical() != null) {

            Contract existContract = this.updateContract(contractId, newContract);

            Payment updatedPayment = this.updatePayment(existContract.getPayment().getId(), newContract.getPayment());
            existContract.setPayment(updatedPayment);

            Juridical updatedJuridical = this.updateJuridical(existContract.getJuridical().getId(), newContract.getJuridical());
            existContract.setJuridical(updatedJuridical);

            contractService.save(existContract);

        }

        return "redirect:/customers/edit/" + customerId;
    }

    public Contract updateContract(int id, Contract newContract){
        Contract contract = contractService.getContract(id);
        contract.setContractNo(newContract.getContractNo());
        contract.setProject(newContract.getProject());
        contract.setStatus(newContract.getStatus());
        contract.setStatusCreatedBy(newContract.getStatusCreatedBy());
        contract.setSigned(newContract.isSigned());
        contract.setLotNumber(newContract.getLotNumber());
        contract.setValue(newContract.getValue());
        contract.setSignedDate(newContract.getSignedDate());
        contract.setType(newContract.getType());
        contract.setAreaSigned(newContract.getAreaSigned());
        return contract;
    }

    public Payment updatePayment(int paymentId, Payment newPayment){
        Payment payment = paymentRepository.getOne(paymentId);
        payment.setPaymentProgress(newPayment.getPaymentProgress());
        payment.setPaymentDate95(newPayment.getPaymentDate95());
        return payment;
    }

    public Juridical updateJuridical(int juridicalId, Juridical newJuridical){
        Juridical juridical = juridicalRepository.getOne(juridicalId);
        juridical.setContractInfo(newJuridical.getContractInfo());
        juridical.setBookHolder(newJuridical.getBookHolder());
        juridical.setLiquidation(newJuridical.isLiquidation());
        juridical.setRegistrationProcedures(newJuridical.getRegistrationProcedures());
        juridical.setDeliveryLandDate(newJuridical.getDeliveryLandDate());
        juridical.setDeliveryBookDate(newJuridical.getDeliveryBookDate());
        juridical.setBillProfile(newJuridical.getBillProfile());
        juridical.setNotarizedDate(newJuridical.getNotarizedDate());
        juridical.setContractStatus(newJuridical.getContractStatus());
        return juridical;
    }

    @GetMapping("/delete/{contractId}")
    public String deleteContract(@PathVariable("contractId") int contractId, @PathVariable("customerId") int customerId){
        contractService.deleteContract(contractId);
        return "redirect:/customers/edit/" + customerId;
    }

}
