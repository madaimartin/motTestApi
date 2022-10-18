package hu.szintezis.net;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hu.szintezis.net.controller.MotTestController;
import hu.szintezis.net.controller.OwnerController;
import hu.szintezis.net.controller.TesterPersonController;
import hu.szintezis.net.controller.VehicleController;

@SpringBootTest
public class ControllersTest {

	/*
	 * Testing the dependency injection
	 * controllers must not be null.
	 */
	
	@Autowired
	TesterPersonController testerPersonController;
	
	@Autowired
	OwnerController ownerController;
	
	@Autowired
	VehicleController vehicleController;
	
	@Autowired
	MotTestController mostTestController;
	
	
	@Test
	public void controllersAreLoaded() throws Exception {
		Assertions.assertThat(testerPersonController).isNotNull();
		Assertions.assertThat(ownerController).isNotNull();
		Assertions.assertThat(vehicleController).isNotNull();
		Assertions.assertThat(mostTestController).isNotNull();
	}
}
