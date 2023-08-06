package com.example.CarRental;



import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CarRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper(){ // this will directly convert entity to dto, this is shortest way
		return new ModelMapper();
	}

}
