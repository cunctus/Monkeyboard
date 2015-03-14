/**
 * @project Monkeyboard
 * @author  Christopher Rogoza
 * @email   christopher@cunctus.de
 * @package de.cunctus.Monkeyboard.Exceptions
 * @version 
 * @since   06.03.2015
 *
 */
package de.cunctus.Monkeyboard.Exceptions;

public class DeviceNotFoundException extends MonkeyboardException {

	private static final long serialVersionUID = -4127361470041516118L;
	
	public DeviceNotFoundException() {
		super();
	}
	
	public DeviceNotFoundException(String message) {
		super(message);
	}

}
