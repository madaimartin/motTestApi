package hu.szintezis.net.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.szintezis.net.model.TesterPerson;

public interface TesterPersonRepository extends JpaRepository<TesterPerson, Long>{

}
