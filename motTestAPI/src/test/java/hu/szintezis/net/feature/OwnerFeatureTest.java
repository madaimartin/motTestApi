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
import hu.szintezis.net.service.OwnerService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class OwnerFeatureTest {
	
	private final static Long TEST_OWNER_ID = 1L;
	private final static String TEST_OWNER_ADDRESS = "1000 Doe street, Test City, Test State";
	private final static String TEST_OWNER_PHONE_NUMBER =  "555-123456789";
	
	@Autowired
	OwnerService ownerService;
	
	@Test
	@Order(1)
	public void createNewOwnerObjectAndSaveToDatabase() {
		// creating the object
		Owner testOwner = new Owner();
		testOwner.setFirstName("John Jeremiah");
		testOwner.setLastName("Doe");
		testOwner.setAddress("266 33rd Ave S # 8, St Cloud, Minnesota 56301, USA");
		testOwner.setPhoneNumber("202-555-0184");
		
		// saving to database
		ownerService.create(testOwner);
		
		Assertions.assertNotNull(testOwner);
	}
	
	@Test
	@Order(2)
	public void createNewOwnerListAndSaveToDatabase() {
		// creating the owner list
		Owner owner1 = new Owner();
		owner1.setFirstName("Elek Elemér");
		owner1.setLastName("Teszt");
		owner1.setAddress("123 Test street, Nekeresd, Hungary");
		owner1.setPhoneNumber("100-555-0122");
		
		Owner owner2 = new Owner();
		owner2.setFirstName("Emma Éva");
		owner2.setLastName("Minta");
		owner2.setAddress("425 Test street, Kukutyin, Hungary");
		owner2.setPhoneNumber("100-555-0122");
		
		List<Owner> ownerList = List.of(owner1, owner2);
		
		// saving to database
		ownerService.saveAll(ownerList);
		
		Assertions.assertNotNull(ownerList);
		Assertions.assertTrue(ownerList.size() >= 2);
		Assertions.assertTrue(ownerList.get(0).getLastName().equals("Teszt"));
	}
	
	@Test
	@Order(3)
	public void readAllOwnersFromDatabase() {
		List<Owner> ownerList = ownerService.findAll();
		Assertions.assertNotNull(ownerList);
		Assertions.assertTrue(ownerList.size() > 0);
	}
	
	@Test
	@Order(4)
	public void readOneOwnerFromDatabase() {
		Owner testOwner = ownerService.findById(TEST_OWNER_ID);
		System.out.println("TEST - Owner data with id "+ TEST_OWNER_ID +" is: " + testOwner);
		Assertions.assertNotNull(testOwner);
	}
	
	@Test
	@Order(5)
	public void testingUpdateWithTestOwner() {
		// updating the address and phone number of a test owner
		Owner testOwner = ownerService.findById(TEST_OWNER_ID);
		testOwner.setAddress(TEST_OWNER_ADDRESS);
		testOwner.setPhoneNumber(TEST_OWNER_PHONE_NUMBER);
		
		ownerService.update(testOwner);
		
		// testing the updated attributes
		Owner ownerFromDatabase = ownerService.findById(TEST_OWNER_ID);
		Assertions.assertNotNull(ownerFromDatabase);
		Assertions.assertEquals(ownerFromDatabase.getAddress(), TEST_OWNER_ADDRESS);
		Assertions.assertEquals(ownerFromDatabase.getPhoneNumber(), TEST_OWNER_PHONE_NUMBER);
	}
}
