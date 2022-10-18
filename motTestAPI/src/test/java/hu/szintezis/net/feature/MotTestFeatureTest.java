package hu.szintezis.net.feature;


import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hu.szintezis.net.model.MotTest;
import hu.szintezis.net.model.MotTestCenter;
import hu.szintezis.net.model.TesterPerson;
import hu.szintezis.net.model.Vehicle;
import hu.szintezis.net.service.MotTestCenterService;
import hu.szintezis.net.service.MotTestService;
import hu.szintezis.net.service.TesterPersonService;
import hu.szintezis.net.service.VehicleService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MotTestFeatureTest {
	
	private final static Long TEST_MOT_TEST_ID = 1L;
	private final static Long TEST_TESTER_PERSON_ID = 1L;
	private final static Long TEST_VEHICLE_ID = 1L;
	private final static Long TEST_MOT_TEST_CENTER_ID = 1L;
	
	private final static LocalDateTime TEST_DATETIME = LocalDateTime.now();
	
	@Autowired
	MotTestService motTestService;
	
	@Autowired
	TesterPersonService testerPersonService;
	
	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	MotTestCenterService motTestCenterService;
	
	@Test
	@Order(1)
	public void createNewMOTTestObjectAndSaveToDatabase() {
		// Read a test vehicle, a tester person and a test center from the database
		Vehicle vehicle = vehicleService.findById(TEST_VEHICLE_ID);
		TesterPerson testerPerson = testerPersonService.findById(TEST_TESTER_PERSON_ID);
		MotTestCenter motTestCenter = motTestCenterService.findById(TEST_MOT_TEST_CENTER_ID);
		
		// creating the object
		MotTest motTest = new MotTest();
		motTest.setVehicle(vehicle);
		motTest.setTesterPerson(testerPerson);
		motTest.setMotTestCenter(motTestCenter);
		
		motTest.setSucceeded(true);
		motTest.setInspectionStartingDate(TEST_DATETIME);
		motTest.setInspectionDate(TEST_DATETIME.plusHours(1));
		
		// saving to database
		motTestService.create(motTest);
		
		Assertions.assertNotNull(vehicle);
		Assertions.assertNotNull(testerPerson);
		Assertions.assertNotNull(motTestCenter);
		
		Assertions.assertTrue(motTest.getVehicle().equals(vehicle));
		Assertions.assertTrue(motTest.getTesterPerson().equals(testerPerson));
		Assertions.assertTrue(motTest.getMotTestCenter().equals(motTestCenter));
		
		Assertions.assertNotNull(motTest);
		Assertions.assertNotNull(motTest.isSucceeded());
		Assertions.assertNotNull(motTest.getInspectionDate());
		Assertions.assertTrue(motTest.getInspectionDate().toString().length() > 4);
	}
	
	@Test
	@Order(2)
	public void readAllMOTTestsFromDatabase() {
		List<MotTest> motTestList = motTestService.findAll();
		Assertions.assertNotNull(motTestList);
		Assertions.assertTrue(motTestList.size() > 0);
	}
	
	@Test
	@Order(3)
	public void readOneMOTTestFromDatabase() {
		MotTest motTest = motTestService.findById(TEST_MOT_TEST_ID);
		System.out.println("TEST - Vehicle inspection data with id "+ TEST_MOT_TEST_ID+" is: " + motTest);
		Assertions.assertNotNull(motTest);
		Assertions.assertNotNull(motTest.getVehicle());
		Assertions.assertNotNull(motTest.getTesterPerson());
		Assertions.assertNotNull(motTest.getMotTestCenter());
		Assertions.assertNotNull(motTest.getInspectionDate());
		Assertions.assertNotNull(motTest.isSucceeded());
	}
}
