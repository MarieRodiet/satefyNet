package com.mariemoore.safetynet.dto;

public record FirestationAndPersonDTO(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String stationId
) {
}
