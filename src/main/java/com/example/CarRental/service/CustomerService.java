package com.example.CarRental.service;

import com.example.CarRental.domain.Customer;
import com.example.CarRental.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO getCustomerById(Long id);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    public List<CustomerDTO> getAllCustomers();

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    public void deleteCustomer(Long id);


}
