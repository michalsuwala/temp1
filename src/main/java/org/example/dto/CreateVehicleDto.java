package org.example.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateVehicleDto {
    private String brand;
    private String model;
    private int year;
    private double price;
    private String plate;
    private boolean rent;
    private UserDto user; // Assuming UserDto is the DTO for User
}
