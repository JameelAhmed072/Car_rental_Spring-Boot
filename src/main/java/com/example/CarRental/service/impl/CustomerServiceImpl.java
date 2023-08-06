package com.example.CarRental.service.impl;

import com.example.CarRental.domain.Customer;
import com.example.CarRental.dto.CustomerDTO;
import com.example.CarRental.exception.RecordNotFoundException;
import com.example.CarRental.exception.ResourceNotFoundException;
import com.example.CarRental.repository.CustomerRepository;
import com.example.CarRental.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer customer = optionalCustomer.orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
        return modelMapper.map(customer, CustomerDTO.class);

    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapper.map(savedCustomer, CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> modelMapper.map(customer, CustomerDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));

        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setCustomerCnic(customerDTO.getCustomerCnic());
        customer.setAddress(customerDTO.getAddress());
        customer.setCustomerPhoneNumber(customerDTO.getCustomerPhoneNumber());

        Customer updatedCustomer = customerRepository.save(customer);
        return modelMapper.map(updatedCustomer, CustomerDTO.class);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found with ID: " + id);
        }
        customerRepository.deleteById(id);
    }
}
