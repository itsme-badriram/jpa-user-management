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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserRepository;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@PostMapping("/insert")
	public ResponseEntity<User> insert(User newUser) throws Exception {
		
		
		try {
			User user = userRepository.save(newUser);
			 return new ResponseEntity<>(user, HttpStatus.CREATED);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<User> update(@PathVariable() long id,User updateUser) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			User _user = user.get();
			_user.setRole(updateUser.getRole());
			 return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
		}
		else {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/findAll")
	public ResponseEntity<List<User>> findAll() {
		try {
			return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<User> findById(@PathVariable long id) {
		
		try {
			Optional<User> user = userRepository.findById(id);
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/findByRole/{role}")
	public ResponseEntity<List<User>> findByRole(@PathVariable String role) {
		try {
			List<User> users = userRepository.findByRole(role);
			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/deleteById/{id}") 
	public ResponseEntity<HttpStatus> deleteId(@PathVariable long id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping("/deleteAll")
	  public ResponseEntity<HttpStatus> deleteAll() {
	    try {
	      userRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	  }

	
	
}
