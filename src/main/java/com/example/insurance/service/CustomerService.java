package com.example.insurance.service;

import com.example.insurance.model.Customer;
import com.example.insurance.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setCustomerName(customerDetails.getCustomerName());
        customer.setOccupation(customerDetails.getOccupation());
        customer.setCity(customerDetails.getCity());
        customer.setState(customerDetails.getState());
        customer.setCountry(customerDetails.getCountry());
        customer.setZip(customerDetails.getZip());
        customer.setMobileNumber(customerDetails.getMobileNumber());
        customer.setDOB1(customerDetails.getDOB1());
        customer.setNomineeName(customerDetails.getNomineeName());
        customer.setNomineeOccupation(customerDetails.getNomineeOccupation());
        customer.setNomineeAddress(customerDetails.getNomineeAddress());
        customer.setNomineemobileNumber(customerDetails.getNomineemobileNumber());
        customer.setNomineeDOB(customerDetails.getNomineeDOB());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
