package hu.szintezis.net;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
	
	private final static String TEST_USERNAME = "user";
	private final static String TEST_PASSWORD = "password";
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	/*
	 * Checking the successful application start on the local server port
	 */
	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		Assertions.assertThat(
			this
			.restTemplate
			.withBasicAuth(TEST_USERNAME, TEST_PASSWORD)
			.getForObject("http://localhost:" + port + "/", String.class)
		).contains("API is running");
	}
}
