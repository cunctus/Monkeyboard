/**
 * @project Monkeyboard
 * @author  Christopher Rogoza
 * @email   christopher@cunctus.de
 * @package de.cunctus.Monkeyboard.Enums
 * @version 
 * @since   05.03.2015
 *
 */
package de.cunctus.Monkeyboard.Enums;

public enum PlayStatus {

	FUNCTION_FAILED(-1, "Function failed"), PLAYING(0,"Playing"), SEARCHING(1,"Searching"), TUNING(2,"Tuning"), STOP(3,"Stop"), SORTING(4,"Sorting"), RECONFIGURING(5,"Reconfiguring"), INVALID(6,"Invalid");
	
	private int value;
	private String status;
	
	private PlayStatus(int value, String status) {
		this.value = value;
		this.status = status;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getStatus() {
		return status;
	}
	
	public static PlayStatus getByValue(int value) {
		for(PlayStatus status : PlayStatus.values()) {
			if (status.getValue() == value)
				return status;
		}
		return PlayStatus.INVALID;
	}
}
