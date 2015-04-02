package fr.sii.survival.ext.abaudet.constants;

public enum States {
	/**
	 * State used to indicate that a player is immobilized
	 */
	IMMOBILIZED;
	
	public String getValue() {
		return name().toLowerCase();
	}
}
