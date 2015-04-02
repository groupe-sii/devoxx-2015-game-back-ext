package fr.sii.survival.ext.abaudet;

import java.util.concurrent.TimeUnit;

import fr.sii.survival.core.domain.image.ClientImage;
import fr.sii.survival.core.ext.DelegateEnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.annotation.Developer;
import fr.sii.survival.core.ext.behavior.action.AttackActionBehavior;
import fr.sii.survival.core.ext.behavior.action.CooldownActionBehavior;
import fr.sii.survival.core.ext.behavior.action.EnemyActionBehavior;
import fr.sii.survival.core.ext.behavior.move.EnemyMoveBehavior;
import fr.sii.survival.core.ext.behavior.move.RandomMoveNearBehavior;
import fr.sii.survival.core.ext.behavior.target.RandomPlayerTargetBehavior;
import fr.sii.survival.core.ext.behavior.target.TargetBehavior;

/**
 * Simple enemy that attack a random alive player every 2 seconds. He attacks
 * making 20 points of damage to targeted player.
 * 
 * @author Aurélien Baudet
 *
 */
@Developer(value="abaudet", name="Aurélien Baudet", email="abaudet@sii.fr")
public class Lemming extends DelegateEnemyExtension {

	/**
	 * Lemmings are small rodents, usually found in or near the Arctic, in
	 * tundra biomes. They are subniveal animals, and together with voles and
	 * muskrats, they make up the subfamily Arvicolinae (also known as
	 * Microtinae), which forms part of the largest mammal radiation by far, the
	 * superfamily Muroidea, which also includes rats, mice, hamsters, and
	 * gerbils.
	 * 
	 * @return a lemming aka the weakest enemy in the world
	 */
	public Lemming() {
		//This enemy has its image hosted in the client, and 10 Health Points
		super("Lemming", new ClientImage("lemming"), 10);
	}

	/**
	 * Chose a Random Target (Enemy or Wizard (human players are wizards))
	 * 
	 * @param context
	 *            the game context
	 * @return any player on the game chosen randomly
	 */
	@Override
	protected TargetBehavior getTargetBehavior(GameContext context) {
		return new RandomPlayerTargetBehavior();
	}

	/**
	 * Lemming will attack its target (randomly chosen each action) every 2
	 * seconds doing 20 health points damages
	 * 
	 * @param context
	 *            the game context
	 * @return an action that attacks for 20 HP with a 2 seconds cooldown
	 */
	@Override
	protected EnemyActionBehavior getActionBehavior(GameContext context) {
		return new CooldownActionBehavior(new AttackActionBehavior(actionService, getEnemy(), 20), 2, TimeUnit.SECONDS);
	}

	/**
	 * Randomly moves by one case horizontally or vertically (exclusive)
	 * 
	 * @param context
	 *            the game context
	 * @return the enemy will move either one case (either up, down, left or right)
	 */
	@Override
	protected EnemyMoveBehavior getMoveBehavior(GameContext context) {
		return new RandomMoveNearBehavior();
	}
}
