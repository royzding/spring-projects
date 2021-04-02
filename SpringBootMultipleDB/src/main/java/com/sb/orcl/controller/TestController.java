package com.sb.orcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.orcl.model.Test;
import com.sb.orcl.service.TestService;

@RestController  
@CrossOrigin(origins="http://localhost:4200")  
@RequestMapping(value="/testx")
public class TestController {
	
	@Autowired
	TestService testService;
	

    @PostMapping("/save")  
    public Test saveTest(@RequestBody Test test) {  
         return testService.save(test);  
          
    }  

	@GetMapping("/list") 
	public List<Test> list() 
	{
		return testService.findAll();
	}
	
	@GetMapping("/{id}")
	public Test get(@PathVariable("id") int id) {
		return testService.findById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") int id) {
		testService.delete(testService.findById(id));
	}
	
	@PutMapping("/update/{id}") 
	public Test update(@RequestBody Test test,@PathVariable("id") long id) {
		test.setId(id);  
		return testService.update(test);
	}
	

}
