# devoxx-2015-game-back Enemy Extension
Enemy extension module for the SII Game development challenge (devoxx 2015)

# [DelegateEnemyExtension](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/DelegateEnemyExtension.java)
This is the Enemy SuperClass(Abstract), all your Enemies extensions must extend it and overrides 3 methods : `EnemyActionBehavior`, `EnemyMoveBehavior`, and `TargetBehavior`


## Extending EnemyMoveBehavior
Here stands the move logic of your Enemy
Take the [GameContext](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/GameContext.java) and returns an [EnemyMoveBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/move/EnemyMoveBehavior.java) implementation (see [below](https://github.com/groupe-sii/devoxx-2015-game-back-ext/tree/master/src/main/java/fr/sii/survival/ext#enemymovebehavior-implementations) fro samples)
```java
@Override
protected EnemyMoveBehavior getMoveBehavior(GameContext context) {}
```
Basically The EnemyMoveBehavior returned is a next destination cell i.a.

## Extending TargetBehavior
Here stands the targeting logic of your Enemy
Take the [GameContext](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/GameContext.java) and returns an [TargetBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/target/TargetBehavior.java) implementation (see [below](https://github.com/groupe-sii/devoxx-2015-game-back-ext/tree/master/src/main/java/fr/sii/survival/ext#targetbehavior-implementations) fro samples)
```java
@Override
protected TargetBehavior getTargetBehavior(GameContext context) {}
```
Basically The TargetBehavior returned is a cells to target i.a.

## Extending EnemyActionBehavior
Here stands the actions your Enemy does
Take the [GameContext](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/GameContext.java) and returns an [EnemyActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/EnemyActionBehavior.java) implementation (see [below](https://github.com/groupe-sii/devoxx-2015-game-back-ext/tree/master/src/main/java/fr/sii/survival/ext#enemyactionbehavior-implementations) fro samples), may throw a [GameException](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/exception/GameException.java)
```java
@Override
  protected EnemyActionBehavior getActionBehavior(GameContext context) throws GameException {}
```
Basically The EnemyActionBehavior returned is the enemy i.a. it will do something to its targets.

#Utilities
The [core](https://github.com/groupe-sii/devoxx-2015-game-back/tree/master/survival-core) module provides some classes to help you extends your Enemy : 

## EnemyMoveBehavior implementations
- [RandomAroundNearBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/move/RandomAroundNearBehavior.java) : move your enemy  by one cell around its currend position

- [RandomCellBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/move/RandomCellBehavior.java) : Teleports your enemy on a random cell

- [RandomMoveNearBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/move/RandomMoveNearBehavior.java) : move your enemy by one horizontal or vertical (exclusive) cell.

## TargetBehavior implementations
- [RandomCellTargetBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/target/RandomCellTargetBehavior.java) : Target a random cell on the board (near useless one ;))

- [RandomPlayerTargetBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/target/RandomPlayerTargetBehavior.java) : Target a cell with a player chosen randomly on it.

- [SinglePlayerTargetBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/target/SinglePlayerTargetBehavior.java) : chose a player and target it until the enemy or the player dies.

## EnemyActionBehavior implementations
- [AttackActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/AttackActionBehavior.java) : Simplest behavior, will attack Enemies and Players on the targeted cell by an amount. (a negative amount is a heal)

- [HealActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/HealActionBehavior.java) : Simplest behavior, will heal Enemies and Players on the targeted cell by an amount. (a negative amount is an attack)

- [UpdateStateActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/UpdateStateActionBehavior.java) : Add a [StateChange](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/domain/action/StateChange.java) to all Enemies and Players on the targeted Cell. States must have a [client](https://github.com/groupe-sii/devoxx-2015-game-front) counterpart to effectively do something. Try [extending](https://github.com/groupe-sii/devoxx-2015-game-front/tree/master/app/scripts/Extension) the front ! 

- [TemporaryChangeState](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/TemporaryChangeState.java) : Same as [UpdateStateActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/UpdateStateActionBehavior.java), but with a duration.

- [AreaActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/AreaActionBehavior.java) : Manager that applies the Action on a shape centered on the targeted Cell. Multiple Cells are affected.

- [CooldownActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/CooldownActionBehavior.java) : Decorator to add Cooldowns on actions to prevent enemies to execute actions too often. We recommend to use it to keep the game fun for players.

- [RepeatedActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/RepeatedActionBehavior.java) : Repeat N times the same action.

- [DelayedActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/DelayedActionBehavior.java) : Provides a delayed action.

- [MultiActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/MultiActionBehavior.java) : Decorators to do several actions at the same time.

- [TemporaryActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/TemporaryActionBehavior.java) : Action over the time for a duration. Works by execution two actions ([MultiActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/MultiActionBehavior.java)) one that start the action at the call and one [DelayedActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/DelayedActionBehavior.java), to stop it.

#Samples

## Immobilizator
```java
/**
 * Enemy that immobilize a player and hit him progressively
 * 
 * @author Aurélien Baudet
 *
 */
@Developer(value="abaudet", name="Aurélien Baudet", email="abaudet@sii.fr")
public class Immobilizator extends DelegateEnemyExtension {

  public Immobilizator() {
    super("Immobilizator", new ClientImage("npc5_fr1"), 5000);
  }

  
  @Override
  protected EnemyActionBehavior getActionBehavior(GameContext context) throws GameException {
    int spellDuration = 5000;
    return new CooldownActionBehavior(
          // Action that adds "immobilized" state on the players available on targeted cell and remove it after 5 seconds
          new MultiActionBehavior(
              new RepeatedActionBehavior(new AttackActionBehavior(actionService, enemy, 10), spellDuration/5, 5),
              new TemporaryChangeState(actionService, enemy, States.IMMOBILIZED.toString(), spellDuration)),
        10, TimeUnit.SECONDS);
  }

  @Override
  protected EnemyMoveBehavior getMoveBehavior(GameContext context) {
    return new RandomMoveNearBehavior();
  }

  @Override
  protected TargetBehavior getTargetBehavior(GameContext context) {
    // randomly targets one human player that is alive, if nobody matches then no action is executed
    return new RandomPlayerTargetBehavior(new PlayerTypePredicate(Wizard.class).and(p -> p.isAlive()));
  }

}
```

## WorldBoss
```java
/**
 * Enemy that attackes large Areas of the board
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