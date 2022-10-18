package hu.szintezis.net.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.szintezis.net.model.Owner;
import hu.szintezis.net.model.Vehicle;
import hu.szintezis.net.request.VehicleOwnerConnectionRequest;
import hu.szintezis.net.service.OwnerService;
import hu.szintezis.net.service.VehicleService;

@RestController
@Validated
@RequestMapping("/api/vehicles")
public class VehicleController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TesterPersonController.class);

	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	OwnerService ownerService;

	/*
	 * GET: /api/vehicles/
	 */
	@GetMapping("/")
	public ResponseEntity<List<Vehicle>> listVehicles(){
		LOGGER.info("VehicleController - listVehicles()");
		
		List<Vehicle> vehicleList = vehicleService.findAll();
		return ResponseEntity.ok(vehicleList);
		
	}
	
	/*
	 * GET: /api/vehicles/{id}
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Vehicle> findById(@PathVariable("id") Long id) {
	    LOGGER.info("VehicleController - find by id: " + id);
	    
	    Vehicle vehicle = vehicleService.findById(id);
	   
		return ResponseEntity.ok(vehicle);
	    
	}
	
	/*
	 * POST: /api/vehicles/create
	 */
	@PostMapping("/create")
	public ResponseEntity<String> addNewVehicle( @Valid @RequestBody Vehicle vehicle){
		LOGGER.info("VehicleController - creating new vehicle");

		// Saving the vehicle object to the database
		vehicleService.create(vehicle);
		return ResponseEntity.status(HttpStatus.CREATED).body("Vehicle created successfully.");
	}
	
	/*
	 * PUT: /api/vehicles/{id}
	 */
	@PutMapping("/{id}")
	public ResponseEntity<String> updateVehicle(
			@PathVariable("id") Long id,
			@Valid @RequestBody Vehicle vehicleRequest){
		LOGGER.info("VehicleController - updating owner");
		
		// Retrieving vehicle data from the database by id 
		Vehicle vehicle = vehicleService.findById(id);
		
		// Updating owner object with the new data
		Vehicle newVehicle = vehicle;
		newVehicle.setId(id);
		newVehicle.setBrand(vehicleRequest.getBrand());
		newVehicle.setModel(vehicleRequest.getModel());
		newVehicle.setColor(vehicleRequest.getColor());
		newVehicle.setYearOfManufacture(vehicleRequest.getYearOfManufacture());
		
		// the owner must be be unchanged.
		newVehicle.setOwner(vehicle.getOwner());

		vehicleService.update(newVehicle);
		
		return ResponseEntity.status(HttpStatus.OK).body("Vehicle updated with id: " + id);
	}
	
	/*
	 * POST: /api/vehicles/connect-to-owner
	 */
	@PostMapping("/connect-to-owner")
	public ResponseEntity<String> connectVehicleToOwner( 
		@RequestBody VehicleOwnerConnectionRequest connectionRequest
	){
		
		// Retrieving vehicle data from the database by id 
		Vehicle vehicle = vehicleService.findById(connectionRequest.getVehicleId());
		
		// Retrieving owner data from the database by id 
		Owner owner = ownerService.findById(connectionRequest.getOwnerId());
		
		// Connect owner object to the vehicle
		vehicle.setOwner(owner);
		
		// update the vehicle in the database
		vehicleService.update(vehicle);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("Vehicle (id: " + connectionRequest.getVehicleId()+") connected to owner (id: " + connectionRequest.getOwnerId()+")");
		
	}
}
