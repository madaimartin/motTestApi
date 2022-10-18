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
import hu.szintezis.net.service.OwnerService;

@RestController
@Validated
@RequestMapping("/api/owners")
public class OwnerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OwnerController.class);
	
	@Autowired
	OwnerService ownerService;

	/*
	 * GET: /api/owners/
	 */
	@GetMapping("/")
	public ResponseEntity<List<Owner>> listOwners(){
		LOGGER.info("OwnerController - listOwners()");
		
		List<Owner> ownerList = ownerService.findAll();
		return ResponseEntity.ok(ownerList);
		
	}
	
	/*
	 * GET: /api/owners/{id}
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Owner> findById(@PathVariable("id") Long id) {
	    LOGGER.info("OwnerController - find by id: " + id);
	    
	    Owner owner = ownerService.findById(id);
	    return ResponseEntity.ok(owner);
	    
	}
	
	/*
	 * POST: /api/owners/create
	 */
	@PostMapping("/create")
	public ResponseEntity<String> createNewOwner( @Valid @RequestBody  Owner owner){
		LOGGER.info("OwnerController - createNewOwner()");
		
		// Saving the owner object to the database
		ownerService.create(owner);
		return ResponseEntity.status(HttpStatus.CREATED).body("Owner created successfully.");
	}
	
	/*
	 * PUT: /api/owners/{id}
	 */
	@PutMapping("/{id}")
	public ResponseEntity<String> updateOwner(
			@PathVariable("id") Long id,
			@Valid @RequestBody Owner ownerRequest){
		LOGGER.info("OwnerController - updateOwner()");
		
		// Retrieving owner data from the database by id 
		Owner owner = ownerService.findById(id);
		
		// Updating owner object with the new data
		Owner newOwner = owner;
		newOwner.setId(id);
		newOwner.setFirstName(ownerRequest.getFirstName());
		newOwner.setLastName(ownerRequest.getLastName());
		newOwner.setAddress(ownerRequest.getAddress());
		newOwner.setPhoneNumber(ownerRequest.getPhoneNumber());
		
		// saving the updated object to database
		ownerService.update(newOwner);
		
		return ResponseEntity.status(HttpStatus.OK).body("Owner updated with id: " + id);
	}
}
