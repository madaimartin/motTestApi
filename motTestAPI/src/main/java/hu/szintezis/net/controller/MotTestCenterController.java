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

import hu.szintezis.net.model.MotTestCenter;
import hu.szintezis.net.service.MotTestCenterService;

@RestController
@Validated
@RequestMapping("/api/mot-test-centers")
public class MotTestCenterController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MotTestCenterController.class);
	
	@Autowired
	MotTestCenterService motTestCenterService;

	/*
	 * GET: /api/mot-test-centers/
	 */
	@GetMapping("/")
	public ResponseEntity<List<MotTestCenter>> listMotTestCenters(){
		LOGGER.info("MotTestCenterController - listMotTestCenters()");
		
		List<MotTestCenter> motTestCenterList = motTestCenterService.findAll();
		return ResponseEntity.ok(motTestCenterList);
		
	}
	
	/*
	 * GET: /api/mot-test-centers/{id}
	 */
	@GetMapping("/{id}")
	public ResponseEntity<MotTestCenter> findById(@PathVariable("id") Long id) {
	    LOGGER.info("MotTestCenterController - find by id: " + id);
	    
	    MotTestCenter motTestCenter = motTestCenterService.findById(id);
	    return ResponseEntity.ok(motTestCenter);
	    
	}
	
	/*
	 * POST: /api/mot-test-centers/create
	 */
	@PostMapping("/create")
	public ResponseEntity<String> createNewMotTestCenter( @Valid @RequestBody  MotTestCenter motTestCenter){
		LOGGER.info("MotTestCenterController - creating new motTestCenter");
		
		// Saving the motTestCenter object to the database
		motTestCenterService.create(motTestCenter);
		return ResponseEntity.status(HttpStatus.CREATED).body("MotTestCenter created successfully.");
	}
	
	/*
	 * PUT: /api/mot-test-centers/{id}
	 */
	@PutMapping("/{id}")
	public ResponseEntity<String> updateMotTestCenter(
			@PathVariable("id") Long id,
			@Valid @RequestBody MotTestCenter motTestCenterRequest){
		LOGGER.info("MotTestCenterController - updating motTestCenter");
		
		// Retrieving motTestCenter data from the database by id 
		MotTestCenter motTestCenter = motTestCenterService.findById(id);
		
		// Updating motTestCenter object with the new data
		MotTestCenter newMotTestCenter = motTestCenter;
		newMotTestCenter.setId(id);
		newMotTestCenter.setName(motTestCenterRequest.getName());
		newMotTestCenter.setAddress(motTestCenterRequest.getAddress());
		
		// saving the updated object to database
		motTestCenterService.update(newMotTestCenter);
		
		return ResponseEntity.status(HttpStatus.OK).body("MotTestCenter updated with id: " + id);
	}
}
