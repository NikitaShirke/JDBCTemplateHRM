package com.csi.controller;

import com.csi.model.Customer;
import com.csi.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {
    @Autowired
    private CustomerService customerServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Customer customer) {
        log.info("Trying to signUp for Customer:" + customer.getCustName());
        customerServiceImpl.signUp(customer);
        return ResponseEntity.ok("SignUp DOne Successfully");
    }

    @GetMapping("/signin/{custEmail}/{custPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String custEmail, @PathVariable String custPassword) {
        return ResponseEntity.ok(customerServiceImpl.signIn(custEmail, custPassword));
    }

    @PostMapping("/saveall")
    public ResponseEntity<String> saveAll(@RequestBody List<Customer> customerList) {
        customerServiceImpl.saveAllData(customerList);
        return ResponseEntity.ok("All Data Save Successfully");
    }

    @GetMapping("/findbyid/{custId}")

    public ResponseEntity<Customer> findById(@PathVariable int custId) {
        return ResponseEntity.ok(customerServiceImpl.findById(custId));
    }

    @GetMapping("/findall")

    public ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.ok(customerServiceImpl.findAll());
    }

    @GetMapping("/findbyname/{custName}")

    public ResponseEntity<List<Customer>> findByName(@PathVariable String custName) {
        return ResponseEntity.ok(customerServiceImpl.findAll().stream().filter(cust -> cust.getCustName().equals(custName)).toList());
    }

    @GetMapping("findbyaddress/{custAddress}")

    public ResponseEntity<List<Customer>> findByAddress(@PathVariable String custAddress) {
        return ResponseEntity.ok(customerServiceImpl.findAll().stream().filter(cust -> cust.getCustAddress().equals(custAddress)).toList());
    }

    @GetMapping("/findbygmail/{custGmail}")
    public ResponseEntity<Customer>findByEmail(@PathVariable String custEmail) {
        return ResponseEntity.ok(customerServiceImpl.findAll().stream().filter(cust -> cust.getCustEmail().equals(custEmail)).toList().get(0));
    }

    @GetMapping("/findbydob/{custDOB}")

    public ResponseEntity<List<Customer>> findByDOB(@PathVariable String custDOB) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return ResponseEntity.ok(customerServiceImpl.findAll().stream().filter(cust -> dateFormat.format(cust.getCustDOB()).equals(custDOB)).toList());
    }

    @GetMapping("/findbyanyInput/{input}")

    public ResponseEntity<List<Customer>> findByAnyInput(@PathVariable String input) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return ResponseEntity.ok(customerServiceImpl.findAll().stream().filter(customer -> customer.getCustName().equals(input)
                || customer.getCustEmail().equals(input)
                || dateFormat.format(customer.getCustDOB()).equals(input)
                || String.valueOf(customer.getCustId()).equals(input)
                || String.valueOf(customer.getCustName()).equals(input)
                || String.valueOf(customer.getCustAddress()).equals(input)
                || String.valueOf(customer.getCustPassword()).equals(input)).toList());
    }

    @GetMapping("/sortbyid")
    public ResponseEntity<List<Customer>> sortById() {
        return ResponseEntity.ok(customerServiceImpl.findAll().stream().sorted(Comparator.comparing(Customer::getCustId)).toList());
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Customer>> sortByname() {
        return ResponseEntity.ok(customerServiceImpl.findAll().stream().sorted(Comparator.comparing(Customer::getCustName)).toList());
    }

    @GetMapping("/sortbyaddress")
    public ResponseEntity<List<Customer>> sortByAddress() {
        return ResponseEntity.ok(customerServiceImpl.findAll().stream().sorted(Comparator.comparing(Customer::getCustAddress)).toList());

    }

    @PutMapping("/update/{custId}")
    public ResponseEntity<String> update(@PathVariable int custId, @RequestBody Customer customer) {
        customerServiceImpl.update(custId, customer);
        return ResponseEntity.ok("Data Updated Successfully");
    }

    @DeleteMapping("/deletebyid/{custId}")

    public ResponseEntity<String> deleteById(@PathVariable int custId) {
        customerServiceImpl.deleteById(custId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll() {
        customerServiceImpl.deleteAll();
        return ResponseEntity.ok("All DAta Deleted Successfully");
    }

}
