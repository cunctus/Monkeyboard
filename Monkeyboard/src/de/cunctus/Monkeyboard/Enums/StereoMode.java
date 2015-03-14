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

public enum StereoMode {

	FUNCTION_FAILED(-1, "Function failed"), FORCED_MONO(0, "Forced mono"), AUTO_STEREO(
			1, "Auto stereo");
	
	private int value;
	private String mode;

	private StereoMode(int value, String mode) {
		this.value = value;
		this.mode = mode;
	}

	public int getValue() {
		return this.value;
	}
	
	public String getMode() {
		return this.mode;
	}
	
	public static StereoMode getByValue(int value) {
		for(StereoMode mode : StereoMode.values()) {
			if (mode.getValue() == value)
				return mode;
		}
		return StereoMode.FUNCTION_FAILED;
	}
}
