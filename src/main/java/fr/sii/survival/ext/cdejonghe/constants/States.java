package fr.sii.survival.ext.cdejonghe.constants;

import fr.sii.survival.core.ext.annotation.Developer;

@Developer(value = "cdejonghe", name = "Cyril Dejonghe", email = "cdejonghe@sii.fr")
public enum States {
	/**
	 * State used to indicate that a player is immobilized
	 */
	POISONED;

	public String getValue() {
		return name().toLowerCase();
	}
}
