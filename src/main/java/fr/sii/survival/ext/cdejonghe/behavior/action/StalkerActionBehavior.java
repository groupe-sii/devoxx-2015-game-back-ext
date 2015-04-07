package fr.sii.survival.ext.cdejonghe.behavior.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.ext.annotation.Developer;
import fr.sii.survival.core.ext.behavior.action.EnemyActionBehavior;
import fr.sii.survival.ext.cdejonghe.domain.player.Stalker;

/**
 * An action waiting to be close enough of a {@link Stalker} target to execute
 * the delegated action.
 * 
 * @author cdejonghe
 *
 */
@Developer(value = "cdejonghe", name = "Cyril Dejonghe", email = "cdejonghe@sii.fr")
public class StalkerActionBehavior implements EnemyActionBehavior {
	private static final Logger LOG = LoggerFactory.getLogger(StalkerActionBehavior.class);

	private Stalker stalker;

	private int distance;

	private EnemyActionBehavior delegate;

	public StalkerActionBehavior(Stalker stalker, EnemyActionBehavior actionBehavior, int distance) {
		super();
		this.stalker = stalker;
		this.delegate = actionBehavior;
		this.distance = distance;
	}

	@Override
	public void execute(Game game, Cell cell) throws GameException {
		if (isTargetInSight(game, cell)) {
			LOG.info("Target in sight => executing delegate action (Game: {}, Cell: {})", game, cell);
			delegate.execute(game, cell);
		} else {
			LOG.info("Cannot see target, waiting");
		}
	}

	/**
	 * W
	 * 
	 * @param game
	 * @param to
	 *            target cell
	 * @return
	 */
	private boolean isTargetInSight(Game game, Cell to) {
		Cell from = game.getBoard().getCell(stalker);
		// manhattan distance on the grid between stalker and target
		int actualDistance = (Math.abs(to.getX() - from.getX()) + Math.abs(to.getY() - from.getY()));
		return distance == actualDistance;
	}

}
