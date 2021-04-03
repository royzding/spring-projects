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

import com.sb.orcl.model.Course;
import com.sb.orcl.service.CourseService;

@RestController  
@CrossOrigin(origins="http://localhost:4200")  
@RequestMapping(value="/course")
public class CourseController {
	
	@Autowired
	CourseService courseService;
	

    @PostMapping("save")  
    public Course saveCourse(@RequestBody Course course) {  
         return courseService.save(course);  
          
    }  

	@GetMapping("/list") 
	public List<Course> list() 
	{
		return courseService.findAll();
	}
	
	@GetMapping("/{id}")
	public Course get(@PathVariable("id") int id) {
		return courseService.findById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") int id) {
		courseService.delete(courseService.findById(id));
	}
	
	@PostMapping("/update/{id}") 
	public Course update(@RequestBody Course course,@PathVariable("id") long id) {
		course.setId(id);  
		return courseService.update(course);
	}
	

}
