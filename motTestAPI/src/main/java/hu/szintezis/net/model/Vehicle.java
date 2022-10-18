package hu.szintezis.net.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 3, message = "Brand must be at least 3 characters long.")
	private String brand;
	
	@NotNull
	@Size(min = 1, message = "Model must be at least 1 character long.")
	private String model;
	
	@NotNull
	@Size(min = 3, message = "Color of the vehicle be at least 3 charactera long.")
	private String color;
	
	@NotNull
	@Range(min = 1950, max = 2022, message = "Year of manufacture must be between 1950 and 2022.")
	private int yearOfManufacture;
	
	@ManyToOne
    @JoinColumn(name = "owner_id", insertable = false)
	private Owner owner;
	
	private LocalDateTime validityOfMotTest;

	
}
