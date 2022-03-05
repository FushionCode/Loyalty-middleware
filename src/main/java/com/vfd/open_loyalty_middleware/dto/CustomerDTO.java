package com.vfd.open_loyalty_middleware.dto;

import com.vfd.open_loyalty_middleware.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private Gender gender;
    private Date dateOfBirth;

}
