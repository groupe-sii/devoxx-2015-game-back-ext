package fr.sii.survival.ext.rules;

import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.ext.annotation.Developer;
import fr.sii.survival.core.service.board.Direction;
import fr.sii.survival.core.service.board.rule.AllowMoveRule;
import fr.sii.survival.ext.constants.States;

/**
 * Rule that prevents a player moving if the player has the state {@link States}.IMMOBILIZED
 * applied on him.
 * 
 * @author Aurélien Baudet
 *
 */
@Developer(value="abaudet", name="Aurélien Baudet", email="abaudet@sii.fr")
public class ImmobilizeRule implements AllowMoveRule {

	@Override
	public boolean isMoveAllowed(Board board, Player player, Cell cell) {
		return !isImmobilized(player);
	}

	@Override
	public boolean isMoveAllowed(Board board, Player player, Direction direction) {
		return !isImmobilized(player);
	}

	private boolean isImmobilized(Player player) {
		return player.getPlayerStates().getStates().contains(States.IMMOBILIZED.toString());
	}

}
