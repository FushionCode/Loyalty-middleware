package com.vfd.open_loyalty_middleware.repository;

import com.vfd.open_loyalty_middleware.entity.Customer;
import com.vfd.open_loyalty_middleware.enums.Gender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void findCustomerByCustomerId(){
        Customer customer1 = new Customer();
        customer1.setCustomerId("aa14b736");
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setGender(Gender.MALE);
        customer1.setEmailAddress("john.doe@samplemail.com");
        entityManager.persist(customer1);
        entityManager.flush();

        Optional<Customer> foundCustomer = customerRepository.findByCustomerId(customer1.getCustomerId());
        System.out.println(foundCustomer.get());

        assertThat(foundCustomer.get()).isEqualTo(customer1);
    }


}
