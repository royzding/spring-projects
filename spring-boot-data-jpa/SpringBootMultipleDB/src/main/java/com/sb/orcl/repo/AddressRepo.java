package com.sb.orcl.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sb.orcl.model.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long>{
	
	List<Address> findByAddrContaining(String addr);
	
}