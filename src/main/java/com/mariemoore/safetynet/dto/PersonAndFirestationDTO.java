package com.mariemoore.safetynet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonAndFirestationDTO {
    private List<PersonPhoneDTO> persons;
    private HashMap<String, Integer> nbOfAdultsAndChildren;
}
