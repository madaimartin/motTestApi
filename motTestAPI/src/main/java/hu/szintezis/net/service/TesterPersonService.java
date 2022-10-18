package hu.szintezis.net.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.szintezis.net.exception.ResourceNotFoundException;
import hu.szintezis.net.model.TesterPerson;
import hu.szintezis.net.repository.TesterPersonRepository;

@Service
public class TesterPersonService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TesterPersonService.class);

	@Autowired
	TesterPersonRepository testerPersonRepo;

	public List<TesterPerson> findAll(){
		LOGGER.info("Listed all testers");
		return testerPersonRepo.findAll();
	}
	
	public TesterPerson findById(Long id){
		Optional<TesterPerson> testerPerson = testerPersonRepo.findById(id);
	    if(!testerPerson.isPresent()) {
	    	String errorMessage = "Could not find tester person with id: " + id;
	    	LOGGER.error(errorMessage);
	    	throw new ResourceNotFoundException(errorMessage);
	    }
	    return testerPerson.get();
	}
	
	public void create(TesterPerson testerPerson) {
		testerPersonRepo.save(testerPerson);
		LOGGER.info("HIRED! New tester person saved to database");
	}
	
	public void saveAll(List<TesterPerson> testerPersonList) {
		testerPersonRepo.saveAll(testerPersonList);
		LOGGER.info("A list of new tester persons were saved to database.");
	}
	
	public void update(TesterPerson testerPerson) {
		if(testerPerson.getId() != null) {
			testerPersonRepo.save(testerPerson);
			LOGGER.info("Tester person updated with id: " + testerPerson.getId());
		} else {
			LOGGER.warn("Tester person update failed - " + testerPerson);
		}
	}
}
