package com.globalsoftwaresupport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.globalsoftwaresupport.model.Vehicle;
import com.globalsoftwaresupport.proxy.VehicleProxy;

@RestController
@RequestMapping("v1/vehicles")
public class GatewayVehicleController {

	@Autowired
	private VehicleProxy vehicleProxy;
	
	@PostMapping
	public void create(@RequestBody Vehicle vehicle) {
		vehicleProxy.create(vehicle);
	}
	
	@PostMapping("{vehicleId}/users/{userId}")
	public void associate(@PathVariable(name = "vehicleId") String vehicleId, 
			@PathVariable(name = "userId") String userId) {
		vehicleProxy.associate(vehicleId, userId);
	}
	
	@DeleteMapping("{vehicleId}/users/{userId}")
	public void remove(@PathVariable(name = "vehicleId") String vehicleId, 
			@PathVariable(name = "userId") String userId) {
		vehicleProxy.removeAssociation(vehicleId, userId);
	}
}
