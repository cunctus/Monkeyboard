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

public class GetProgramInfoResult {
	
	protected int ServiceComponentID;
	protected int ServiceID;
	protected int EnsembleID;
	public int getServiceComponentID() {
		return ServiceComponentID;
	}
	public int getServiceID() {
		return ServiceID;
	}
	public int getEnsembleID() {
		return EnsembleID;
	}
	public void setServiceComponentID(int serviceComponentID) {
		ServiceComponentID = serviceComponentID;
	}
	public void setServiceID(int serviceID) {
		ServiceID = serviceID;
	}
	public void setEnsembleID(int ensembleID) {
		EnsembleID = ensembleID;
	}
	
	
}
