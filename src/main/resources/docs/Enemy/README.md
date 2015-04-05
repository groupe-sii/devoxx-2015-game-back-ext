# devoxx-2015-game-back Enemy Extension

Enemy extension module for the SII Game development challenge (devoxx 2015)


# [DelegateEnemyExtension](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/DelegateEnemyExtension.java)

This is the simplest way to create an enemy. Just extend it and overrides 3 methods to provide basic AI (Artificial Intelligence) behaviors:
- one `EnemyMoveBehavior` to indicate how your enemy will move on the board
- one `TargetBehavior` to indicate how your enemy will target cells for actions
- one `EnemyActionBehavior` to indicate which action your enemy will execute on targeted cells

This basic way is used to declare a fixed AI. We encourage you to start with this helper class. However, if you want to create an AI that is smarter and evolves according to what happens on the game, you can ask Aurélien Baudet on the SII stand to provide useful information.


## Starting your Enemy

1) Create a class extending DelegateEnemyExtension with the @Developer annotation (mandatory to have the extension taken in account by the program)

```java
@Developer(value="abaudet", name="Aurélien Baudet", email="abaudet@sii.fr")
public class MyEnemy extends DelegateEnemyExtension {}
```
2) the @Developer annotation
- if absent the code will not be loaded by the application
- value and name are mandatory and provides the Extension author
- email is optional, will be used only to inform the developer if his extension is disabled for any reason. If you don't care about these notifications, you can omit it.

3) Provides a default constructor
Must call super(String name, Image image, Integer life) with : 
- name : your enemy name
- image : your enemy icon on the board, the easy way is to add a 48x48px image in [image folder](src/main/resources/images) and to load it using either:
  - [Base64ServerImage](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/domain/image/Base64ServerImage.java): to load an image stored on the server side and base64 encoded
  - [UriImage](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/domain/image/UriImage.java): to load an image stored on the server side that provides an URL to access through HTTP
  - [ClientImage](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/domain/image/ClientImage.java): to use an image stored on the client side. It just references an image hosted into the app/images folder off the client
- life : your enemy health points

```java
public MyEnemy() throws IOException, MimetypeDetectionException {
  super("MyEnemyIsGreat", new Base64ServerImage("images/myenemy.png"), 3000);
}
```


## Extending EnemyMoveBehavior

