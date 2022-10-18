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

import hu.szintezis.net.model.TesterPerson;
import hu.szintezis.net.service.OwnerService;
import hu.szintezis.net.service.TesterPersonService;
import hu.szintezis.net.service.VehicleService;

@RestController
@Validated
@RequestMapping("/api/testers")
public class TesterPersonController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TesterPersonController.class);
	
	@Autowired
	TesterPersonService testerPersonService;

	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	OwnerService ownerService;

	/*
	 * GET: /api/testers/
	 */
	@GetMapping("/")
	public ResponseEntity<List<TesterPerson>> listAllTesterPersons(){
		LOGGER.info("TesterPersonController - listTesterPersons()");
		
		List<TesterPerson> testerPersonList = testerPersonService.findAll();
		return ResponseEntity.ok(testerPersonList);
		
	}
	
	/*
	 * GET: /api/testers/{id}
	 */
	@GetMapping("/{id}")
	public ResponseEntity<TesterPerson> findById(@PathVariable("id") Long id) {
	    LOGGER.info("TesterPersonController - finding by id: " + id);
	    
	    TesterPerson testerPerson = testerPersonService.findById(id);
		return ResponseEntity.ok(testerPerson);
	    
	}
	
	/*
	 * POST: /api/testers/hire
	 */
	@PostMapping("/hire")
	public ResponseEntity<String> hireTesterPerson( @Valid @RequestBody TesterPerson testerPerson){
		LOGGER.info("TesterPersonController - hireTesterPerson()");
		
		testerPersonService.create(testerPerson);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body("Tester person hired successfully.");
	}
	
	/*
	 * POST: /api/testers/dismiss/{id}
	 * 
	 * Dismiss = updating "active" status to false
	 */
	@PostMapping("/dismiss/{id}")
	public ResponseEntity<String> dismissTesterPerson( @PathVariable("id") Long id){
		LOGGER.info("TesterPersonController - dismissTesterPerson()");
		TesterPerson testerPerson = testerPersonService.findById(id);
		
		// Setting active attribute to false then save to database.
		TesterPerson testerPersonToSave = testerPerson;
		testerPersonToSave.setActive(false);
		
		testerPersonService.update(testerPersonToSave);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body("Tester person dismissed successfully. Id: " + id);
	}
	
	/*
	 * PUT: /api/inspectors/{id}
	 */
	@PutMapping("/{id}")
	public ResponseEntity<String> updateTesterPerson(
			@PathVariable("id") Long id,
			@Valid @RequestBody TesterPerson testerPersonRequest){
		LOGGER.info("TesterPersonController - updating testerPerson");
		
		// Retrieving owner data from the database by id 
		TesterPerson testerPerson = testerPersonService.findById(id);
		
		// Updating owner object with the new data
		TesterPerson newTesterPerson = testerPerson;
		newTesterPerson.setId(id);
		newTesterPerson.setFirstName(testerPersonRequest.getFirstName());
		newTesterPerson.setLastName(testerPersonRequest.getLastName());
		newTesterPerson.setAddress(testerPersonRequest.getAddress());
		newTesterPerson.setPhoneNumber(testerPersonRequest.getPhoneNumber());

		// save the updated object to database
		testerPersonService.update(newTesterPerson);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("MOT Tester person updated with id: " + id);
	}
}
