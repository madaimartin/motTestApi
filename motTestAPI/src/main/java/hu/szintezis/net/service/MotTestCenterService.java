package hu.szintezis.net.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.szintezis.net.exception.ResourceNotFoundException;
import hu.szintezis.net.model.MotTestCenter;
import hu.szintezis.net.repository.MotTestCenterRepository;

@Service
public class MotTestCenterService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MotTestCenterService.class);
	
	@Autowired
	MotTestCenterRepository motTestCenterRepo;

	public List<MotTestCenter> findAll(){
		LOGGER.info("Listed all MOT Test Centers.");
		return motTestCenterRepo.findAll();
	}
	
	public MotTestCenter findById(Long id){
		Optional<MotTestCenter> motTestCenter = motTestCenterRepo.findById(id);
	    if(!motTestCenter.isPresent()) {
	    	String errorMessage = "Could not find motTestCenter with id: " + id;
	    	LOGGER.error(errorMessage);
	    	throw new ResourceNotFoundException(errorMessage);
	    }
	    return motTestCenter.get();
	}
	
	public void create(MotTestCenter motTestCenter) {
		motTestCenterRepo.save(motTestCenter);
		LOGGER.info("New MOT Test center saved to database.");
	}
	
	public void saveAll(List<MotTestCenter> motTestCenterList) {
		motTestCenterRepo.saveAll(motTestCenterList);
		LOGGER.info("A list of new MOT Test Centers were saved to database.");
	}
	
	public void update(MotTestCenter motTestCenter) {
		if(motTestCenter.getId() != null) {
			motTestCenterRepo.save(motTestCenter);
			LOGGER.info("MOT Test center updated with id: " + motTestCenter.getId());
		} else {
			LOGGER.warn("MOT Test center update failed. motTestCenter object: " + motTestCenter);
		}
	}
	
	
}
