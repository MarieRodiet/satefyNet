package com.mariemoore.safetynet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class Firestation {
    private Integer stationId;
    private String address;
}
