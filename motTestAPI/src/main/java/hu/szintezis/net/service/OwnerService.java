package hu.szintezis.net.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.szintezis.net.exception.ResourceNotFoundException;
import hu.szintezis.net.model.Owner;
import hu.szintezis.net.repository.OwnerRepository;

@Service
public class OwnerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OwnerService.class);
	
	@Autowired
	OwnerRepository ownerRepo;

	public List<Owner> findAll() {
		LOGGER.info("Listed all owners.");
		return ownerRepo.findAll();
	}
	
	public Owner findById(Long id){
		Optional<Owner> owner = ownerRepo.findById(id);
	    if(!owner.isPresent()) {
	    	String errorMessage = "Could not find owner with id: " + id;
	    	LOGGER.error(errorMessage);
	    	throw new ResourceNotFoundException(errorMessage);
	    }
	    return owner.get();
	}
	
	public void create(Owner owner) {
		ownerRepo.save(owner);
		LOGGER.info("New owner saved to database.");
	}
	
	public void saveAll(List<Owner> ownerList) {
		ownerRepo.saveAll(ownerList);
		LOGGER.info("A list of new owners were saved to database.");
	}
	
	public void update(Owner owner) {
		if(owner.getId() != null) {
			ownerRepo.save(owner);
			LOGGER.info("Owner updated with id: " + owner.getId());
		} else {
			LOGGER.warn("Owner update failed. Owner object: " + owner);
		}
	}
	
	
}
