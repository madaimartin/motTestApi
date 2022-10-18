package hu.szintezis.net.feature;


import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hu.szintezis.net.model.TesterPerson;
import hu.szintezis.net.service.TesterPersonService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TesterPersonFeatureTest {
	
	private final static Long TEST_TESTER_ID = 1L;
	private final static String TEST_TESTER_ADDRESS = "2000 Fake avenue, Mucsaröcsöge City, Hungary";
	private final static String TEST_TESTER_PHONE_NUMBER =  "555-99998888";
	
	@Autowired
	TesterPersonService testerPersonService;
	
	@Test
	@Order(1)
	public void hireNewTesterPersonObjectAndSaveToDatabase() {
		// creating the object
		TesterPerson testerPerson = new TesterPerson();
		testerPerson.setFirstName("Jakab János");
		testerPerson.setLastName("Gipsz");
		testerPerson.setAddress("568 24th Avenue, St Cloud, Minnesota 56301, USA");
		testerPerson.setPhoneNumber("223-555-3322");
		testerPerson.setActive(true);
		
		// saving to database
		testerPersonService.create(testerPerson);
		
		Assertions.assertNotNull(testerPerson);
		Assertions.assertTrue(testerPerson.isActive());
		Assertions.assertTrue(testerPerson.getFirstName().length() > 1);
		Assertions.assertTrue(testerPerson.getLastName().length() > 1);
	}
	
	@Test
	@Order(2)
	public void readAllTesterPeopleFromDatabase() {
		List<TesterPerson> testerPersonList = testerPersonService.findAll();
		Assertions.assertNotNull(testerPersonList);
		Assertions.assertTrue(testerPersonList.size() > 0);
	}
	
	@Test
	@Order(3)
	public void readOneTesterPersonFromDatabase() {
		TesterPerson testerPerson = testerPersonService.findById(TEST_TESTER_ID);
		System.out.println("TEST - TesterPerson data with id "+ TEST_TESTER_ID+" is: " + testerPerson);
		Assertions.assertNotNull(testerPerson);
	}
	
	@Test
	@Order(4)
	public void updateTestTesterPerson() {
		// updating the address and phone number of a test tester person
		TesterPerson testTesterPerson = testerPersonService.findById(TEST_TESTER_ID);
		testTesterPerson.setAddress(TEST_TESTER_ADDRESS);
		testTesterPerson.setPhoneNumber(TEST_TESTER_PHONE_NUMBER);
		testTesterPerson.setActive(true);
		
		testerPersonService.update(testTesterPerson);
		
		// testing the updated attributes
		TesterPerson testerPersonFromDatabase = testerPersonService.findById(TEST_TESTER_ID);
		Assertions.assertNotNull(testerPersonFromDatabase);
		Assertions.assertEquals(testerPersonFromDatabase.getAddress(), TEST_TESTER_ADDRESS);
		Assertions.assertEquals(testerPersonFromDatabase.getPhoneNumber(), TEST_TESTER_PHONE_NUMBER);
		Assertions.assertTrue(testerPersonFromDatabase.isActive());
		Assertions.assertTrue(testerPersonFromDatabase.getFirstName().length() > 1);
		Assertions.assertTrue(testerPersonFromDatabase.getLastName().length() > 1);
	}
	
	@Test
	@Order(5)
	public void dismissTesterPerson() {
		TesterPerson testTesterPerson = testerPersonService.findById(TEST_TESTER_ID);
		
		testTesterPerson.setActive(false);
		testerPersonService.update(testTesterPerson);
		
		TesterPerson testerPersonFromDatabase = testerPersonService.findById(TEST_TESTER_ID);
		Assertions.assertNotNull(testerPersonFromDatabase);
		Assertions.assertFalse(testerPersonFromDatabase.isActive());
	}
}
