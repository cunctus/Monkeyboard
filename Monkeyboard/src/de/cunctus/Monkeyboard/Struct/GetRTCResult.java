/**
 * @project Monkeyboard
 * @author  Christopher Rogoza
 * @email   christopher@cunctus.de
 * @package de.cunctus.Monkeyboard.Struct
 * @version 
 * @since   06.03.2015
 *
 */
package de.cunctus.Monkeyboard.Struct;

public class GetRTCResult {

	protected int sec;
	protected int min;
	protected int hour;
	protected int day;
	protected int month;
	protected int year;
	
	public String getDateString() {
		StringBuffer buff = new StringBuffer();
		buff.append(year)
		.append("-")
		.append(month < 10 ? "0" + month : month)
		.append("-")
		.append(day < 10 ? "0" + day : day)
		.append(" ")
		.append(hour < 10 ? "0" + hour : hour)
		.append(":")
		.append(min < 10 ? "0" + min : min)
		.append(":")
		.append(sec < 10 ? "0" + sec : sec);
		
		return buff.toString();
	}
	
	public int getSec() {
		return sec;
	}
	public int getMin() {
		return min;
	}
	public int getHour() {
		return hour;
	}
	public int getDay() {
		return day;
	}
	public int getMonth() {
		return month;
	}
	public int getYear() {
		return year;
	}
	public void setSec(int sec) {
		this.sec = sec;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public void setYear(int year) {
		this.year = year;
	}
		
}
