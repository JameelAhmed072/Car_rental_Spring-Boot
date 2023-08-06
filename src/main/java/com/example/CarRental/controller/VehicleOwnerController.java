package com.example.CarRental.controller;


import com.example.CarRental.domain.Vehicle;
import com.example.CarRental.domain.VehicleOwner;
import com.example.CarRental.dto.VehicleOwnerDTO;
import com.example.CarRental.service.VehicleOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleOwnerController {

    @Autowired
    VehicleOwnerService vehicleOwnerService;

    @GetMapping("vehicleOwner/{id}")
    public ResponseEntity<VehicleOwnerDTO> getVehicleOwnerById(@PathVariable Long id) {
        VehicleOwnerDTO owner = vehicleOwnerService.getVehicleOwnerById(id);
        return ResponseEntity.ok(owner);
    }
    @PostMapping("/vehicleOwner")
    public ResponseEntity<VehicleOwner> createVehicleOwner(@Valid @RequestBody VehicleOwnerDTO ownerDTO) {
        VehicleOwner createdOwner = vehicleOwnerService.createVehicleOwner(ownerDTO);
        return ResponseEntity.ok(createdOwner);
    }

    @GetMapping("/allvehicleOwners")
    public ResponseEntity<List<VehicleOwnerDTO>> getAllVehicleOwners() {
        List<VehicleOwnerDTO> owners = vehicleOwnerService.getAllVehicleOwners();
        return ResponseEntity.ok(owners);
    }

    @PutMapping("vehicleOwner/{id}")
    public ResponseEntity<VehicleOwnerDTO> updateVehicleOwner(@Valid @PathVariable Long id,@RequestBody VehicleOwnerDTO ownerDTO) {
        VehicleOwnerDTO updatedOwner = vehicleOwnerService.updateVehicleOwner(id, ownerDTO);
        return ResponseEntity.ok(updatedOwner);
    }

    @DeleteMapping("vehicleOwner/{id}")
    public ResponseEntity<Void> deleteVehicleOwner(@PathVariable Long id) {
        vehicleOwnerService.deleteVehicleOwner(id);
        return ResponseEntity.noContent().build();
    }


}
