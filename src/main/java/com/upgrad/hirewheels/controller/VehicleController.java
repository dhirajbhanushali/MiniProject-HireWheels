package com.upgrad.hirewheels.controller;

import com.upgrad.hirewheels.dto.VehicleDTO;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.services.AdminService;
import com.upgrad.hirewheels.services.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hirewheels/v1")
public class VehicleController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    ModelMapper modelMapper;

    /**
     * Get to return all vehicles available inside the databas
     *http://localhost:8080/hirewheels/v1/vehicles
     *
     */
    @GetMapping(value = "/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getVehicles() {
        List<Vehicle> vehicleList = vehicleService.getAllVehicles();
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();

        for(Vehicle vehicle : vehicleList){
            vehicleDTOList.add(modelMapper.map(vehicle, VehicleDTO.class));
        }

        return new ResponseEntity(vehicleDTOList , HttpStatus.OK);
    }
}
