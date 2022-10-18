package hu.szintezis.net.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.szintezis.net.exception.ResourceNotFoundException;
import hu.szintezis.net.model.MotTest;
import hu.szintezis.net.repository.MotTestRepository;

@Service
public class MotTestService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MotTestService.class);

	@Autowired
	MotTestRepository motTestRepo;

	public List<MotTest> findAll(){
		LOGGER.info("Listed all MOT Tests.");
		return motTestRepo.findAll();
	}
	
	public MotTest findById(Long id){
		Optional<MotTest> motTest = motTestRepo.findById(id);
	    if(!motTest.isPresent()) {
	    	String errorMessage = "Could not find MOT test with id: " + id;
	    	LOGGER.error(errorMessage);
	    	throw new ResourceNotFoundException(errorMessage);
	    }
	    return motTest.get();
	}
	
	public void create(MotTest motTest) {
		// A MOT test takes an hour.
		LocalDateTime now = LocalDateTime.now();
		motTest.setInspectionStartingDate(now);
		motTest.setInspectionDate(now.plusHours(1));
				
		// The MOT test will fail with a 15% chance
		int randomNumber = (int) (Math.random() * (100 - 1)) + 1;
		int failurePercentage = 15;
		boolean succeeded = randomNumber >= failurePercentage;
		
		motTest.setSucceeded(succeeded);
		
		motTestRepo.save(motTest);
		LOGGER.info("New MOT Test saved to database.");
	}
}
