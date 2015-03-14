/**
 * @project Monkeyboard
 * @author  Christopher Rogoza
 * @email   christopher@cunctus.de
 * @package de.cunctus.Monkeyboard.Struct
 * @version 
 * @since   07.03.2015
 *
 */
package de.cunctus.Monkeyboard.Struct;

public class GetSignalStrengthResult {

	protected int biterror;
	protected int signalStrength;

	public GetSignalStrengthResult(int biterror, int signalStrength) {
		this.biterror = biterror;
		this.signalStrength = signalStrength;
	}

	public int getBiterror() {
		return biterror;
	}

	/**
	 * @return Signal strength from 0 to 100%
	 */
	public int getSignalStrength() {
		return signalStrength;
	}

	public void setBiterror(int biterror) {
		this.biterror = biterror;
	}

	/**
	 * Set signal strength (0-100%)
	 * 
	 * @param signalStrength the strength of the signal
	 */
	public void setSignalStrength(int signalStrength) {
		this.signalStrength = signalStrength;
	}

}
