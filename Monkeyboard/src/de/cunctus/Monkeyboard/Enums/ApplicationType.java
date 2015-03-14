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

public enum ApplicationType {

	MOT_SLIDESHOW(0, "MOT Slideshow"), MOT_BWS(1, "MOT BWS"), TPEG(2, "TPEG"), DPGS(
			3, "DPGS"), TMC(4, "TMC"), EPG(5, "EPG"), DAB_JAVA(6, "DAB Java"), DMB(
			7, "DMB"), PUSH_RADIO(8, "Push Radio");

	private int value;
	private String type;

	private ApplicationType(int value, String type) {
		this.value = value;
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public String getType() {
		return type;
	}

	public static ApplicationType getByValue(int value) {
		for (ApplicationType type : ApplicationType.values()) {
			if (type.getValue() == value)
				return type;
		}
		return ApplicationType.MOT_SLIDESHOW;
	}

}
