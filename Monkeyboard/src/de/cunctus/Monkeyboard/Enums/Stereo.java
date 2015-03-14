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

public enum Stereo {

	FUNCTION_FAILED(-1, "Function failed"), STEREO(0, "Stereo"), JOINT_STEREO(
			1, "Joint stereo"), DUAL_CHANNEL(2, "Dual channel"), SINGLE_CHANNEL(
			3, "Single channel (mono)"), INVALID(4, "Invalid");

	private int value;
	private String mode;

	private Stereo(int value, String mode) {
		this.value = value;
		this.mode = mode;
	}

	public int getValue() {
		return this.value;
	}

	public String getStereo() {
		return this.mode;
	}

	public static Stereo getByValue(int value) {
		for (Stereo mode : Stereo.values()) {
			if (mode.getValue() == value)
				return mode;
		}
		return Stereo.INVALID;
	}
}
