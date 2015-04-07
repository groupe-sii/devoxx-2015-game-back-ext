package fr.sii.survival.ext.cdejonghe.behavior.target;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.domain.player.Wizard;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.annotation.Developer;
import fr.sii.survival.core.ext.behavior.target.TargetBehavior;
import fr.sii.survival.ext.cdejonghe.domain.player.Stalker;

/**
 * Behavior that targets a single non poisoned player. Until the player is not
 * poisoned, this target manager will always provide the current cell where the
 * player is.
 * 
 * @author Cyril Dejonghe
 *
 */
@Developer(value = "cdejonghe", name = "Cyril Dejonghe", email = "cdejonghe@sii.fr")
public class StateStalkerTargetBehavior implements TargetBehavior {
	private static final Logger LOG = LoggerFactory.getLogger(StateStalkerTargetBehavior.class);

	private Stalker follower;
	/** A player with any of those states might be targeted. **/
	private List<String> validStates;
	/** A player with any of those states must not be targeted. **/
	private List<String> invalidStates;

	public StateStalkerTargetBehavior(Stalker stalker, List<String> validStates, List<String> invalidStates) {
		this.follower = stalker;
		this.validStates = validStates;
		this.invalidStates = invalidStates;
	}

	@Override
	public List<Cell> getTargetPositions(GameContext context) {
		Player target = follower.getTarget();

		// no target or actual target invalid ? find a new one
		if (target == null || !isTargetable(target)) {
			follower.setTarget(getNewTarget(context));
			LOG.debug("Stalker switch target : " + follower.getTarget());
		}

		// returns the target active cell
		if (target == null) {
			return new ArrayList<>();
		} else {
			Cell cell = context.getBoard().getCell(target);
			return cell == null ? new ArrayList<>() : Arrays.asList(cell);
		}
	}

	private Player getNewTarget(GameContext context) {
		List<Player> validPlayerList = context.getGame().getPlayers(p -> isTargetable(p));
		if (validPlayerList != null && validPlayerList.size() > 0) {
			// targets random healthy player
			return getRandomPlayer(validPlayerList);
		} else {
			return null;
		}

	}

	private Player getRandomPlayer(List<Player> playerList) {
		return playerList.get((int) Math.floor(Math.random() * playerList.size()));
	}

	/**
	 *
	 * @param player
	 * @return true if this player is alive, must not have any invalid states
	 *         must have at least one valid state.
	 */
	protected boolean isTargetable(Player player) {
		if (player.isAlive() && player instanceof Wizard) {
			if (invalidStates != null) {
				for (String state : invalidStates) {
					if (player.getPlayerStates().getStates().contains(state)) {
						return false;
					}
				}
			}

			if (validStates != null) {
				for (String state : validStates) {
					if (player.getPlayerStates().getStates().contains(state)) {
						return true;
					}
				}
			} else {
				return true;
			}
		}

		return false;
	}
}
