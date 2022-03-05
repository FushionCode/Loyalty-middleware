package com.vfd.open_loyalty_middleware.serviceImpl;

import com.vfd.open_loyalty_middleware.dto.CustomerDTO;
import com.vfd.open_loyalty_middleware.entity.Customer;
import com.vfd.open_loyalty_middleware.enums.Gender;
import com.vfd.open_loyalty_middleware.repository.CustomerRepository;
import com.vfd.open_loyalty_middleware.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImpTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Test
    void shouldCreateCustomer(){
        Customer customer = new Customer();
        customer.setCustomerId("aa14b736");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setGender(Gender.MALE);
        customer.setEmailAddress("john.doe@samplemail.com");

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        Customer newCustomer = customerService.createCustomer(customer);

        assertThat(newCustomer).isNotNull().isEqualTo(customer);
    }

    @Test
    void shouldUpdateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId("aa14b736");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setGender(Gender.MALE);
        customer.setEmailAddress("john.doe@samplemail.com");

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        Customer newCustomer = customerService.createCustomer(customer);

        CustomerDTO updateCustomer = new CustomerDTO(newCustomer.getFirstName(), newCustomer.getLastName(),
                newCustomer.getEmailAddress(), "01234567889",newCustomer.getGender(),
                Date.valueOf("1990-12-12"));

        when(customerRepository.findByCustomerId(newCustomer.getCustomerId())).thenReturn(Optional.of(customer));
        assertThat(customerService.updateCustomer(updateCustomer, newCustomer.getCustomerId())).isEqualTo(customer);
    }

    @Test
    void shouldFindCustomerByCustomerId() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId("aa14b736");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setGender(Gender.MALE);
        customer.setEmailAddress("john.doe@samplemail.com");

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        Customer newCustomer = customerService.createCustomer(customer);

        when(customerRepository.findByCustomerId(newCustomer.getCustomerId())).thenReturn(Optional.of(customer));
        assertThat(customerService.findCustomerByCustomerId(newCustomer.getCustomerId())).isNotNull().isEqualTo(customer);
    }

}
