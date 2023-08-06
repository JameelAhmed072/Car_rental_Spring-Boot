package com.example.CarRental.service.impl;

import com.example.CarRental.domain.Vehicle;
import com.example.CarRental.domain.VehicleOwner;
import com.example.CarRental.dto.VehicleDTO;
import com.example.CarRental.exception.RecordNotFoundException;
import com.example.CarRental.exception.ResourceNotFoundException;
import com.example.CarRental.repository.VehicleOwnerRepository;
import com.example.CarRental.repository.VehicleRepository;
import com.example.CarRental.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    VehicleOwnerRepository vehicleOwnerRepository;


    @Autowired
    ModelMapper modelMapper;
    @Override
    public VehicleDTO getVehicleById(Long id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        Vehicle vehicle = optionalVehicle.orElseThrow(() -> new RecordNotFoundException("Vehicle not found with ID: " + id));
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    public List<VehicleDTO> findAll(){
        return vehicleRepository.findAll().stream().map(v->toDTO(v)).collect(Collectors.toList());
    }

    @Override
    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        VehicleOwner owner = vehicleOwnerRepository.findById(vehicleDTO.getOwnerId())
                .orElseThrow(() -> new RecordNotFoundException("Owner not found with ID: " + vehicleDTO.getOwnerId()));

        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        vehicle.setOwnerId(owner);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return modelMapper.map(savedVehicle, VehicleDTO.class);


//        return vehicleRepository.save(toEntity(vehicleDTO));
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream().map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class)).collect(Collectors.toList());
    }

    @Override
    public VehicleDTO updateVehicle(Long id, VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with ID: " + id));

        VehicleOwner owner = vehicleOwnerRepository.findById(vehicleDTO.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with ID: " + vehicleDTO.getOwnerId()));

        vehicle.setVehicleName(vehicleDTO.getVehicleName());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setColor(vehicleDTO.getColor());
        vehicle.setOwnerId(owner);

        Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        return modelMapper.map(updatedVehicle, VehicleDTO.class);
    }

    @Override
    public void deleteVehicle(Long id) {
        if (!vehicleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vehicle not found with ID: " + id);
        }
        vehicleRepository.deleteById(id);
    }


    public Vehicle toEntity(VehicleDTO vehicleDTO){  // this is conversion method , from dto to entity
        return modelMapper.map(vehicleDTO,Vehicle.class);
    }
    public VehicleDTO toDTO(Vehicle vehicle){  // Conversion from entity to DTO
        return modelMapper.map(vehicle,VehicleDTO.class);
    }

}
