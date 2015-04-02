package fr.sii.survival.ext.abaudet;

import java.util.concurrent.TimeUnit;

import fr.sii.survival.core.domain.image.ClientImage;
import fr.sii.survival.core.domain.player.Wizard;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.ext.DelegateEnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.annotation.Developer;
import fr.sii.survival.core.ext.behavior.action.AttackActionBehavior;
import fr.sii.survival.core.ext.behavior.action.CooldownActionBehavior;
import fr.sii.survival.core.ext.behavior.action.EnemyActionBehavior;
import fr.sii.survival.core.ext.behavior.action.MultiActionBehavior;
import fr.sii.survival.core.ext.behavior.action.RepeatedActionBehavior;
import fr.sii.survival.core.ext.behavior.action.TemporaryChangeState;
import fr.sii.survival.core.ext.behavior.move.EnemyMoveBehavior;
import fr.sii.survival.core.ext.behavior.move.RandomMoveNearBehavior;
import fr.sii.survival.core.ext.behavior.target.RandomPlayerTargetBehavior;
import fr.sii.survival.core.ext.behavior.target.TargetBehavior;
import fr.sii.survival.core.service.game.PlayerTypePredicate;
import fr.sii.survival.ext.abaudet.constants.States;

/**
 * Enemy that immobilizes a player and hits him progressively
 * 
 * @author Aurélien Baudet
 *
 */
@Developer(value="abaudet", name="Aurélien Baudet", email="abaudet@sii.fr")
public class Immobilizator extends DelegateEnemyExtension {

	/**
	 * Immobilizator is a StateChange specialist who prevents its target form
	 * moving in order to hit him easily
	 * 
	 * @return An Enemy named Immobilizator with 5000 HP and a Client hosted
	 *         image
	 */
	public Immobilizator() {
		super("Immobilizator", new ClientImage("npc5_fr1"), 5000);
	}

	/**
	 * Chose a random target which is a living Wizard (human player)
	 * 
	 * @param context
	 *            the game context
	 * @return new RandomPlayerTargetBehavior targeting a living wizard
	 */
	@Override
	protected TargetBehavior getTargetBehavior(GameContext context) {
		// randomly targets one human player that is alive, if nobody matches then no action is executed
		return new RandomPlayerTargetBehavior(new PlayerTypePredicate(Wizard.class).and(p -> p.isAlive()));
	}
	
	/**
	 * Immobilizator immobilizes its target for 5 seconds and makes 10 points of damage every seconds:
	 * <ul>
	 * <li>TemporaryChangeState is used to apply the state "immobilized" on the player for 5 seconds</li>
	 * <li>AttackActionBehavior is used to attack the targeted alive players and make them 10 points of damage</li>
	 * <li>RepeatedActionBehavior is used to repeat the previous attack action 5 times (1 per second)</li>
	 * <li>CooldownActionBehavior is used to execute all the previous actions only every 10 seconds</li>
	 * </ul>
	 * 
	 * @param context
	 *            the game context
	 * @return the immobilize action that is composed of several simple actions
	 */
	@Override
	protected EnemyActionBehavior getActionBehavior(GameContext context) throws GameException {
		int spellDuration = 5000;
		return new CooldownActionBehavior(
					// Action that adds "immobilized" state on the players available on targeted cell and remove it after 5 seconds
					new MultiActionBehavior(
							new RepeatedActionBehavior(new AttackActionBehavior(actionService, enemy, 10), spellDuration/5, 5),
							new TemporaryChangeState(actionService, enemy, States.IMMOBILIZED.getValue(), spellDuration)),
				10, TimeUnit.SECONDS);
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
