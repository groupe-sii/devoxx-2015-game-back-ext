package fr.sii.survival.ext.cdejonghe;

import fr.sii.survival.core.domain.image.ClientImage;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.ext.DelegateEnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.annotation.Developer;
import fr.sii.survival.core.ext.behavior.action.EnemyActionBehavior;
import fr.sii.survival.core.ext.behavior.move.EnemyMoveBehavior;
import fr.sii.survival.core.ext.behavior.target.TargetBehavior;


/**
 * Enemy that stalks a player until he is close enough to poisonned it and then
 * chooses another target.
 * 
 * @author Cyril Dejonghe
 *
 */
@Developer(value = "cdejonghe", name = "Cyril Dejonghe", email = "cdejonghe@sii.fr")
public class Assassin extends DelegateEnemyExtension {

	protected Assassin() {
		super("Assassin", new ClientImage("assasin"), 10);
	}

	@Override
	protected EnemyActionBehavior getActionBehavior(GameContext context) throws GameException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected EnemyMoveBehavior getMoveBehavior(GameContext context) throws GameException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TargetBehavior getTargetBehavior(GameContext context) throws GameException {
		// TODO Auto-generated method stub
		return null;
	}

}
