package hu.szintezis.net.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MotTest {
	
	/*
	 * MOT = Ministry of Transport test
	 * 
	 * Contains: 
	 * - Which inspector inspected which vehicle in which test center.
	 * - date time of inspection
	 * - success
	 */
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "vehicle_id", referencedColumnName = "id")
	private Vehicle vehicle;
	
	@ManyToOne
	@JoinColumn(name = "tester_person_id", referencedColumnName = "id")
	private TesterPerson testerPerson;
	
	@ManyToOne
	@JoinColumn(name = "mot_test_center_id", referencedColumnName = "id")
	private MotTestCenter motTestCenter;
	
	private LocalDateTime inspectionStartingDate;
	private LocalDateTime inspectionDate;
	
	private boolean succeeded;

	
	
}
