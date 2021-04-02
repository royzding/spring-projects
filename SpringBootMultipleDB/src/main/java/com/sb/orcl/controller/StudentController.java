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

import com.sb.orcl.model.Student;
import com.sb.orcl.service.StudentService;

@RestController  
@CrossOrigin(origins="http://localhost:4200")  
@RequestMapping(value="/student")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
/*
    {
        "name": "Jane",
        "age": 24,
        "address": {
            "addr": "Jane addr"
        }
    }  
*/  
	
    @PostMapping("save")  
    public Student saveStudent(@RequestBody Student student) {  
         return studentService.save(student);  
          
    }  

	@GetMapping("/list") 
	public List<Student> list() 
	{
		return studentService.findAll();
	}
	
	@GetMapping("/{id}")
	public Student get(@PathVariable("id") int id) {
		return studentService.findById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") int id) {
		studentService.delete(studentService.findById(id));
	}
	
	@PostMapping("/update/{id}") 
	public Student update(@RequestBody Student student,@PathVariable("id") long id) {
		student.setId(id);  
		return studentService.update(student);
	}
	

}
