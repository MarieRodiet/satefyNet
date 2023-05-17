package com.mariemoore.safetynet.dto;

import com.mariemoore.safetynet.model.Person;

import java.util.HashMap;
import java.util.List;

public record PersonAndFirestationDTO(
        List<Person> persons,
        HashMap<String, Integer> nbOfAdultsAndChildren
) {
}
