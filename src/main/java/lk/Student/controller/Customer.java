package lk.Student.controller;

import lk.Student.dto.CustomerDTO;
import lk.Student.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/customers")
public class Customer {


            @Autowired
            private CustomerService customerService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }


    @PostMapping("/{id}")
    public void saveCustomer(@PathVariable("id") String id,@RequestBody CustomerDTO customerDto){
        customerService.saveCustomer(id,customerDto);
    }

    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable("id") String id,
                               @RequestBody CustomerDTO customerDTO){
        customerService.updateCustomer(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") String id){
        customerService.deleteCustomer(id);
    }

    @GetMapping("/{id}")
    public CustomerDTO findCustomer(@PathVariable("id") String id)
    {
        return customerService.findCustomer(id);

    }


}
