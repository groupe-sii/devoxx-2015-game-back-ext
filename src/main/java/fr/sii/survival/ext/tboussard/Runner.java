package fr.sii.survival.ext.tboussard;

import java.io.IOException;
import java.net.URISyntaxException;

import fr.sii.survival.core.domain.image.UriImage;
import fr.sii.survival.core.domain.player.Wizard;
import fr.sii.survival.core.ext.DelegateEnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.annotation.Developer;
import fr.sii.survival.core.ext.behavior.action.AttackActionBehavior;
import fr.sii.survival.core.ext.behavior.action.EnemyActionBehavior;
import fr.sii.survival.core.ext.behavior.action.RepeatedActionBehavior;
import fr.sii.survival.core.ext.behavior.move.EnemyMoveBehavior;
import fr.sii.survival.core.ext.behavior.target.RandomPlayerTargetBehavior;
import fr.sii.survival.core.ext.behavior.target.TargetBehavior;
import fr.sii.survival.core.service.game.PlayerTypePredicate;

@Developer(value="tboussard", name="Tanguy Boussard", email="tboussard@sii.fr")
public class Runner extends DelegateEnemyExtension {

	public Runner() throws IOException, URISyntaxException {
		super("Runner", new UriImage("images/248.png"), 3000);
	}

	@Override
	protected TargetBehavior getTargetBehavior(GameContext context) {
		return new RandomPlayerTargetBehavior(new PlayerTypePredicate(Wizard.class).and(p -> p.isAlive()));
	}

	@Override
	protected EnemyActionBehavior getActionBehavior(GameContext context) {
		return new RepeatedActionBehavior(new AttackActionBehavior(actionService, enemy, 10), 1000, 5);
	}

	@Override
	protected EnemyMoveBehavior getMoveBehavior(GameContext context) {
		return new RandomRunAroundNearBehavior();	
	}
}
