package com.example.CarRental.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VehicleOwnerDTO {
    private Long id;

    @NotBlank(message = "Owner name cannot be blank")
    private String ownerName;

    @NotBlank(message = "Owner CNIC cannot be blank")
    private String ownerCnic;

    @NotNull(message = "Owner phone number cannot be null")
    private Long ownerPhoneNumber;

    @NotBlank(message = "Owner address cannot be blank")
    private String ownerAddress;

    @NotNull(message = "Commission cannot be null")
    private Double commission;
}
