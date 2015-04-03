package fr.sii.survival.ext.clegallo;

import java.io.IOException;
import java.util.List;

import fr.sii.survival.core.domain.image.Base64ServerImage;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.exception.MimetypeDetectionException;
import fr.sii.survival.core.ext.DelegateEnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.annotation.Developer;
import fr.sii.survival.core.ext.behavior.action.AreaActionBehavior;
import fr.sii.survival.core.ext.behavior.action.AttackActionBehavior;
import fr.sii.survival.core.ext.behavior.action.EnemyActionBehavior;
import fr.sii.survival.core.ext.behavior.action.shape.Circle;
import fr.sii.survival.core.ext.behavior.move.EnemyMoveBehavior;
import fr.sii.survival.core.ext.behavior.move.FollowPlayerBehavior;
import fr.sii.survival.core.ext.behavior.target.SinglePlayerTargetBehavior;
import fr.sii.survival.core.ext.behavior.target.TargetBehavior;

@Developer(value="CLG", name="clegallo", email="clegallo@sii.fr")
public class ExplosiveGoblin extends DelegateEnemyExtension {

	private Player target = null;
	
	public ExplosiveGoblin() throws IOException, MimetypeDetectionException {
		super("GoblinWithDynamite", new Base64ServerImage("images/explosiveGoblin.png"), 5000, true);
	}

	@Override
	protected EnemyActionBehavior getActionBehavior(GameContext context)
			throws GameException {
		if (context.getBoard().getCell(getEnemy()).equals(context.getBoard().getCell(getTarget(context)))) {//is on same Cell than target
			return new AreaActionBehavior(
				new AttackActionBehavior(actionService, getEnemy(), Integer.MAX_VALUE / 2),
				new Circle(context.getBoard(), 2));
		}else {
			return new AttackActionBehavior(actionService, getEnemy(), 0);//Do Nothing
		}
	}

	@Override
	protected EnemyMoveBehavior getMoveBehavior(GameContext context)
			throws GameException {
		return new FollowPlayerBehavior(getEnemy(), getTarget(context), 2);
	}

	@Override
	protected TargetBehavior getTargetBehavior(GameContext context)
			throws GameException {
		return new SinglePlayerTargetBehavior(getTarget(context));
	}

	private Player getTarget(GameContext context){
		if (target == null) {
			List<Player> players = context.getGame().getPlayers(p -> !p.equals(getEnemy()));
			if (players.size() == 0){
				target = getEnemy();
			}else {
				int random = (int) Math.floor(Math.random() * players.size());
				target = players.get(random);
			}
		}
		return target;
	}
}
