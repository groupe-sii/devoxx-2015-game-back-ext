package fr.sii.survival.ext.abaudet.animation;

import java.io.IOException;

import fr.sii.survival.core.domain.animation.Animation;
import fr.sii.survival.core.domain.animation.SpriteAnimation;
import fr.sii.survival.core.domain.image.UriSprite;
import fr.sii.survival.core.exception.AnimationInitializationException;
import fr.sii.survival.core.exception.MimetypeDetectionException;
import fr.sii.survival.core.ext.animation.AnimationProvider;
import fr.sii.survival.core.ext.annotation.Developer;
import fr.sii.survival.ext.abaudet.constants.States;

/**
 * Animation that will start every time the CSS class "immobilized" is added on
 * a player.
 * 
 * @author Aurélien Baudet
 *
 */
@Developer(value="abaudet", name="Aurélien Baudet", email="abaudet@sii.fr")
public class ImmobilizeAnimationProvider implements AnimationProvider {
	
	/**
	 * This animation is used for the immobilize Rule and displays a root sprite
	 * on the targeted board cell All the sprite is hosted in this extension
	 * module and informations sent to the client by websocket.
	 * 
	 * @return a SpriteAnimation
	 * @throws AnimationInitializationException
	 *             if error occurs while initializing the SpriteAnimation
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
