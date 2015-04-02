# devoxx-2015-game-back Enemy Extension
Enemy extension module for the SII Game development challenge (devoxx 2015)

# [DelegateEnemyExtension](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/DelegateEnemyExtension.java)
This is the Enemy SuperClass(Abstract), all your Enemies extensions must extend it and overrides 3 methods : `EnemyActionBehavior`, `EnemyMoveBehavior`, and `TargetBehavior` in order to provides a proper AI (Artificial Intelligence)

## Starting your Enemy
1) Create a class extending DelegateEnemyExtension with the @Developer annotation (mandatory to have the extension taken in account by the program)
```java
@Developer(value="abaudet", name="Aurélien Baudet", email="abaudet@sii.fr")
public class MyEnemy extends DelegateEnemyExtension {}
```
2) the @Developer annontation
- if absent the code will not be loaded by the application
- value and name are mandatory and provides the Extension author
- email is optionnal, will be used only to inform the developer if his extension is disabled for any reason. If you dont care for these notifications, you can omit it.

3) Provides a default constructor
Must call super(String name, Image image, Integer life) with : 
- name : your Enemy name
- image : your Enemy icon on the board, the easy way is to add a 48x48px image in [image folder](https://github.com/groupe-sii/devoxx-2015-game-back-ext/tree/master/src/main/resources/images) and to call new [Base64ServerImage](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/domain/image/Base64ServerImage.java)("images/myimage.png")
- life : the enemy Health Points
```java
public MyEnemy() throws IOException, MimetypeDetectionException {
  super("MyEnemyIsGreat", new Base64ServerImage("images/myenemy.png"), 3000);
}
```

## Extending EnemyMoveBehavior
Here stands the move logic of your Enemy
takes the [GameContext](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/GameContext.java) and returns an [EnemyMoveBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/move/EnemyMoveBehavior.java) implementation (see [below](https://github.com/groupe-sii/devoxx-2015-game-back-ext/tree/master/src/main/java/fr/sii/survival/ext#enemymovebehavior-implementations) for samples)
```java
@Override
protected EnemyMoveBehavior getMoveBehavior(GameContext context) {}
```
Basically The EnemyMoveBehavior returned is the part of the AI which computes next destination cell of your Enemy.

## Extending TargetBehavior
Here stands the targeting logic of your Enemy
takes the [GameContext](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/GameContext.java) and returns an [TargetBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/target/TargetBehavior.java) implementation (see [below](https://github.com/groupe-sii/devoxx-2015-game-back-ext/tree/master/src/main/java/fr/sii/survival/ext#targetbehavior-implementations) for samples)
```java
@Override
protected TargetBehavior getTargetBehavior(GameContext context) {}
```
Basically The TargetBehavior returned is the part of the AI which choses cells to target.

## Extending EnemyActionBehavior
Here stands the actions your Enemy does
takes the [GameContext](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/GameContext.java) and returns an [EnemyActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/EnemyActionBehavior.java) implementation (see [below](https://github.com/groupe-sii/devoxx-2015-game-back-ext/tree/master/src/main/java/fr/sii/survival/ext#enemyactionbehavior-implementations) for samples)
```java
@Override
  protected EnemyActionBehavior getActionBehavior(GameContext context) {}
```
Basically The EnemyActionBehavior returned is the enemy AI it will do something to its targets.

#Utilities
The [core](https://github.com/groupe-sii/devoxx-2015-game-back/tree/master/survival-core) module provides some classes to help you extends your Enemy : 

## EnemyMoveBehavior implementations
- [RandomAroundNearBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/move/RandomAroundNearBehavior.java) : move your enemy  by one cell around its currend position

- [RandomCellBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/move/RandomCellBehavior.java) : Teleports your enemy on a random cell

- [RandomMoveNearBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/move/RandomMoveNearBehavior.java) : move your enemy by one horizontal or vertical (exclusive) cell.

## TargetBehavior implementations
- [RandomCellTargetBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/target/RandomCellTargetBehavior.java) : Target a random cell on the board (near useless one ;))

- [RandomPlayerTargetBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/target/RandomPlayerTargetBehavior.java) : Target a cell with a player(Enemy or Wizard) chosen randomly on it. You can instanciate this one with a Predicate applied on the Player to say if it is eligible. (i.e. the Player is an Enemy, or a Wizard)

- [SinglePlayerTargetBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/target/SinglePlayerTargetBehavior.java) : chose a player and target it forever (even if the player is dead).

## EnemyActionBehavior implementations
- [AttackActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/AttackActionBehavior.java) : Simplest behavior, will attack Enemies and Players on the targeted cell by an amount.

- [HealActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/HealActionBehavior.java) : Simplest behavior, will heal Enemies and Players on the targeted cell by an amount.

- [UpdateStateActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/UpdateStateActionBehavior.java) : Add a [StateChange](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/domain/action/StateChange.java) to all Enemies and Players on the targeted Cell. States should have a [client](https://github.com/groupe-sii/devoxx-2015-game-front) counterpart to informs players that something is happening. Try [extending](https://github.com/groupe-sii/devoxx-2015-game-front/tree/master/app/scripts/Extension) the front ! 

- [TemporaryChangeState](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/TemporaryChangeState.java) : Same as [UpdateStateActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/UpdateStateActionBehavior.java), but with a duration.

- [AreaActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/AreaActionBehavior.java) : Manager that applies the Action on a shape centered on the targeted Cell. Multiple Cells are affected.

- [CooldownActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/CooldownActionBehavior.java) : Decorator to add Cooldowns on actions to prevent enemies to execute actions too often. We recommend to use it to keep the game fun for players.

- [RepeatedActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/RepeatedActionBehavior.java) : Repeat N times the same action.

- [DelayedActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/DelayedActionBehavior.java) : Provides a delayed action.

- [MultiActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/MultiActionBehavior.java) : Decorators to do several actions at the same time.

- [TemporaryActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/TemporaryActionBehavior.java) : Action over the time for a duration. Works by execution two actions ([MultiActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/MultiActionBehavior.java)) one that start the action at the call and one [DelayedActionBehavior](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/behavior/action/DelayedActionBehavior.java), to stop it.

#Samples

## Lemming
```java
/**
 * Simple enemy that attack a random alive player every 2 seconds. He attacks
 * making 20 points of damage to targeted player.
 * 
 * @author Aurélien Baudet
 *
 */
@Developer(value = "abaudet", name = "Aurélien Baudet", email = "abaudet@sii.fr")
public class Lemming extends DelegateEnemyExtension {

  /**
   * Lemmings are small rodents, usually found in or near the Arctic, in tundra biomes. They are subniveal animals, and together with voles and muskrats, they make up the subfamily Arvicolinae (also known as Microtinae), which forms part of the largest mammal radiation by far, the superfamily Muroidea, which also includes rats, mice, hamsters, and gerbils.
   * @return a lemming aka the weakest enemy in the world
   */
  public Lemming() {
    //This enemy has its image hosted in the client, and 10 Health Points
    super("Lemming", new ClientImage("lemming"), 10);
  }

  /**
   * Chose a Random Target which is a living Wizard(human player)
   * @param  context 
   * @return         new RandomPlayerTargetBehavior targeting a living wizard;
   */
  @Override
  protected TargetBehavior getTargetBehavior(GameContext context) {
    return new RandomPlayerTargetBehavior(new PlayerTypePredicate(Wizard.class).and(p -> p.isAlive()));
  }

  /**
   * Lemming will attack its target (randomly chosen each action) every 2 seconds doing 20 health points damages
   * @param  context 
   * @return         AttackActionBehavior for 20 HP with a 2 seconds cooldown
   */
  @Override
  protected EnemyActionBehavior getActionBehavior(GameContext context) {
    return new CooldownActionBehavior(new AttackActionBehavior(actionService, getEnemy(), 20), 2, TimeUnit.SECONDS);
  }

  /**
   * Randomly moves by one case horizontally or vertically (exclusive)
   * @param  context 
   * @return         RandomMoveNearBehavior
   */
  @Override
  protected EnemyMoveBehavior getMoveBehavior(GameContext context) {
    return new RandomMoveNearBehavior();
  }
}
```

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

  /**
   * Immobilizator is a StateChange specialist who prevents its target form moving in order to hit him easily
   * 
   * @return An Enemy named Immobilizator with 5000 HP and a Client hosted image
   */
  public Immobilizator() {
    super("Immobilizator", new ClientImage("npc5_fr1"), 5000);
  }

  /**
   * Chose a Random Target which is a living Wizard(human player)
   * 
   * @param  context 
   *           the game context
   * @return         new RandomPlayerTargetBehavior targeting a living wizard
   */
  @Override
  protected TargetBehavior getTargetBehavior(GameContext context) {
    // randomly targets one human player that is alive, if nobody matches then no action is executed
    return new RandomPlayerTargetBehavior(new PlayerTypePredicate(Wizard.class).and(p -> p.isAlive()));
  }
  
  /**
   * Immobilizator immobilizes its target for 5 seconds(TemporaryChangeState) 
   * and attacks(AttackActionBehavior) it repeatedly(RepeatedActionBehavior)(5 times) for 10 damages over the 5 seconds immobilization.
   * Does it every 10 seconds(CooldownActionBehavior).
   * 
   * @param  context       
   *           the game context
   * @return               [description]
   */
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

  /**
   * Randomly moves by one case horizontally or vertically (exclusive)
   * 
   * @param  context 
   *           the game context
   * @return         RandomMoveNearBehavior
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
 * Enemy that attacks a large Areas of the board
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