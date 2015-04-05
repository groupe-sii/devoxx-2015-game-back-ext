package fr.sii.survival.ext.abaudet.rules;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.domain.player.PlayerStates;
import fr.sii.survival.core.service.board.Direction;
import fr.sii.survival.ext.abaudet.constants.States;

@RunWith(MockitoJUnitRunner.class)
public class ImmobilizeRuleTest {
	private ImmobilizeRule rule;
	
	@Mock
	private Board board;
	
	@Mock
	private Player player;

	@Before
	public void setUp() {
		rule = new ImmobilizeRule();
	}
	
	@Test
	public void immobilized() {
		when(player.getPlayerStates()).thenReturn(new PlayerStates("foo", "bar", States.IMMOBILIZED.getValue()));
		boolean canMove = rule.isMoveAllowed(board, player, new Cell(0, 0));
		Assert.assertFalse("should not be able to move", canMove);
		canMove = rule.isMoveAllowed(board, player, Direction.DOWN);
		Assert.assertFalse("should not be able to move down", canMove);
	}
	
	@Test
	public void free() {
		when(player.getPlayerStates()).thenReturn(new PlayerStates("foo", "bar"));
		boolean canMove = rule.isMoveAllowed(board, player, new Cell(0, 0));
		Assert.assertTrue("should be able to move", canMove);
		canMove = rule.isMoveAllowed(board, player, Direction.DOWN);
		Assert.assertTrue("should be able to move down", canMove);
	}
}
