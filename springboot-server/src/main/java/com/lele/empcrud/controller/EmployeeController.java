package com.lele.empcrud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

import com.lele.empcrud.exceptions.ResourceNotFoundException;
import com.lele.empcrud.models.Employee;
import com.lele.empcrud.repositories.EmployeeRepository;
import com.lele.empcrud.services.SequenceGeneratorService;

@CrossOrigin(origins = "http://localhost:4200") // gives the angular application acess to this controller running on port 8080 
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	// GET all records
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll();
	}
	
	// GET record
	@GetMapping("/employees/{id}")
    public ResponseEntity < Employee > getEmployeeById(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        
        return ResponseEntity.ok().body(employee);
    }
	
	// CREATE record
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		employee.setId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
		
		return employeeRepository.save(employee);
	}
	
	// UPDATE record
	@PutMapping("/employees/{id}")
	public ResponseEntity < Employee > updateEmployee(@PathVariable(value = "id") Long employeeId, 
	@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		
		final Employee updatedEmployee = employeeRepository.save(employee);
		
		return ResponseEntity.ok(updatedEmployee);
	}
	
	// DELETE record
	@DeleteMapping("/employees/{id}")
	public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") Long employeeId)
	throws ResourceNotFoundException {
		
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		
		employeeRepository.delete(employee);
		Map< String, Boolean > response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
		
	}

}
