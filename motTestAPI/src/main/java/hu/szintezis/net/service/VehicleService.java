package hu.szintezis.net.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.szintezis.net.exception.ResourceNotFoundException;
import hu.szintezis.net.model.Vehicle;
import hu.szintezis.net.repository.VehicleRepository;

@Service
public class VehicleService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TesterPersonService.class);
	
	@Autowired
	VehicleRepository vehicleRepo;

	public List<Vehicle> findAll(){
		LOGGER.info("Listing all vehicles");
		return vehicleRepo.findAll();
	}
	
	public Vehicle findById(Long id){
		Optional<Vehicle> vehicle = vehicleRepo.findById(id);
	    if(!vehicle.isPresent()) {
	    	String errorMessage = "Could not find vehicle with id: " + id;
	    	LOGGER.error(errorMessage);
	    	throw new ResourceNotFoundException(errorMessage);
	    }
	    return vehicle.get();
	}
	
	public void create(Vehicle vehicle) {
		vehicleRepo.save(vehicle);
		LOGGER.info("New vehicle created.");
	}
	
	public void saveAll(List<Vehicle> vehicleList) {
		vehicleRepo.saveAll(vehicleList);
		LOGGER.info("A list of new vehicles were saved to database.");
	}
	
	public void update(Vehicle vehicle) {
		if(vehicle.getId() != null) {
			vehicleRepo.save(vehicle);
			LOGGER.info("Vehicle updated with id: " + vehicle.getId());
		} else {
			LOGGER.warn("Vehicle update failed. Object: "+vehicle);
		}
	}
}
