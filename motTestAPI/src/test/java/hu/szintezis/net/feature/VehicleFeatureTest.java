package hu.szintezis.net.feature;


import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hu.szintezis.net.model.Owner;
import hu.szintezis.net.model.Vehicle;
import hu.szintezis.net.service.OwnerService;
import hu.szintezis.net.service.VehicleService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class VehicleFeatureTest {
	
	private final static Long TEST_VEHICLE_ID = 1L;
	private final static String TEST_VEHICLE_COLOR = "yellow";
	
	private final static Long TEST_OWNER_ID = 1L;
	
	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	OwnerService ownerService;
	
	@Test
	@Order(1)
	public void createNewVehicleObjectAndSaveToDatabase() {
		// creating the object
		Vehicle testVehicle = new Vehicle();
		testVehicle.setBrand("Audi");
		testVehicle.setModel("A Test");
		testVehicle.setColor("black");
		testVehicle.setYearOfManufacture(2018);
		
		// saving to database
		vehicleService.create(testVehicle);
		
		Assertions.assertNotNull(testVehicle);
	}
	
	@Test
	@Order(1)
	public void createNewVehicleListAndSaveToDatabase() {
		// creating the object
		Vehicle vehicle1 = new Vehicle();
		vehicle1.setBrand("BMW");
		vehicle1.setModel("T123");
		vehicle1.setColor("black");
		vehicle1.setYearOfManufacture(2021);
		
		Vehicle vehicle2 = new Vehicle();
		vehicle2.setBrand("Suzuki");
		vehicle2.setModel("SU");
		vehicle2.setColor("grey");
		vehicle2.setYearOfManufacture(2019);
		
		List<Vehicle> vehicleList = List.of(vehicle1, vehicle2);
		
		// saving to database
		vehicleService.saveAll(vehicleList);
		
		Assertions.assertNotNull(vehicleList);
		Assertions.assertTrue(vehicleList.size() >= 2);
		Assertions.assertTrue(vehicleList.get(0).getBrand().equals("BMW"));
		Assertions.assertTrue(vehicleList.get(1).getBrand().equals("Suzuki"));
	}
	
	@Test
	@Order(3)
	public void readAllVehiclesFromDatabase() {
		List<Vehicle> vehicleList = vehicleService.findAll();
		Assertions.assertTrue(vehicleList != null && vehicleList.size() > 0);
	}
	
	@Test
	@Order(4)
	public void readOneVehicleFromDatabase() {
		Vehicle testVehicle = vehicleService.findById(TEST_VEHICLE_ID);
		System.out.println("TEST - Vehicle data with id "+ TEST_VEHICLE_ID +" is: " + testVehicle);
		Assertions.assertNotNull(testVehicle);
	}
	
	@Test
	@Order(5)
	public void updateTestVehicle() {
		// updating the color of a test vehicle
		Vehicle testVehicle = vehicleService.findById(TEST_VEHICLE_ID);
		testVehicle.setColor(TEST_VEHICLE_COLOR);
		
		vehicleService.update(testVehicle);
		
		// testing the updated attributes
		Vehicle vehicleFromDatabase = vehicleService.findById(TEST_VEHICLE_ID);
		Assertions.assertNotNull(vehicleFromDatabase);
		Assertions.assertEquals(vehicleFromDatabase.getColor(), TEST_VEHICLE_COLOR);
		Assertions.assertNotEquals(vehicleFromDatabase.getColor(), "fake color");
	}
	
	@Test
	@Order(6)
	public void connectVehicleToOwerTest() {
		Vehicle testVehicle = vehicleService.findById(TEST_VEHICLE_ID);
		Owner testOwner = ownerService.findById(TEST_OWNER_ID);
		
		testVehicle.setOwner(testOwner);
		
		vehicleService.update(testVehicle);
		
		Vehicle vehicleFromDatabase = vehicleService.findById(TEST_VEHICLE_ID);
		Owner ownerFromDatabase = ownerService.findById(vehicleFromDatabase.getOwner().getId());
		
		Assertions.assertNotNull(vehicleFromDatabase);
		Assertions.assertNotNull(ownerFromDatabase);
		Assertions.assertEquals(vehicleFromDatabase.getOwner(), ownerFromDatabase);
		
	}
}
