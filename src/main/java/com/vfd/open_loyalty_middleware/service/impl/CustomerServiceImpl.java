package com.vfd.open_loyalty_middleware.service.impl;

import com.vfd.open_loyalty_middleware.dto.CustomerDTO;
import com.vfd.open_loyalty_middleware.entity.Customer;
import com.vfd.open_loyalty_middleware.repository.CustomerRepository;
import com.vfd.open_loyalty_middleware.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(CustomerDTO customerDTO, String customerId) throws Exception {
        Optional<Customer> findCustomer = customerRepository.findByCustomerId(customerId);
        if (findCustomer.isPresent()){
            Customer updateThisCustomer = findCustomer.get();

            updateThisCustomer.setFirstName(customerDTO.getFirstName());
            updateThisCustomer.setLastName(customerDTO.getLastName());
            updateThisCustomer.setEmailAddress(customerDTO.getEmailAddress());
            updateThisCustomer.setGender(customerDTO.getGender());
            updateThisCustomer.setDateOfBirth(customerDTO.getDateOfBirth());
            updateThisCustomer.setPhoneNumber(customerDTO.getPhoneNumber());

            return customerRepository.save(updateThisCustomer);
        }
        else {
            throw new Exception("Customer not FOUND");
        }
    }

    @Override
    public Customer findCustomerByCustomerId(String customerId) throws Exception {
        Optional<Customer> foundCustomer = customerRepository.findByCustomerId(customerId);
        if (foundCustomer.isEmpty()) throw new Exception("Customer not found");
        return foundCustomer.get();
    }
}
