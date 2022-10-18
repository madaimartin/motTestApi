package hu.szintezis.net.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class MotTestRequest {
	
	/*
	 * Holder class for connecting the vehicle with the inspector in requests.
	 */
	
	private Long vehicleId;
	private Long testerPersonId;
	private Long motTestCenterId;
	
	
}
