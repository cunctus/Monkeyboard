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

public enum PlayMode {
	
	DAB(0, "DAB"), FM(1, "FM"), INVALID(3, "Invalid"), FUNCTION_FAILED(-1,
			"Function failed");

	private int value;
	private String mode;

	private PlayMode(int value, String mode) {
		this.value = value;
		this.mode = mode;
	}

	public int getValue() {
		return this.value;
	}
	
	public String getMode() {
		return this.mode;
	}
	
	public static PlayMode getByValue(int value) {
		for(PlayMode mode : PlayMode.values()) {
			if (mode.getValue() == value)
				return mode;
		}
		return PlayMode.INVALID;
	}
}
