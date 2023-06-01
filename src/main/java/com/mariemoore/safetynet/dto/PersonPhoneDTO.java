package com.mariemoore.safetynet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PersonPhoneDTO {
    public String firstName;
    public String lastName;
    public String phone;
}
