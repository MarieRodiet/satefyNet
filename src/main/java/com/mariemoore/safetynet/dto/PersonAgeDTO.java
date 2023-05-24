package com.mariemoore.safetynet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonAgeDTO {
    private String firstName;
    private String lastName;
    private long age;
}
