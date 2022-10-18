package hu.szintezis.net.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
public class TesterPerson extends Person {
	
	@NotNull
	private boolean active = true;
	
	public TesterPerson(String firstName, String lastName, String address, String phoneNumber) {
		super(firstName, lastName, address, phoneNumber);
		this.active = true;
	}

}
