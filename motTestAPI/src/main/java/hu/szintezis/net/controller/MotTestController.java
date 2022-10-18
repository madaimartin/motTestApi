package hu.szintezis.net.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.szintezis.net.model.MotTest;
import hu.szintezis.net.model.MotTestCenter;
import hu.szintezis.net.model.TesterPerson;
import hu.szintezis.net.model.Vehicle;
import hu.szintezis.net.request.MotTestRequest;
import hu.szintezis.net.service.MotTestCenterService;
import hu.szintezis.net.service.MotTestService;
import hu.szintezis.net.service.TesterPersonService;
import hu.szintezis.net.service.VehicleService;

@RestController
@Validated
@RequestMapping("/api/mot-tests")
public class MotTestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MotTestController.class);

	@Autowired
	MotTestService motTestService;
	
	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	TesterPersonService testerPersonService;
	
	@Autowired
	MotTestCenterService motTestCenterService;
	
	/*
	 * GET: /api/mot-tests/
	 */
	@GetMapping("/")
	public ResponseEntity<List<MotTest>> listAllMotTests(){
		LOGGER.info("MotTestController - listAllMotTests()");
		
		List<MotTest> mostTestList = motTestService.findAll();
		return ResponseEntity.ok(mostTestList);
		
	}
	
	/*
	 * GET: /api/mot-tests/{id}
	 */
	@GetMapping("/{id}")
	public ResponseEntity<MotTest> findById(@PathVariable("id") Long id) {
	    LOGGER.info("MotTestController - find by id: " + id);
	    
	    MotTest motTest = motTestService.findById(id);
		return ResponseEntity.ok(motTest);
	    
	}
	
	/*
	 * POST: /api/mot-tests/create
	 */
	@PostMapping("/create")
	public ResponseEntity<String> createNewMotTest( 
		@RequestBody MotTestRequest motTestRequest
	){
		LOGGER.info("MotTestController - createNewMotTest()");
		
		// The motTestRequest object contains the necessary id-s.
		// Retrieving vehicle, testerPerson and motTestCenter objects from the database by id 
		Vehicle vehicle = vehicleService.findById(motTestRequest.getVehicleId());
		TesterPerson testerPerson = testerPersonService.findById(motTestRequest.getTesterPersonId());
		MotTestCenter motTestCenter = motTestCenterService.findById(motTestRequest.getMotTestCenterId());
		
		// Create new MOT test object
		MotTest motTest = new MotTest();
		
		motTest.setVehicle(vehicle);
		motTest.setTesterPerson(testerPerson);
		motTest.setMotTestCenter(motTestCenter);
		
		// Updating the validity of inspection of the vehicle object and save to database.
		vehicle.setValidityOfMotTest(LocalDateTime.now().plusYears(2));
		vehicleService.update(vehicle);
		
		// save the MOT Test object to the database
		motTestService.create(motTest);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body("MOT Test added. VehicleID: " + motTestRequest.getVehicleId()
				+". InspectorPersonID: " + motTestRequest.getTesterPersonId()
				+". MotTestCenterId: " + motTestRequest.getMotTestCenterId()
		);
		
	}
}
