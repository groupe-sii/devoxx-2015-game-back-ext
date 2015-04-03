package fr.sii.survival.ext.tboussard;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.behavior.move.EnemyMoveBehavior;

/**
 * Move manager that places the enemy two cells around the current one.
 * The enemy can move on x axis and in y axis on the same time.
 * 
 * @author Tanguy Boussard
 *
 */
public class RandomRunAroundNearBehavior implements EnemyMoveBehavior {
	@Override
	public Cell getNextPosition(GameContext context) {
		int directionX = (int) Math.round(2*Math.random())-1;
		int directionY = (int) Math.round(2*Math.random())-1;
		int newX = context.getCell().getX()+directionX * 2;
		int newY = context.getCell().getY()+directionY * 2;
		if(newX<0) {
			newX = 0;
		} else if(newX>=context.getBoard().getWidth()) {
			newX = context.getBoard().getWidth()-1;
		}
		if(newY<0) {
			newY = 0;
		} else if(newY>=context.getBoard().getHeight()) {
			newY = context.getBoard().getHeight()-1;
		}
		return new Cell(newX, newY);
	}

}
