package org.example.service;

import org.example.dao.IUserRepository;
import org.example.dao.IVehicleRepository;
import org.example.model.User;
import org.example.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RentService {
    @Autowired
    public RentService(IUserRepository userRepository, IVehicleRepository vehicleRepository) {
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }
    private IUserRepository userRepository;

    private IVehicleRepository vehicleRepository;

    public boolean rentVehicle(String plate, String login) {
        User user = userRepository.getUser(login);
        Vehicle vehicle = vehicleRepository.getVehicle(plate);

        if (user != null && vehicle != null && !vehicle.isRent()) {
            vehicleRepository.rentVehicle(plate,login);
            return true;
        }
        return false;
    }

    public boolean returnVehicle(String plate, String login){
        User user = userRepository.getUser(login);
        Vehicle vehicle = vehicleRepository.getVehicle(plate);
        if(user != null && vehicle != null && vehicle.isRent()){
            vehicleRepository.returnVehicle(plate, login);
            System.out.println("Returned vehicle "+plate);
            return true;
        }
        return  false;
    }
}