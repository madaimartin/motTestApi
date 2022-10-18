package hu.szintezis.net.feature;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hu.szintezis.net.model.MotTestCenter;
import hu.szintezis.net.service.MotTestCenterService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MotTestCenterFeatureTest {
	
	private final static Long TEST_MOT_TEST_CENTER_ID = 1L;
	private final static String TEST_MOT_TEST_CENTER_NAME = "Lorem Ipsum Test Center of Győr";
	private final static String TEST_MOT_TEST_CENTER_ADDRESS = "9000 Dolor Street, Győr City, Hungary";
	
	@Autowired
	MotTestCenterService motTestCenterService;
	
	@Test
	@Order(1)
	public void createNewMotTestCenterObjectAndSaveToDatabase() {
		// creating the object
		MotTestCenter testMotTestCenter = new MotTestCenter();
		testMotTestCenter.setName("Beautiful Test Center Name ");
		testMotTestCenter.setAddress("Teszt Utca 1, Kukutyin 1234, Hungary");
		
		// saving to database
		motTestCenterService.create(testMotTestCenter);
		
		Assertions.assertNotNull(testMotTestCenter);
	}
	
	@Test
	@Order(2)
	public void createNewMotTestCenterListAndSaveToDatabase() {
		// creating the list
		MotTestCenter center1 = new MotTestCenter();
		center1.setName("Car Sample Super Name ");
		center1.setAddress("Teszt Utca 26, Kukutyin 1234, Hungary");
		
		MotTestCenter center2 = new MotTestCenter();
		center2.setName("Modern MOT Center of Hungary ");
		center2.setAddress("Teszt tér 35, Kukutyin 1234, Hungary");
		
		List<MotTestCenter> motTestCenterList = List.of(center1, center2);
		
		// saving to database
		motTestCenterService.saveAll(motTestCenterList);
		
		Assertions.assertNotNull(motTestCenterList);
		Assertions.assertTrue(motTestCenterList.size() >= 2);
	}
	
	@Test
	@Order(3)
	public void readAllMotTestCentersFromDatabase() {
		List<MotTestCenter> motTestCenterList = motTestCenterService.findAll();
		Assertions.assertNotNull(motTestCenterList);
		Assertions.assertTrue(motTestCenterList.size() > 0);
	}
	
	@Test
	@Order(4)
	public void readOneMotTestCenterFromDatabase() {
		MotTestCenter testMotTestCenter = motTestCenterService.findById(TEST_MOT_TEST_CENTER_ID);
		System.out.println("TEST - MotTestCenter data with id "+ TEST_MOT_TEST_CENTER_ID +" is: " + testMotTestCenter);
		Assertions.assertNotNull(testMotTestCenter);
	}
	
	@Test
	@Order(5)
	public void testingUpdateWithTestMotTestCenter() {
		// updating the address and phone number of a test motTestCenter
		MotTestCenter testMotTestCenter = motTestCenterService.findById(TEST_MOT_TEST_CENTER_ID);
		testMotTestCenter.setAddress(TEST_MOT_TEST_CENTER_ADDRESS);
		testMotTestCenter.setName(TEST_MOT_TEST_CENTER_NAME);
		
		motTestCenterService.update(testMotTestCenter);
		
		// testing the updated attributes
		MotTestCenter motTestCenterFromDatabase = motTestCenterService.findById(TEST_MOT_TEST_CENTER_ID);
		Assertions.assertNotNull(motTestCenterFromDatabase);
		Assertions.assertEquals(motTestCenterFromDatabase.getAddress(), TEST_MOT_TEST_CENTER_ADDRESS);
		Assertions.assertEquals(motTestCenterFromDatabase.getName(), TEST_MOT_TEST_CENTER_NAME);
	}
}

