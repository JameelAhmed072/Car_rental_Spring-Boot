package com.example.CarRental.service;

import com.example.CarRental.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {

    VehicleDTO getVehicleById(Long id);

    VehicleDTO createVehicle(VehicleDTO vehicleDTO);

    public List<VehicleDTO> getAllVehicles();

    public VehicleDTO updateVehicle(Long id, VehicleDTO vehicleDTO);

    public void deleteVehicle(Long id);
}