Here stands the move logic of your enemy. It 
takes the [GameContext](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/GameContext.java) and returns an [EnemyMoveBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/move/EnemyMoveBehavior.java) implementation (see [below](#enemymovebehavior-implementations) for samples)

```java
@Override
protected EnemyMoveBehavior getMoveBehavior(GameContext context) {}
```
Basically The EnemyMoveBehavior returned is the part of the AI which computes next destination cell of your Enemy.


## Extending TargetBehavior

Here stands the targeting logic of your enemy. It 
takes the [GameContext](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/GameContext.java) and returns an [TargetBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/target/TargetBehavior.java) implementation (see [below](#targetbehavior-implementations) for samples)

```java
@Override
protected TargetBehavior getTargetBehavior(GameContext context) {}
```

Basically The TargetBehavior returned is the part of the AI that choses cells to target.


## Extending EnemyActionBehavior
Here stands the actions your enemy. It 
takes the [GameContext](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/GameContext.java) and returns an [EnemyActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/EnemyActionBehavior.java) implementation (see [below](#enemyactionbehavior-implementations) for samples)

```java
@Override
  protected EnemyActionBehavior getActionBehavior(GameContext context) {}
```

Basically The EnemyActionBehavior returned is the enemy AI that will do something to its targets.


# Utilities

The [core](https://github.com/groupe-sii/devoxx-2015-game-back/tree/master/survival-core) module provides some predefined classes to help you create your enemy: 


## EnemyMoveBehavior implementations

- [RandomAroundNearBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/move/RandomAroundNearBehavior.java): move your enemy by one cell around its current position

- [RandomCellBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/move/RandomCellBehavior.java): Teleports your enemy on a random cell

- [RandomMoveNearBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/move/RandomMoveNearBehavior.java): move your enemy by one horizontal or vertical (exclusive) cell.

## TargetBehavior implementations

- [RandomCellTargetBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/target/RandomCellTargetBehavior.java): Target a random cell on the board (near useless one ;))

- [RandomPlayerTargetBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/target/RandomPlayerTargetBehavior.java): Target a cell with a player (Enemy or Wizard) chosen randomly on it. You can instanciate this one with a Predicate applied on the Player to say if it is eligible. A Player is an Enemy or a Wizard. For example, you can use a predicate for filtering on wizards that are alive. Then this behavior will randomly select one of the filtered players (i.e. one of the alive wizards).

- [SinglePlayerTargetBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/target/SinglePlayerTargetBehavior.java): chose a player and target it forever (even if the player is dead).


## EnemyActionBehavior implementations

- [AttackActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/AttackActionBehavior.java): Simplest behavior, will attack all players (enemies and wizards) that are on the targeted cell by the provided amount (must be positive).

- [HealActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/HealActionBehavior.java): Simplest behavior, will heal all players (enemies and wizards) that are on the targeted cell by the provided amount (must be positive).

- [UpdateStateActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/UpdateStateActionBehavior.java): Add a [StateChange](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/domain/action/StateChange.java) to all players on the targeted Cell. States are just a string applied on the player so it does nothing alone. A behavior can be provided to do something when the state is present by either:
  - Implement a [rule extension](../Rule) on server side
  - Implement a [client](https://github.com/groupe-sii/devoxx-2015-game-front) counterpart to informs players that something is happening. Try [extending](https://github.com/groupe-sii/devoxx-2015-game-front/tree/master/app/scripts/Extension) the front ! 

- [TemporaryChangeState](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/TemporaryChangeState.java): Same as [UpdateStateActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/UpdateStateActionBehavior.java), except that the state will automatically be removed after the provided duration.

- [AreaActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/AreaActionBehavior.java): Manager that applies the Action on a shape centered on the targeted cell. Multiple cells are affected.

- [CooldownActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/CooldownActionBehavior.java): Decorator to add cooldowns on actions to prevent enemies to execute actions too often. We recommend to use it to keep the game fun for players.

- [RepeatedActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/RepeatedActionBehavior.java): Repeat N times the same action.

- [DelayedActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/DelayedActionBehavior.java): Wait for the provided delay before executing the action. For example, the Worldboss enemy uses it to let a chance for players to move before being attacked.

- [MultiActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/MultiActionBehavior.java): Decorator to combine several actions.

- [TemporaryActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/TemporaryActionBehavior.java): Action over the time for a duration. Works by execution two actions ([MultiActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/MultiActionBehavior.java)) one that start the action at the call and one [DelayedActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/DelayedActionBehavior.java), to stop it. The [TemporaryChangeState](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/TemporaryChangeState.java) is one of possible uses of this helper.


#Samples

## Lemming

A stupid enemy that targets anyone on the board. He has only 10 points of life and do 20 points of damage. As he can target himself, he can suicide alone...

```java
/**
 * Simple enemy that attack a random alive player every 2 seconds. He attacks
 * making 20 points of damage to targeted player.
 * 
 * @author Aurélien Baudet
 *
 */
@Developer(value="abaudet", name="Aurélien Baudet", email="abaudet@sii.fr")
public class Lemming extends DelegateEnemyExtension {

	/**
	 * Lemmings are small rodents, usually found in or near the Arctic, in
	 * tundra biomes. They are subniveal animals, and together with voles and
	 * muskrats, they make up the subfamily Arvicolinae (also known as
	 * Microtinae), which forms part of the largest mammal radiation by far, the
	 * superfamily Muroidea, which also includes rats, mice, hamsters, and
	 * gerbils.
	 * 
	 * Creates a lemming aka the weakest enemy in the world
	 */
	public Lemming() {
		//This enemy has its image hosted in the client, and 10 Health Points
		super("Lemming", new ClientImage("lemming"), 10);
	}

	/**
	 * Chose a Random Target (Enemy or Wizard (human players are wizards))
	 * 
	 * @param context
	 *            the game context
	 * @return any player on the game chosen randomly
	 */
	@Override
	protected TargetBehavior getTargetBehavior(GameContext context) {
		return new RandomPlayerTargetBehavior();
	}

	/**
	 * Lemming will attack its target (randomly chosen each action) every 2
	 * seconds doing 20 health points damages
	 * 
	 * @param context
	 *            the game context
	 * @return an action that attacks for 20 HP with a 2 seconds cooldown
	 */
	@Override
	protected EnemyActionBehavior getActionBehavior(GameContext context) {
		return new CooldownActionBehavior(new AttackActionBehavior(actionService, getEnemy(), 20), 2, TimeUnit.SECONDS);
	}

	/**
	 * Randomly moves by one case horizontally or vertically (exclusive)
	 * 
	 * @param context
	 *            the game context
	 * @return the enemy will move either one case (either up, down, left or right)
	 */
	@Override
	protected EnemyMoveBehavior getMoveBehavior(GameContext context) {
		return new RandomMoveNearBehavior();
	}
}
```


## Immobilizator

A pain in the neck enemy that invokes roots. The roots will get out of the earth and block your feet. Then you can't move for 10 seconds. Moreover it makes you some damages while you are stuck.

This example is the best one to show how to combine an enemy with a [rule](../../../../java/fr/sii/survival/ext/abaudet/rules/ImmobilizeRule.java) and an [animation](../../../../java/fr/sii/survival/ext/abaudet/animation/ImmobilizeAnimationProvider.java).

```java
/**
 * Enemy that immobilizes a player and hits him progressively.
 * 
 * The real immobilization is done using {@link ImmobilizeRule} implementation.
 * 
 * The associated animation is done by {@link ImmobilizeAnimationProvider} implementation.
 * 
 * @author Aurélien Baudet
 *
 */
@Developer(value="abaudet", name="Aurélien Baudet", email="abaudet@sii.fr")
public class Immobilizator extends DelegateEnemyExtension {

	/**
	 * Immobilizator is a StateChange specialist who prevents its target form
	 * moving in order to hit him easily
	 * 
	 * Creates an Enemy named Immobilizator with 5000 HP and a Client hosted
	 *         image
	 */
	public Immobilizator() {
		super("Immobilizator", new ClientImage("npc5_fr1"), 5000);
	}

	/**
	 * Chose a random target which is a living Wizard (human player)
	 * 
	 * @param context
	 *            the game context
	 * @return new RandomPlayerTargetBehavior targeting a living wizard
	 */
	@Override
	protected TargetBehavior getTargetBehavior(GameContext context) {
		// randomly targets one human player that is alive, if nobody matches then no action is executed
		return new RandomPlayerTargetBehavior(new PlayerTypePredicate(Wizard.class).and(p -> p.isAlive()));
	}
	
	/**
	 * Immobilizator immobilizes its target for 5 seconds and makes 10 points of damage every seconds:
	 * <ul>
	 * <li>TemporaryChangeState is used to apply the state "immobilized" on the player for 5 seconds</li>
	 * <li>AttackActionBehavior is used to attack the targeted alive players and make them 10 points of damage</li>
	 * <li>RepeatedActionBehavior is used to repeat the previous attack action 5 times (1 per second)</li>
	 * <li>CooldownActionBehavior is used to execute all the previous actions only every 10 seconds</li>
	 * </ul>
	 * 
	 * @param context
	 *            the game context
	 * @return the immobilize action that is composed of several simple actions
	 */
	@Override
	protected EnemyActionBehavior getActionBehavior(GameContext context) throws GameException {
		int spellDuration = 5000;
		return new CooldownActionBehavior(
					// Action that adds "immobilized" state on the players available on targeted cell and remove it after 5 seconds
					new MultiActionBehavior(
							new RepeatedActionBehavior(new AttackActionBehavior(actionService, enemy, 10), spellDuration/5, 5),
							new TemporaryChangeState(actionService, enemy, States.IMMOBILIZED.getValue(), spellDuration)),
				10, TimeUnit.SECONDS);
	}

	/**
	 * Randomly moves by one case horizontally or vertically (exclusive)
	 * 
	 * @param context
	 *            the game context
	 * @return the enemy will move either one case (either up, down, left or right)
	 */
	@Override
	protected EnemyMoveBehavior getMoveBehavior(GameContext context) {
		return new RandomMoveNearBehavior();
	}
}
```


## WorldBoss

```java
/**
 * A master enemy that is almost invincible !! You can hit him but he is very
 * strong ! But there is a way to kill him easily, it's up to you to find how !
 * He also attacks heavily on a large area.
 * 
 * @author Aurélien Baudet
 *
 */
@Developer(value="abaudet", name="Aurélien Baudet", email="abaudet@sii.fr")
public class WorldBoss extends DelegateEnemyExtension implements SpecialEnemy {

	public WorldBoss() throws IOException, MimetypeDetectionException {
		super("WorldBoss", new Base64ServerImage("images/worldboss.png"), Integer.MAX_VALUE);
	}

	@Override
	protected EnemyActionBehavior getActionBehavior(GameContext context) {
		return new CooldownActionBehavior(new DelayedActionBehavior(
					new AreaActionBehavior(
							new AttackActionBehavior(actionService, getEnemy(), 500),
							new Circle(context.getBoard(), 3)),
					1, TimeUnit.SECONDS),
				20, TimeUnit.SECONDS);
	}

	@Override
	protected EnemyMoveBehavior getMoveBehavior(GameContext context) {
		return new RandomAroundNearBehavior();
	}

	@Override
	protected TargetBehavior getTargetBehavior(GameContext context) {
		return new RandomPlayerTargetBehavior(Wizard.class);
	}
}

```
