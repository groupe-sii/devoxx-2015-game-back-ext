# devoxx-2015-game-back Animation Extension
Animation extension module for the SII Game development challenge (devoxx 2015)

# [AnimationProvider](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/ext/animation/AnimationProvider.java)
This is the AnimationProvider interface, all your extensions must implements it and override the `provide` method which returns an [Animation](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/domain/animation/Animation.java)

# Starting your Extension
1) Create a class implenting AnimationProvider with the @Developer annotation (mandatory to have the extension taken in account by the program)
```java
@Developer(value="abaudet", name="Aurélien Baudet", email="abaudet@sii.fr")
public class ImmobilizeAnimationProvider implements AnimationProvider {}
```
2) the @Developer annontation
- if absent the code will not be loaded by the application
- value and name are mandatory and provides the Extension author
- email is optionnal, will be used only to inform the developer if his extension is disabled for any reason. If you dont care for these notifications, you can omit it.

3) Overrides provide method
You will have to create an [Animation](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/domain/animation/Animation.java) by implementing one or using one which does exist(see [below](#utilities)) 
```java
@Override
public Animation provide() throws AnimationInitializationException {}
```

#Utilities
The [core](https://github.com/groupe-sii/devoxx-2015-game-back/tree/master/survival-core) module provides some animations that you can use with your images or sprites :
- [AnimationBase](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/domain/animation/AnimationBase.java) : Basic Animation, displays nothing, must have a name and a duration, this class should be extended to have a real animation. Do not use
- [PropertiesAnimation](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/domain/animation/PropertiesAnimation.java) : AnimationBase extension which uses a list of frames to display on the client (one frame = one image). Duration is the total duration of the Animation, each frame last for duration/number of frames.
- [SpriteAnimation](https://github.com/groupe-sii/devoxx-2015-game-back/blob/master/survival-core/src/main/java/fr/sii/survival/core/domain/animation/SpriteAnimation.java) : PropertiesAnimation extension that displays a frame for each image of the sprite. The server will generates a linear sprite with all the images you provides in the folder. /!\ All your images must be 60x60px.

#Samples

## Immobilize
```java
@Developer(value="abaudet", name="Aurélien Baudet", email="abaudet@sii.fr")
public class ImmobilizeAnimationProvider implements AnimationProvider {
	
	/**
	 * This animation is used for the immobilize Rule and displays a root sprite on the targeted board cell
	 * All the sprite is hosted in this extension module and informations sent to the client by websocket.
	 * 
	 * @return an SpriteAnimation
	 * @throws AnimationInitializationException if error occurs while initializing the SpriteAnimation
	 */
	@Override
	public Animation provide() throws AnimationInitializationException {
		try {
			return new SpriteAnimation(States.IMMOBILIZED.getValue(), 5000, new UriSprite("images/immobilize"));
		} catch(IOException | MimetypeDetectionException e) {
			throw new AnimationInitializationException("Failed to initialize 'immobilized' animation", e);
		}
	}
}
```