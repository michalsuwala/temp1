package org.example.service;

import org.example.dao.IVehicleRepository;
import org.example.dto.VehicleDto;
import org.example.model.Car;
import org.example.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.dto.CreateVehicleDto;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class VehicleService {
    private IVehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(IVehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Collection<VehicleDto> getVehicles() {
        Collection<VehicleDto> vehicleDtos = new ArrayList<>();
        Collection<Vehicle> vehicles = vehicleRepository.getVehicles();
        for (Vehicle vehicle : vehicles) {
            VehicleDto vehicleDto = convertToDto(vehicle);
            vehicleDtos.add(vehicleDto);
        }
        return vehicleDtos;
    }

    public VehicleDto getVehicle(String plate) {
        Vehicle vehicle = vehicleRepository.getVehicle(plate);
        if (vehicle != null)
            return convertToDto(vehicle);
        else
            return null;
    }

    public String createVehicle(CreateVehicleDto createVehicleDto) {
        Vehicle newVehicle = new Car();
        newVehicle.setBrand(createVehicleDto.getBrand());
        newVehicle.setModel(createVehicleDto.getModel());
        newVehicle.setYear(createVehicleDto.getYear());
        newVehicle.setPrice(createVehicleDto.getPrice());
        newVehicle.setPlate(createVehicleDto.getPlate());
        newVehicle.setRent(createVehicleDto.isRent());

        if (newVehicle.getBrand() == null || newVehicle.getModel() == null || newVehicle.getPlate() == null)
            return null;
        else {
            vehicleRepository.addVehicle(newVehicle);
            return "Vehicle created";
        }
    }

    public String deleteVehicle(String plate) {
        Vehicle vehicle = vehicleRepository.getVehicle(plate);
        if (vehicle == null)
            return "Vehicle not found";
        else {
            vehicleRepository.removeVehicle(plate);
            return "Vehicle deleted";
        }
    }

    private VehicleDto convertToDto(Vehicle vehicle) {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setBrand(vehicle.getBrand());
        vehicleDto.setModel(vehicle.getModel());
        vehicleDto.setYear(vehicle.getYear());
        vehicleDto.setPrice(vehicle.getPrice());
        vehicleDto.setPlate(vehicle.getPlate());
        vehicleDto.setRent(vehicle.isRent());
        vehicleDto.setUser(vehicle.getUser());
        return vehicleDto;
    }
}