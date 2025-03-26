package com.globalsoftwaresupport.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.globalsoftwaresupport.model.Vehicle;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

}
