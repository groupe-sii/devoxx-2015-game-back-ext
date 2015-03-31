package fr.sii.survival.ext;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import fr.sii.survival.core.domain.image.Base64ServerImage;
import fr.sii.survival.core.domain.player.Wizard;
import fr.sii.survival.core.exception.MimetypeDetectionException;
import fr.sii.survival.core.ext.DelegateEnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.SpecialEnemy;
import fr.sii.survival.core.ext.annotation.Developer;
import fr.sii.survival.core.ext.behavior.action.AreaActionBehavior;
import fr.sii.survival.core.ext.behavior.action.AttackActionBehavior;
import fr.sii.survival.core.ext.behavior.action.CooldownActionBehavior;
import fr.sii.survival.core.ext.behavior.action.DelayedActionBehavior;
import fr.sii.survival.core.ext.behavior.action.EnemyActionBehavior;
import fr.sii.survival.core.ext.behavior.action.shape.Circle;
import fr.sii.survival.core.ext.behavior.move.EnemyMoveBehavior;
import fr.sii.survival.core.ext.behavior.move.RandomAroundNearBehavior;
import fr.sii.survival.core.ext.behavior.target.RandomPlayerTargetBehavior;
import fr.sii.survival.core.ext.behavior.target.TargetBehavior;

@Developer(value="abaudet", name="Aurélien Baudet", email="abaudet@sii.fr")
public class WorldBoss extends DelegateEnemyExtension implements SpecialEnemy {

	public WorldBoss() throws IOException, MimetypeDetectionException {
		super("WorldBoss", new Base64ServerImage("images/worldboss.png"), Integer.MAX_VALUE);
	}

	@Override
	protected EnemyActionBehavior getActionBehavior(GameContext context) {
		return new CooldownActionBehavior(new DelayedActionBehavior(
					new AreaActionBehavior(
							new AttackActionBehavior(actionService, getEnemy(), 500),
							new Circle(context.getBoard(), 3)),
					1, TimeUnit.SECONDS),
				20, TimeUnit.SECONDS);
	}

	@Override
	protected EnemyMoveBehavior getMoveBehavior(GameContext context) {
		return new RandomAroundNearBehavior();
	}

	@Override
	protected TargetBehavior getTargetBehavior(GameContext context) {
		return new RandomPlayerTargetBehavior(Wizard.class);
	}

}
