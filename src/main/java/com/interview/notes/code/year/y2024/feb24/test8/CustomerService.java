package com.interview.notes.code.year.y2024.feb24.test8;

import com.interview.notes.code.year.y2023.july23.test4.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer registerCustomer(Customer customer) {
        // return customerRepository.save(customer);
        return null;
    }
}
