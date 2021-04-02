package com.sb.post.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.post.model.City;
import com.sb.post.service.CityService;

@RestController
@RequestMapping(value="/city")
public class CityControlloer {
	
	@Autowired
	CityService cityService;
	

    @PostMapping("/save")  
    public City saveStudent(@RequestBody City city) {  
         return cityService.save(city);  
          
    }  

	@GetMapping("/list") 
	public List<City> list() 
	{
		return cityService.findAll();
	}
	
	@GetMapping("/get/{id}")
	public City get(@PathVariable("id") long id) {
		return cityService.findById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") long id) {
		cityService.delete(cityService.findById(id));
	}
	
	@PostMapping("/update/{id}") 
	public City update(@RequestBody City city,@PathVariable("id") long id) {
		city.setId(id);  
		return cityService.update(city);
	}
	

}
