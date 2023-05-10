package com.mariemoore.safetynet.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Content {
    List<Person> persons;
    List<Firestation> firestations;
    List<MedicalRecord> medicalrecords;
}
