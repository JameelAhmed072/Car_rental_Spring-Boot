package com.example.CarRental.dto;

import com.example.CarRental.domain.Booking;
import lombok.*;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDTO {

    private Long id;

    @NotBlank(message = "Customer Name cannot be Blank")
    private String customerName;

    @NotNull(message = "Customer cnic should not be Null")
    private String customerCnic;

    @NotBlank(message = "Customer Address Name cannot be Blank")
    private String address;
    @NotNull(message = "Customer Phone Number should not be Null")
    private Long customerPhoneNumber;

    private List<Booking> bookings;
}
