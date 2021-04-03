package com.sb.orcl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.orcl.model.Address;
import com.sb.orcl.repo.AddressRepo;

@Service
public class AddressService {
	
	@Autowired
	AddressRepo addressRepo;
	
	public Address save(Address address) {
		return addressRepo.save(address);
	}
	
	public List<Address> findAll() {
		
		return addressRepo.findAll();
	}
	
	public Address findById(long id) {
		return addressRepo.findById(id).get();
	}
	
	public void delete(Address address) {
		addressRepo.delete(address);
	}
	
	public Address update(Address address) {
		return addressRepo.save(address);
	}

}
