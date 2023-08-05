package com.jimmycasta.pizzeria.services;

import com.jimmycasta.pizzeria.entities.CustomerEntity;
import com.jimmycasta.pizzeria.entities.OrderEntity;
import com.jimmycasta.pizzeria.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity findByPhone(String phone) {
        return customerRepository.findByPhoneNumber(phone);
    }




}
