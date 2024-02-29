package com.interview.notes.code.months.feb24.test8;

import com.interview.notes.code.months.year2023.july23.test4.Customer;
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
