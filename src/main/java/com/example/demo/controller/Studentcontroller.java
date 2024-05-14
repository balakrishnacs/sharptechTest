package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.repo.Studentrepo;

@RestController
public class Studentcontroller {

	@Autowired
	Studentrepo studentrepo;
	
	@PostMapping("/savesetails")
	public ResponseEntity<Student> saveDetails(@RequestBody Student student) {
		return new ResponseEntity<>( studentrepo.save(student),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getdetails")
	public ResponseEntity<List<Student>> getDetails() {
		return new ResponseEntity<>(studentrepo.findAll(),HttpStatus.OK);
	}
	
	@PutMapping("/updateDetails/{id}")
	public ResponseEntity<Student> updatedetails(@PathVariable long id, @RequestBody Student stud) {
		Optional<Student> student=studentrepo.findById(id);
		if(student.isPresent()) {
			student.get().setName(stud.getName());
			student.get().setEmail(stud.getEmail());
			student.get().setAddress(stud.getAddress());
			return new ResponseEntity<>(studentrepo.save(student.get()),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/deletdetails/{id}")
	public ResponseEntity<Student> deletDetails(@PathVariable long id) {
		Optional<Student> student=studentrepo.findById(id);
		if(student.isPresent()) {
			studentrepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
	}
}

