package com.example.CarRental.service.impl;

import com.example.CarRental.domain.VehicleOwner;
import com.example.CarRental.dto.VehicleOwnerDTO;
import com.example.CarRental.exception.ResourceNotFoundException;
import com.example.CarRental.repository.VehicleOwnerRepository;
import com.example.CarRental.service.VehicleOwnerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleOwnerServiceImpl implements VehicleOwnerService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    VehicleOwnerRepository vehicleOwnerRepository;

    @Override
    public VehicleOwnerDTO getVehicleOwnerById(Long id) {
        Optional<VehicleOwner> optionalOwner = vehicleOwnerRepository.findById(id);
        VehicleOwner owner = optionalOwner.orElseThrow(() -> new ResourceNotFoundException("Owner not found with ID: " + id));
        return modelMapper.map(owner, VehicleOwnerDTO.class);
    }
    @Override
    public VehicleOwner createVehicleOwner(VehicleOwnerDTO ownerDTO) {
//        VehicleOwner owner = modelMapper.map(ownerDTO, VehicleOwner.class);

//        VehicleOwner savedOwner = vehicleOwnerRepository.save(owner);
//        return modelMapper.map(savedOwner, VehicleOwnerDTO.class);

        return vehicleOwnerRepository.save(toEntity(ownerDTO));

    }

    @Override
    public List<VehicleOwnerDTO> getAllVehicleOwners() {
        List<VehicleOwner> owners = vehicleOwnerRepository.findAll();
        return owners.stream().map(owner -> modelMapper.map(owner, VehicleOwnerDTO.class)).collect(Collectors.toList());

    }
    @Override
    public VehicleOwnerDTO updateVehicleOwner(Long id, VehicleOwnerDTO ownerDTO) {
        VehicleOwner owner = vehicleOwnerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with ID: " + id));

        owner.setOwnerName(ownerDTO.getOwnerName());
        owner.setOwnerCnic(ownerDTO.getOwnerCnic());
        owner.setOwnerPhoneNumber(ownerDTO.getOwnerPhoneNumber());
        owner.setOwnerAddress(ownerDTO.getOwnerAddress());
        owner.setCommission(ownerDTO.getCommission());

        VehicleOwner updatedOwner = vehicleOwnerRepository.save(owner);
        return modelMapper.map(updatedOwner, VehicleOwnerDTO.class);

    }
    @Override
    public void deleteVehicleOwner(Long id) {
        if (!vehicleOwnerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Owner not found with ID: " + id);
        }
        vehicleOwnerRepository.deleteById(id);
    }

    public VehicleOwner toEntity(VehicleOwnerDTO vehicleOwnerDTO){  // this is conversion method , from dto to entity
        return modelMapper.map(vehicleOwnerDTO,VehicleOwner.class);
    }
    public VehicleOwnerDTO toDTO(VehicleOwner vehicleOwner){  // Conversion from entity to DTO
        return modelMapper.map(vehicleOwner,VehicleOwnerDTO.class);
    }

}
