package com.mariemoore.safetynet.dto;

import java.util.HashMap;
import java.util.List;

public record PersonAndFirestationDTO(
        List<PersonPhoneDTO> persons,
        HashMap<String, Integer> nbOfAdultsAndChildren
) {
}
