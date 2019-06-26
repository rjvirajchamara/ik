package lk.Student.service.impl;

import lk.Student.dto.CustomerDTO;
import lk.Student.entity.Customer;
import lk.Student.repository.CustomerRepository;
import lk.Student.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CustomerServiceImpl implements CustomerService {




    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public ArrayList<CustomerDTO> getAllCustomers() {

       List<Customer> customer=customerRepository.findAll();

       ArrayList<CustomerDTO> customerAll = new ArrayList<>();

       for (Customer customer1:customer){
           CustomerDTO customerDTO = new CustomerDTO(customer1.getId(),customer1.getName(),customer1.getAddress());

           customerAll.add(customerDTO);
       }



        return customerAll;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveCustomer(String customerId, CustomerDTO dto) {

        if (!dto.getId().equals(customerId)) {

            throw new RuntimeException("Customer ID mismatched");
        }
        Customer customer=new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setAddress(dto.getAddress());
        customerRepository.save(customer);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateCustomer(String customerId, CustomerDTO dto) {
        if (!dto.getId().equals(customerId)) {
            throw new RuntimeException("Customer ID mismatched");
        }
        if (customerRepository.existsById(customerId)) {
            customerRepository.save(new Customer(dto.getId(), dto.getName(), dto.getAddress()));
        }else{
            throw new RuntimeException("Customer doesn't exist");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public CustomerDTO findCustomer(String customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());
    }

}
