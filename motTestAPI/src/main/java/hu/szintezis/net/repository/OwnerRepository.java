package hu.szintezis.net.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.szintezis.net.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long>{

}
