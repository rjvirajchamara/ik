package lk.Student.service;

import lk.Student.dto.CustomerDTO;

import java.util.ArrayList;

public interface CustomerService {

    public ArrayList<CustomerDTO> getAllCustomers();
    void saveCustomer(String customerId, CustomerDTO dto);

    void updateCustomer(String customerId, CustomerDTO dto);

    void deleteCustomer(String customerId);

    CustomerDTO findCustomer(String customerId);



}
