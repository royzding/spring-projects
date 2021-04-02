package com.sb.orcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.orcl.model.Address;
import com.sb.orcl.service.AddressService;

@RestController  
@CrossOrigin(origins="http://localhost:4200")  
@RequestMapping(value="/address")
public class AddressController {
	
	@Autowired
	AddressService addressService;
	

    @PostMapping("save")  
    public Address saveAddress(@RequestBody Address address) {  
         return addressService.save(address);  
          
    }  

	@GetMapping("/list") 
	public List<Address> list() 
	{
		return addressService.findAll();
	}
	
	@GetMapping("/{id}")
	public Address get(@PathVariable("id") int id) {
		return addressService.findById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") int id) {
		addressService.delete(addressService.findById(id));
	}
	
	@PostMapping("/update/{id}") 
	public Address update(@RequestBody Address address,@PathVariable("id") long id) {
		address.setId(id);  
		return addressService.update(address);
	}
	

}
