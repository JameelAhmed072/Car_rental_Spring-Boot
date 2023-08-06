package com.example.CarRental.controller;


import com.example.CarRental.domain.Vehicle;

import com.example.CarRental.dto.VehicleDTO;
import com.example.CarRental.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class VehicleController {


    @Autowired
    VehicleService vehicleService;
    @GetMapping("/vehicle/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long id){
        VehicleDTO vehicle = vehicleService.getVehicleById(id);
        return ResponseEntity.ok(vehicle);
    }

    @PostMapping("/vehicle")
    public ResponseEntity<VehicleDTO> saveVehicle(@Valid @RequestBody VehicleDTO vehicleDTO){
        VehicleDTO createVehicle = vehicleService.createVehicle(vehicleDTO);
        return ResponseEntity.ok(createVehicle);
    }

    @GetMapping("/allVehicle")
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        List<VehicleDTO> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @PutMapping("/vehicle/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Long id, @Valid @RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO updatedVehicle = vehicleService.updateVehicle(id, vehicleDTO);
        return ResponseEntity.ok(updatedVehicle);
    }

    @DeleteMapping("/vehicle/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }


}






