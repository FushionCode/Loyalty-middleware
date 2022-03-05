package com.vfd.open_loyalty_middleware.entity;

import com.vfd.open_loyalty_middleware.enums.Gender;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String customerId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private Gender gender;
    private Date dateOfBirth;
    private String referrerId;

}
