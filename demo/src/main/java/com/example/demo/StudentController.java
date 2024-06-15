package com.example.demo;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins= "http://localhost:3000/")
@RestController
@RequestMapping("api/")
public class StudentController {
	
	@Autowired
	KieSession kieSession;
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	@GetMapping("students")
	public List<Student> getStudents(){
		return studentRepository.findAll() ;
	}
	
	@PostMapping("newstudent")
	public String createStudent(@RequestBody Student student) {
		this.studentRepository.save(student);
		return "Created new Student";
	}
	
	@PostMapping("student")
	public StudentModel getStudents13(@RequestBody StudentModel studentModel){
		kieSession.insert(studentModel);
		kieSession.fireAllRules();		
		return studentModel;
	}
}
