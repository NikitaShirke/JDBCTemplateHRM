package com.csi.service;

import com.csi.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService{

    void signUp(Customer customer);

    boolean signIn(String custEmail, String custPassword);

    void saveAllData(List<Customer> customerList);
    Customer findById(int custId);
    List<Customer> findAll();
    void update(int custId, Customer customer);

    void deleteById(int custId);

    void deleteAll();
}
