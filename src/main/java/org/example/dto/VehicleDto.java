package org.example.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.BooleanToShortConventer;
import org.example.model.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    private String brand;
    private String model;
    private int year;
    private double price;
    private String plate;
    private boolean rent;
    private User user;
    private String login;
    private String password;
}
