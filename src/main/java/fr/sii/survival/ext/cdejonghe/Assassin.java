package fr.sii.survival.ext.cdejonghe;

import fr.sii.survival.core.domain.image.ClientImage;
import fr.sii.survival.core.ext.DelegateEnemyExtension;
import fr.sii.survival.core.ext.annotation.Developer;


/**
 * Enemy that stalks a player until he is close enough to poisonned it and then
 * chooses another target.
 * 
 * @author Cyril Dejonghe
 *
 */
@Developer(value = "cdejonghe", name = "Cyril Dejonghe", email = "cdejonghe@sii.fr")
public class Assassin extends DelegateEnemyExtension {

	protected Assassin() {
		super("Assassin", new ClientImage("assasin"), 10);
	}

}
