package fr.sii.survival.ext;

import java.util.concurrent.TimeUnit;

import fr.sii.survival.core.domain.image.ClientImage;
import fr.sii.survival.core.domain.player.Wizard;
import fr.sii.survival.core.ext.DelegateEnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.behavior.action.AttackActionBehavior;
import fr.sii.survival.core.ext.behavior.action.CooldownActionBehavior;
import fr.sii.survival.core.ext.behavior.action.EnemyActionBehavior;
import fr.sii.survival.core.ext.behavior.move.EnemyMoveBehavior;
import fr.sii.survival.core.ext.behavior.move.RandomMoveNearBehavior;
import fr.sii.survival.core.ext.behavior.target.RandomPlayerTargetBehavior;
import fr.sii.survival.core.ext.behavior.target.TargetBehavior;
import fr.sii.survival.core.service.game.PlayerTypePredicate;

public class Lemming extends DelegateEnemyExtension {

	public Lemming() {
		super("Lemming", new ClientImage("lemming"), 10);
	}

	@Override
	protected EnemyActionBehavior getActionBehavior(GameContext context) {
		return new CooldownActionBehavior(new AttackActionBehavior(actionService, getEnemy(), 20), 2, TimeUnit.SECONDS);
	}

	@Override
	protected EnemyMoveBehavior getMoveBehavior(GameContext context) {
		return new RandomMoveNearBehavior();
	}

	@Override
	protected TargetBehavior getTargetBehavior(GameContext context) {
		return new RandomPlayerTargetBehavior(new PlayerTypePredicate(Wizard.class).and(p -> p.isAlive()));
	}

}
