package com.globalsoftwaresupport.service;

import com.globalsoftwaresupport.model.Vehicle;

public interface VehicleService {
	public Vehicle create(Vehicle vehicle);
	public void validateVehicle(String vehicleId);
	public void associate(String vehicleId, String userId);
	public void delete(String vehicleId, String userId);
}
