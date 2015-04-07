package fr.sii.survival.ext.cdejonghe.behavior.target;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.domain.player.SimpleEnemy;
import fr.sii.survival.core.domain.player.SimplePlayer;
import fr.sii.survival.core.domain.player.SimpleWizard;
import fr.sii.survival.ext.cdejonghe.domain.player.Stalker;

public class StateStalkerTargetBehaviorTest {
	private Stalker stalker = new Stalker("stalker", null, 40);
	private StateStalkerTargetBehavior targetBehavior;

	@Test
	public void testIsTargetableSimplePlayer() {
		targetBehavior = new StateStalkerTargetBehavior(stalker, null, null);
		Player player = new SimplePlayer(null);

		boolean result = targetBehavior.isTargetable(player);

		Assert.assertFalse(result);
	}

	@Test
	public void testIsTargetableSimpleEnemy() {
		targetBehavior = new StateStalkerTargetBehavior(stalker, null, null);
		Player player = new SimpleEnemy(null);

		boolean result = targetBehavior.isTargetable(player);

		Assert.assertFalse(result);
	}

	@Test
	public void testIsTargetableSimpleWizard() {
		targetBehavior = new StateStalkerTargetBehavior(stalker, null, null);
		Player player = new SimpleWizard(null);

		boolean result = targetBehavior.isTargetable(player);

		Assert.assertTrue(result);
	}

	@Test
	public void testIsTargetableInvalidState() {
		String invalidState = "bad";
		targetBehavior = new StateStalkerTargetBehavior(stalker, null, Arrays.asList(invalidState));
		Player player = new SimpleWizard(null);
		player.getPlayerStates().addState(invalidState);

		boolean result = targetBehavior.isTargetable(player);

		Assert.assertFalse(result);
	}

	@Test
	public void testIsTargetableValidState() {
		String validState = "good";
		targetBehavior = new StateStalkerTargetBehavior(stalker, Arrays.asList(validState), null);
		Player player = new SimpleWizard(null);
		player.getPlayerStates().addState(validState);

		boolean result = targetBehavior.isTargetable(player);

		Assert.assertTrue(result);
	}

	@Test
	public void testIsTargetableValidStateAndInvalidState() {
		String validState = "good";
		String invalidState = "bad";
		targetBehavior = new StateStalkerTargetBehavior(stalker, Arrays.asList(validState), Arrays.asList(invalidState));
		Player player = new SimpleWizard(null);
		player.getPlayerStates().addState(validState);
		player.getPlayerStates().addState(invalidState);
		player.getPlayerStates().addState("none");

		boolean result = targetBehavior.isTargetable(player);

		Assert.assertFalse(result);
	}

	@Test
	public void testIsTargetableDead() {
		targetBehavior = new StateStalkerTargetBehavior(stalker, null, null);
		Player player = new SimpleWizard(null);
		player.getLife().setCurrent(0);

		boolean result = targetBehavior.isTargetable(player);

		Assert.assertFalse(result);
	}
}
