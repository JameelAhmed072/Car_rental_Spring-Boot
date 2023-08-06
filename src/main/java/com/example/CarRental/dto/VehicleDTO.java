package com.example.CarRental.dto;


import com.example.CarRental.domain.VehicleOwner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleDTO {


    private Long id;

    @NotBlank(message = "Vehicle name cannot be blank")
    private String vehicleName;

    @NotNull(message = "Model cannot be null")
    private Long model;

    @NotBlank(message = "Brand cannot be blank")
    private String brand;

    @NotBlank(message = "Color cannot be blank")
    private String color;

    @NotNull(message = "Owner ID cannot be null")
    private Long ownerId;
}
