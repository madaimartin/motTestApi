package hu.szintezis.net.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class VehicleOwnerConnectionRequest {
	
	/*
	 * Container class with following purpose: 
	 * - assigning a vehicle with the owner in requests.
	 */

	private Long vehicleId;
	private Long ownerId;
	
}
