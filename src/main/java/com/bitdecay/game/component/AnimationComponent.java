package com.bitdecay.game.component;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bitdecay.game.animation.Animation;
import com.bitdecay.game.animation.AnimationDirection;
import com.bitdecay.game.animation.AnimationState;
import com.bitdecay.game.animation.AnimationUtils;
import com.typesafe.config.Config;

import java.util.Map;

/**
 * Created by Monday on 7/29/2017.
 */
public class AnimationComponent extends DrawableComponent {
    public Map<AnimationState, Map<AnimationDirection, Animation>> animations;

    public Animation activeAnimation;

    public AnimationComponent(Config conf) {
        String bundle = conf.getString("bundle");
        animations = AnimationUtils.parseAnimationBundle(bundle);
        activeAnimation = animations.get(AnimationState.IDLE).get(AnimationDirection.DOWN);
    }

    @Override
    public TextureRegion image() {
        if (activeAnimation == null) {
            throw new RuntimeException("Active animation was null");
        } else {
            return activeAnimation.getFrame();
        }
    }
}
