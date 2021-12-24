package com.upgrad.hirewheels.controller;

import com.upgrad.hirewheels.dto.VehicleDTO;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.services.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hirewheels/v1")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    ModelMapper modelMapper;
    /**
     * POST to add new vehicle inside the database
     *http://localhost:8081/hirewheels/v1/vehicles
     *
     */
    @PostMapping(value = "/vehicles" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity AddVehicle(@RequestBody VehicleDTO vehicleDTO) throws Exception {
        Vehicle newVehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        Vehicle savedVehicle = adminService.registerVehicle(newVehicle);
        VehicleDTO savedVehicleDTO = modelMapper.map(savedVehicle, VehicleDTO.class);
        return new ResponseEntity(savedVehicleDTO , HttpStatus.ACCEPTED);
    }

    /**
     * PUT updates the availability status of vehicle with ID passed inside the database
     *http://localhost:8081/hirewheels/v1/vehicles/id
     *
     */
    @PutMapping(value = "/vehicles/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity UpdateVehicle(@PathVariable(name = "id") int id , @RequestBody VehicleDTO vehicleDTO){
        Vehicle vehicle = modelMapper.map(vehicleDTO , Vehicle.class);
        Vehicle updatedVehicle = adminService.changeAvailability(id , vehicleDTO.getAvailabilityStatus());
        VehicleDTO updatedVehicleDTO = modelMapper.map(updatedVehicle ,VehicleDTO.class);
        return new ResponseEntity(updatedVehicleDTO , HttpStatus.OK);
    }
}
