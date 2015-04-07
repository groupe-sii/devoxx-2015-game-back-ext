package fr.sii.survival.ext.cdejonghe.behavior.move;

import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.ext.annotation.Developer;
import fr.sii.survival.core.ext.behavior.move.FollowPlayerBehavior;
import fr.sii.survival.ext.cdejonghe.domain.player.Stalker;

/**
 * Behavior that follows an {@link Follower} target.
 * 
 * @author Cyril Dejonghe
 *
 */
@Developer(value = "cdejonghe", name = "Cyril Dejonghe", email = "cdejonghe@sii.fr")
public class StalkerMoveBehavior extends FollowPlayerBehavior {
	public StalkerMoveBehavior(Stalker stalker, int speed, int distance) {
		super(stalker, null, speed, distance);
	}

	@Override
	public Player getFollowed() {
		return ((Stalker) getFollower()).getTarget();
	}
}
