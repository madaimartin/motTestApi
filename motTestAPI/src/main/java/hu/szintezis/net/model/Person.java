package hu.szintezis.net.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Person {
	
	/*
	 * Both Owner.java and TesterPerson.java classes are inherited from this Person class.
	 * 
	 * Contains general informations from people
	 */
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=1, message="First name must be at least 1 character long.")
	private String firstName;
	
	@NotNull
	@Size(min=1, message="Last name must be at least 1 character long.")
	private String lastName;
	
	@NotNull
	@Size(min=1, message="Address must be at least 1 character long.")
	private String address;
	
	@NotNull
	@Size(min=1, message="Phone number must be at least 1 character long.")
	private String phoneNumber;
	
	
	public Person(String firstName, String lastName, String address, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	
}
