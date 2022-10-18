package hu.szintezis.net.model;

import javax.persistence.Entity;

import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor
public class Owner extends Person {
	
	public Owner(String firstName, String lastName, String address, String phoneNumber) {
		super(firstName, lastName, address, phoneNumber);
	}
	
	
}
