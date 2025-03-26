package com.globalsoftwaresupport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.globalsoftwaresupport.model.Vehicle;
import com.globalsoftwaresupport.service.VehicleService;

@RestController
@RequestMapping("v1")
public class VehicleController {

	@Autowired
	private VehicleService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Vehicle create(@RequestBody Vehicle vehicle) {
		return service.create(vehicle);
	}
	
	@PostMapping("{vehicleId}/users/{userId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void associate(@PathVariable(name = "vehicleId") String vehicleId, 
			@PathVariable(name = "userId") String userId) {
		service.validateVehicle(vehicleId);
		service.associate(vehicleId, userId);
	}
	
	@DeleteMapping("{vehicleId}/users/{userId}")
	public void delete(@PathVariable(name = "vehicleId") String vehicleId, 
			@PathVariable(name = "userId") String userId) {
		service.validateVehicle(vehicleId);
		service.delete(vehicleId, userId);
	}
}