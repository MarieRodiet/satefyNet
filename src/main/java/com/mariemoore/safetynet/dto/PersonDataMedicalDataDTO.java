package com.mariemoore.safetynet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDataMedicalDataDTO {
    private String firstName;
    private String lastName;
    private String address;
    private long age;
    private String email;
    private List<String> medications;
    private List<String> allergies;

}
