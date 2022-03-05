package com.vfd.open_loyalty_middleware.cotroller;

import com.vfd.open_loyalty_middleware.dto.CustomerDTO;
import com.vfd.open_loyalty_middleware.entity.Customer;
import com.vfd.open_loyalty_middleware.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public Customer createCustomerAccount(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @GetMapping("{customerId}")
    public Customer getCustomerDetailsWithCustomerId(@PathVariable("customerId") String customerId) throws Exception {
        return customerService.findCustomerByCustomerId(customerId);
    }

    @PatchMapping
    public Customer updateCustomerInformation(@RequestBody CustomerDTO customerDTO, String customerId) throws Exception {
        return customerService.updateCustomer(customerDTO, customerId);
    }
}
