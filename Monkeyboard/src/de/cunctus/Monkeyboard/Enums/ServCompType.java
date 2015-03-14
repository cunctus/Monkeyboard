/**
 * @project Monkeyboard
 * @author  Christopher Rogoza
 * @email   christopher@cunctus.de
 * @package de.cunctus.Monkeyboard.Enums
 * @version 
 * @since   06.03.2015
 *
 */
package de.cunctus.Monkeyboard.Enums;

public enum ServCompType {
	DAB(0, "DAB"), DABPlus(1, "DAB+"), PACKET_DATA(2, "Packet Data"), DMB(3, "Stream Data");
	
	private int value;
	private String type;
	
	private ServCompType(int value, String type) {
		this.value = value;
		this.type = type;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getType() {
		return type;
	}

	public static ServCompType getByValue(int value) {
		for(ServCompType type : ServCompType.values()) {
			if (type.getValue() == value)
				return type;
		}
		return ServCompType.DAB;
	}
	
}
