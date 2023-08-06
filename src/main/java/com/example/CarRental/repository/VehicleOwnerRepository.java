package com.example.CarRental.repository;

import com.example.CarRental.domain.VehicleOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleOwnerRepository extends JpaRepository<VehicleOwner,Long> {
}
