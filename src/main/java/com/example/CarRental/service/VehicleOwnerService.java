package com.example.CarRental.service;

import com.example.CarRental.domain.VehicleOwner;
import com.example.CarRental.dto.VehicleOwnerDTO;

import java.util.List;

public interface VehicleOwnerService {

    VehicleOwnerDTO getVehicleOwnerById(Long id);

    VehicleOwner createVehicleOwner(VehicleOwnerDTO vehicleOwnerDTO);

    public List<VehicleOwnerDTO> getAllVehicleOwners();

    public VehicleOwnerDTO updateVehicleOwner(Long id, VehicleOwnerDTO ownerDTO);

    public void deleteVehicleOwner(Long id);
}
