package com.globalsoftwaresupport.proxy;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.globalsoftwaresupport.model.User;
import com.globalsoftwaresupport.model.Vehicle;

@Component
public class VehicleProxy {

	private final RestTemplate restTemplate;
	private final String url;
	
	public VehicleProxy(@Value("${vehicle.url}") String url, RestTemplate restTemplate) {
		this.url = url;
		this.restTemplate = restTemplate;
	}
	
	public void create(Vehicle vehicle) {
		restTemplate.postForObject(url + "v1", vehicle, Vehicle.class);
	}

	public void associate(String vehicleId, String userId) {
		// null for the request (no request body now)
		restTemplate.postForObject(url + "v1/{vehicleId}/users/{userId}", null, Void.class, 
				Map.of("vehicleId", vehicleId, "userId", userId));
	}

	public void removeAssociation(String vehicleId, String userId) {
		restTemplate.delete(url + "v1/{vehicleId}/users/{userId}", Map.of("vehicleId", 
				vehicleId, "userId", userId));
	}

}
