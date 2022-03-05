package com.vfd.open_loyalty_middleware.service;

import com.vfd.open_loyalty_middleware.dto.CustomerDTO;
import com.vfd.open_loyalty_middleware.entity.Customer;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer updateCustomer(CustomerDTO customerDTO, String customerId) throws Exception;
    Customer findCustomerByCustomerId(String customerId) throws Exception;
}
