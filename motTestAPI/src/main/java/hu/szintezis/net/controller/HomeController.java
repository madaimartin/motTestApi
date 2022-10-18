package hu.szintezis.net.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	/*
	 * Reading the server port from the application.properties file
	 * By default: 8080
	 */
	
	@Value("${server.port}")
	private String serverPort;

	/*
	 * Simple home page for testing the API
	 */
	@GetMapping("/")
	public ResponseEntity<String> homePage() {
		String message = "API is running. View documentation:<br>"
				+ "<a >http://localhost:"+serverPort+"/swagger-ui.html</a>";
		return ResponseEntity.ok(message);
	}
}
