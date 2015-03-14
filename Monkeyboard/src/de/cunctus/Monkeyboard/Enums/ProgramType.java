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

public enum ProgramType {

	TYPE_NA(0, "<Prg Type N/A>"), NEWS(1, "NEWS"), CURRENT_AFFAIRS(2,
			"Current Affairs"), INFORMATION(3, "Information"), SPORT(4, "Sport"), EDUCATION(
			5, "Education"), DRAMA(6, "Drama"), ARTS(7, "Arts"), SCIENCE(8,
			"Science"), TALK(9, "Talk"), POP_MUSIC(10, "Pop Music"), ROCK_MUSIC(
			11, "Rock Music"), EASY_LISTENING(12, "Easy Listening"), LIGHT_CLASSICAL(
			13, "Light Classical"), CLASSICAL_MUSIC(14, "Classical Music"), OTHER_MUSIC(
			15, "Other Music"), WEATHER(16, "Weather"), FINANCE(17, "Finance"), CHILDRENS(
			18, "Children's"), FACTUAL(19, "FACTUAL"), RELIGION(20, "Religion"), PHONE_IN(
			21, "Phone In"), TRAVEL(22, "Travel"), LEISURE(23, "Leisure"), JAZZ_AND_BLUES(
			24, "Jazz and Blues"), COUNTRY_MUSIC(25, "Country Music"), NATIONAL_MUSIC(
			26, "National Music"), OLDIES_MUSIC(27, "Oldies Music"), FOLK_MUSIC(
			28, "Folk Music"), DOCUMENTARY(29, "Documentary"), UNDEFINED_1(30,
			"Undefined"), UNDEFINED_2(31, "Undefined");

	private int value;
	private String type;

	private ProgramType(int value, String type) {
		this.value = value;
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public String getType() {
		return type;
	}
	
	public static ProgramType getByValue(int value) {
		for(ProgramType type : ProgramType.values()) {
			if (type.getValue() == value)
				return type;
		}
		return ProgramType.UNDEFINED_2;
		
	}
}
