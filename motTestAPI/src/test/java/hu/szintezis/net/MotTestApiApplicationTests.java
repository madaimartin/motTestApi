package hu.szintezis.net;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import hu.szintezis.net.feature.AllFeatureTests;


@SpringBootTest
@Suite
@SelectClasses({ 
	HttpRequestTest.class, 
	ControllersTest.class, 
	AllFeatureTests.class,
})
class MotTestApiApplicationTests {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MotTestApiApplicationTests.class);

	@Test
	void contextLoads() {
		LOGGER.info("Context loaded");
		
	}


}
