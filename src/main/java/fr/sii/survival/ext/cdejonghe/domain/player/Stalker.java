package fr.sii.survival.ext.cdejonghe.domain.player;

import fr.sii.survival.core.domain.image.Image;
import fr.sii.survival.core.domain.player.Enemy;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.domain.player.SimpleEnemy;
import fr.sii.survival.core.ext.annotation.Developer;

/**
 * An {@link Enemy} following a target.
 * 
 * @author cdejonghe
 *
 */
@Developer(value = "cdejonghe", name = "Cyril Dejonghe", email = "cdejonghe@sii.fr")
public class Stalker extends SimpleEnemy {
	public Stalker(String name, Image avatar, int life) {
		super(name, avatar, life);
	}

	private Player target;


	public Player getTarget() {
		return target;
	}

	public void setTarget(Player target) {
		this.target = target;
	}

}
