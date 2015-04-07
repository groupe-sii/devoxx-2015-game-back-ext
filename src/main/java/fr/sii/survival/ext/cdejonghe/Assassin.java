package fr.sii.survival.ext.cdejonghe;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import fr.sii.survival.core.domain.image.Base64ServerImage;
import fr.sii.survival.core.domain.image.Image;
import fr.sii.survival.core.domain.image.UriImage;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.exception.ImageException;
import fr.sii.survival.core.exception.MimetypeDetectionException;
import fr.sii.survival.core.ext.DelegateEnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.annotation.Developer;
import fr.sii.survival.core.ext.behavior.action.AddImageBehavior;
import fr.sii.survival.core.ext.behavior.action.AttackActionBehavior;
import fr.sii.survival.core.ext.behavior.action.EnemyActionBehavior;
import fr.sii.survival.core.ext.behavior.action.MultiActionBehavior;
import fr.sii.survival.core.ext.behavior.action.RemoveImageBehavior;
import fr.sii.survival.core.ext.behavior.action.RepeatedActionBehavior;
import fr.sii.survival.core.ext.behavior.action.TemporaryActionBehavior;
import fr.sii.survival.core.ext.behavior.action.TemporaryChangeState;
import fr.sii.survival.core.ext.behavior.move.EnemyMoveBehavior;
import fr.sii.survival.core.ext.behavior.target.TargetBehavior;
import fr.sii.survival.ext.cdejonghe.behavior.action.StalkerActionBehavior;
import fr.sii.survival.ext.cdejonghe.behavior.move.StalkerMoveBehavior;
import fr.sii.survival.ext.cdejonghe.behavior.target.StateStalkerTargetBehavior;
import fr.sii.survival.ext.cdejonghe.constants.States;
import fr.sii.survival.ext.cdejonghe.domain.player.Stalker;

/**
 * Enemy that stalks a player until he is close enough to poisonned it and then
 * chooses another target.
 * 
 * @author Cyril Dejonghe
 *
 */
@Developer(value = "cdejonghe", name = "Cyril Dejonghe", email = "cdejonghe@sii.fr")
public class Assassin extends DelegateEnemyExtension {

	private static final String ASSASSIN_IMAGE_FILE = "images/assassin.png";
	private static final int ASSASSIN_SPEED = 2;
	private static final int ASSASSIN_LIFE = 40;

	private static final int ONE_SEC_IN_MILLIS = 1000;

	private static final String POISON_IMAGE_FILE = "images/poisoned.png";
	private static final int POISON_DURATION_IN_SEC = 5;
	private static final int POISON_DISTANCE = 1;
	private static final int POISON_DAMAGE_BY_SEC = 20;

	public Assassin() throws IOException, MimetypeDetectionException {
		super(new Stalker("Assassin", new Base64ServerImage(ASSASSIN_IMAGE_FILE), ASSASSIN_LIFE));
	}

	@Override
	protected EnemyActionBehavior getActionBehavior(GameContext context) throws GameException {
		try {
			// Attack and mark enemy as "poisoned" if next to its target
			int actionDurationInMillis = POISON_DURATION_IN_SEC * ONE_SEC_IN_MILLIS;
			Image image = new UriImage(POISON_IMAGE_FILE);

			return new StalkerActionBehavior(
					(Stalker) getEnemy(),
					new MultiActionBehavior(
							// Attack repeated each second for 5 sec
							new RepeatedActionBehavior(new AttackActionBehavior(actionService, enemy, POISON_DAMAGE_BY_SEC), ONE_SEC_IN_MILLIS, POISON_DURATION_IN_SEC),
							// Add POISONED state for 5 sec
							new TemporaryChangeState(actionService, enemy, States.POISONED.getValue(), actionDurationInMillis),
							// Add poisoned.png image for 5 sec
							new TemporaryActionBehavior(
									new AddImageBehavior(actionService, enemy, image),
									new RemoveImageBehavior(actionService, enemy, image),
									actionDurationInMillis)),
					POISON_DISTANCE);
		} catch (URISyntaxException | IOException e) {
			throw new ImageException("Failed to load poisoned image", e);
		}
	}

	@Override
	protected EnemyMoveBehavior getMoveBehavior(GameContext context) throws GameException {
		return new StalkerMoveBehavior((Stalker) getEnemy(), ASSASSIN_SPEED);
	}

	@Override
	protected TargetBehavior getTargetBehavior(GameContext context) throws GameException {
		return new StateStalkerTargetBehavior((Stalker) getEnemy(), null, Arrays.asList(States.POISONED.toString()));
	}
}
