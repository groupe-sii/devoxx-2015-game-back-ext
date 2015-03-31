package fr.sii.survival.ext.animation;

import java.io.IOException;

import fr.sii.survival.core.domain.animation.Animation;
import fr.sii.survival.core.domain.animation.SpriteAnimation;
import fr.sii.survival.core.domain.image.UriSprite;
import fr.sii.survival.core.exception.AnimationInitializationException;
import fr.sii.survival.core.exception.MimetypeDetectionException;
import fr.sii.survival.core.ext.animation.AnimationProvider;
import fr.sii.survival.core.ext.annotation.Developer;
import fr.sii.survival.ext.constants.States;

/**
 * Animation that will start every time the CSS class "immobilized" is added on a player.
 * 
 * @author Aurélien Baudet
 *
 */
@Developer(value="abaudet", name="Aurélien Baudet", email="abaudet@sii.fr")
public class ImmobilizeAnimationProvider implements AnimationProvider {
	
	@Override
	public Animation provide() throws AnimationInitializationException {
		try {
			return new SpriteAnimation(States.IMMOBILIZED.toString(), 5000, new UriSprite("images/immobilize"));
		} catch(IOException | MimetypeDetectionException e) {
			throw new AnimationInitializationException("Failed to initialize 'immobilized' animation", e);
		}
	}
	
}
