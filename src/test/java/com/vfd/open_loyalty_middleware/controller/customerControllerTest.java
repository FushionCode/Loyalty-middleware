package com.vfd.open_loyalty_middleware.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vfd.open_loyalty_middleware.cotroller.CustomerController;
import com.vfd.open_loyalty_middleware.dto.CustomerDTO;
import com.vfd.open_loyalty_middleware.entity.Customer;
import com.vfd.open_loyalty_middleware.enums.Gender;
import com.vfd.open_loyalty_middleware.repository.CustomerRepository;
import com.vfd.open_loyalty_middleware.service.CustomerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class customerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    CustomerService customerService;

    @MockBean
    CustomerRepository repository;

    @Test
    void shouldCreateCustomerAccount() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId("aa14b736");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setGender(Gender.MALE);
        customer.setEmailAddress("john.doe@samplemail.com");

        when(customerService.createCustomer(customer)).thenReturn(customer);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(customer));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.customerId", Matchers.is("aa14b736")))
                .andReturn();


    }

    @Test
    void shouldUpdateCustomerDetails() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId("aa14b736");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setGender(Gender.MALE);
        customer.setEmailAddress("john.doe@samplemail.com");

        CustomerDTO updateCustomer = new CustomerDTO();
                updateCustomer.setFirstName("John");
                updateCustomer.setLastName("Doe");
                updateCustomer.setEmailAddress("john.doe@samplemail.com");
                updateCustomer.setGender(Gender.FEMALE);
                updateCustomer.setPhoneNumber("1234567890");
                updateCustomer.setDateOfBirth(Date.valueOf("1990-12-22"));



        when(repository.findByCustomerId(customer.getCustomerId())).thenReturn(Optional.of(customer));
        when(repository.save(any(Customer.class))).thenReturn(customer);
        when(customerService.updateCustomer(updateCustomer, customer.getCustomerId())).thenReturn(customer);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.patch("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updateCustomer));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())  ;
                //.andExpect(jsonPath("$", Matchers.notNullValue()))
                //.andExpect(jsonPath("$.fistName", Matchers.is("John")));
    }

    @Test
    void shouldGetCustomerById() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId("aa14b736");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setGender(Gender.MALE);
        customer.setEmailAddress("john.doe@samplemail.com");

        when(customerService.findCustomerByCustomerId(customer.getCustomerId())).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get("/customer/aa14b736").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()));
    }
}