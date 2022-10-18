package hu.szintezis.net.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.szintezis.net.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
	
}
