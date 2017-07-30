package com.bitdecay.game.component;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bitdecay.game.Launcher;
import com.bitdecay.game.MyGame;
import com.bitdecay.game.animation.Animation;
import com.bitdecay.game.animation.AnimationDirection;
import com.bitdecay.game.animation.AnimationState;
import com.bytebreakstudios.animagic.animation.FrameRate;
import com.bytebreakstudios.animagic.texture.AnimagicTextureRegion;
import com.typesafe.config.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Monday on 7/29/2017.
 */
public class AnimationComponent extends DrawableComponent {
    public Map<AnimationState, Map<AnimationDirection, Animation>> animations;

    public Animation activeAnimation;

    public AnimationComponent(Config conf) {
        String bundle = conf.getString("bundle");
        animations = AnimationComponent.parseAnimationBundle(bundle);
    }

    private static Map<AnimationState, Map<AnimationDirection, Animation>> parseAnimationBundle(String bundleName) {
        Map<AnimationState, Map<AnimationDirection, Animation>> map = new HashMap<>();
        Config bundleConf = Launcher.conf.getConfig("animation_bundles." + bundleName);
        for (AnimationState state : AnimationState.values()) {
            String stateText = state.name().toLowerCase();
            if (bundleConf.hasPath(stateText)) {
                String statePath = "animations." + bundleConf.getString(stateText);
                if (Launcher.conf.hasPath(statePath)) {
                    map.put(state, AnimationComponent.parseAnimationState(Launcher.conf.getConfig(statePath)));
                }
            }
        }
        return map;
    }

    private static Map<AnimationDirection, Animation> parseAnimationState(Config config) {
        Map<AnimationDirection, Animation> directionMap = new HashMap<>();
        for (AnimationDirection direction : AnimationDirection.values()) {
            String dirName = direction.name().toLowerCase();
            if (config.hasPath(dirName)) {
                Config dirConf = config.getConfig(dirName);
                List<String> framePaths = dirConf.getStringList("frames");
                ArrayList<TextureRegion> regions = new ArrayList<>();
                for (String framePath : framePaths) {
                    for (AnimagicTextureRegion animagicTextureRegion : MyGame.ATLAS.findRegions(framePath)) {
                        regions.add(animagicTextureRegion);
                    }
                }
                Animation animation = new Animation("name", com.bytebreakstudios.animagic.animation.Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.1f), regions.toArray(new TextureRegion[regions.size()]));
                directionMap.put(direction, animation);
            }
        }
        return directionMap;
    }

    @Override
    public TextureRegion image() {
        if (activeAnimation == null) {
            return new TextureRegion();
        } else {
            return activeAnimation.getFrame();
        }
    }
}
