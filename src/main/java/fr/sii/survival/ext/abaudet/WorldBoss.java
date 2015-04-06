package fr.sii.survival.ext.abaudet;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import fr.sii.survival.core.domain.image.Base64ServerImage;
import fr.sii.survival.core.domain.image.Image;
import fr.sii.survival.core.domain.image.UriImage;
import fr.sii.survival.core.domain.player.Wizard;
import fr.sii.survival.core.exception.ImageException;
import fr.sii.survival.core.exception.MimetypeDetectionException;
import fr.sii.survival.core.ext.DelegateEnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.SpecialEnemy;
import fr.sii.survival.core.ext.annotation.Developer;
import fr.sii.survival.core.ext.behavior.action.AddImageBehavior;
import fr.sii.survival.core.ext.behavior.action.AreaActionBehavior;
import fr.sii.survival.core.ext.behavior.action.AttackActionBehavior;
import fr.sii.survival.core.ext.behavior.action.CooldownActionBehavior;
import fr.sii.survival.core.ext.behavior.action.DelayedActionBehavior;
import fr.sii.survival.core.ext.behavior.action.EnemyActionBehavior;
import fr.sii.survival.core.ext.behavior.action.MultiActionBehavior;
import fr.sii.survival.core.ext.behavior.action.RemoveImageBehavior;
import fr.sii.survival.core.ext.behavior.action.TemporaryActionBehavior;
import fr.sii.survival.core.ext.behavior.action.shape.Circle;
import fr.sii.survival.core.ext.behavior.move.EnemyMoveBehavior;
import fr.sii.survival.core.ext.behavior.move.RandomAroundNearBehavior;
import fr.sii.survival.core.ext.behavior.target.RandomPlayerTargetBehavior;
import fr.sii.survival.core.ext.behavior.target.TargetBehavior;

/**
 * A master enemy that is almost invincible !! You can hit him but he is very
 * strong! But there is a way to kill him easily, it's up to you to find how!
 * He also attacks heavily on a large area.
 * 
 * @author Aurélien Baudet
 *
 */
@Developer(value="abaudet", name="Aurélien Baudet", email="abaudet@sii.fr")
public class WorldBoss extends DelegateEnemyExtension implements SpecialEnemy {

	public WorldBoss() throws IOException, MimetypeDetectionException {
		super("WorldBoss", new Base64ServerImage("images/worldboss.png"), Integer.MAX_VALUE);
	}

	@Override
	protected EnemyActionBehavior getActionBehavior(GameContext context) throws ImageException {
		try {
			long delay = 2000;
			long imageAnimationDuration = 3000;
			Circle circle = new Circle(context.getBoard(), 3);
			Image image = new UriImage("images/lava/lava.gif");
			return new CooldownActionBehavior(
						new MultiActionBehavior(
							new TemporaryActionBehavior(
									new AreaActionBehavior(
											new AddImageBehavior(actionService, enemy, image),
											circle
									),
									new AreaActionBehavior(
											new RemoveImageBehavior(actionService, enemy, image),
											circle
									),
								imageAnimationDuration),
							new DelayedActionBehavior(
									new AreaActionBehavior(
											new AttackActionBehavior(actionService, getEnemy(), 500),
											circle),
								delay)
						),
					20, TimeUnit.SECONDS);
		} catch(URISyntaxException|IOException e) {
			throw new ImageException("Failed to load lava image", e);
		}
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
