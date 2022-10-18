package hu.szintezis.net.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class MotTestCenter {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=3, message="Last name must be at least 3 characters long.")
	private String name;
	
	@NotNull
	@Size(min=10, message="Address must be at least 10 characters long.")
	private String address;

	public MotTestCenter(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	
}
