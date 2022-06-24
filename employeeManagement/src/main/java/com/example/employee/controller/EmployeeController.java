package com.example.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.repository.EmployeeRepository;
import com.example.employee.exception.ResourceNotFoundException;
import com.example.employee.model.Employee;
@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/employees")
	public List<Employee> getAllEmmployees(){
		
		return employeeRepo.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepo.save(employee);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
		Employee employee = employeeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(" Employee not exist"));
		return ResponseEntity.ok(employee);
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetail) {
		Employee employee = employeeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(" Employee not exist"));
		
		employee.setFirstName(employeeDetail.getFirstName());
		employee.setLastName(employeeDetail.getLastName());
		employee.setEmailId(employeeDetail.getEmailId());
		
		Employee updatedEmployee = employeeRepo.save(employeeDetail);
		return ResponseEntity.ok(updatedEmployee);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable long id) {
		Employee employee = employeeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(" Employee not exist"));
	
		employeeRepo.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
