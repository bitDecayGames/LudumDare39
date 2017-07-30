package com.bitdecay.game.animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.bitdecay.game.Launcher;
import com.bitdecay.game.MyGame;
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
public class AnimationUtils {
    public static Map<AnimationState, Map<AnimationDirection, Animation>> parseAnimationBundle(String bundleName) {
        Map<AnimationState, Map<AnimationDirection, Animation>> map = new HashMap<>();
        Config bundleConf = Launcher.conf.getConfig("animation_bundles." + bundleName);
        for (AnimationState state : AnimationState.values()) {
            String stateText = state.name().toLowerCase();
            if (bundleConf.hasPath(stateText)) {
                String statePath = "animations." + bundleConf.getString(stateText);
                if (Launcher.conf.hasPath(statePath)) {
                    map.put(state, parseAnimationState(Launcher.conf.getConfig(statePath)));
                }
            }
        }
        return map;
    }

    public static Map<AnimationDirection, Animation> parseAnimationState(Config config) {
        Map<AnimationDirection, Animation> directionMap = new HashMap<>();
        for (AnimationDirection direction : AnimationDirection.values()) {
            String dirName = direction.name().toLowerCase();
            if (config.hasPath(dirName)) {
                Config dirConf = config.getConfig(dirName);
                List<String> framePaths = dirConf.getStringList("frames");
                ArrayList<TextureRegion> regions = new ArrayList<>();
                for (String framePath : framePaths) {
                    Array<AnimagicTextureRegion> animTextureRegions = MyGame.ATLAS.findRegions(framePath);
                    if (animTextureRegions.size == 0) throw new RuntimeException("Got regions of 0 size with framePath: " + framePath);
                    for (AnimagicTextureRegion animagicTextureRegion : animTextureRegions) {
                        if (animagicTextureRegion.getTexture() == null) throw new RuntimeException("Got null texture in region: " + framePath);
                        regions.add(animagicTextureRegion);
                    }
                }
                if (regions.size() == 0) throw new RuntimeException("Got regions of 0 size with framePaths: " + framePaths);
                Animation animation = new Animation("name", com.bytebreakstudios.animagic.animation.Animation.AnimationPlayState.REPEAT, FrameRate.perFrame(0.1f), regions.toArray(new TextureRegion[regions.size()]));
                directionMap.put(direction, animation);
            }
        }
        return directionMap;
    }

    public static AnimationState getStateFromVelocity(Vector2 velocity) {
        if (velocity.len() > 0.2f) {
            return AnimationState.WALK;
        } else {
            return AnimationState.IDLE;
        }
    }

    public static AnimationDirection getDirectionFromInfo(Vector2 aimDir) {
        float angle = aimDir.angle();
        if (angle >= 337.5 || angle < 22.5) {
            return AnimationDirection.RIGHT;
        }

        if (angle >= 22.5 && angle < 67.5) {
            return AnimationDirection.UPRIGHT;
        }

        if (angle >= 67.5 && angle < 112.5) {
            return AnimationDirection.UP;
        }

        if (angle >= 112.5 && angle < 157.5) {
            return AnimationDirection.UPLEFT;
        }

        if (angle >= 157.5 && angle < 202.5) {
            return AnimationDirection.LEFT;
        }

        if (angle >= 202.5 && angle < 247.5) {
            return AnimationDirection.DOWNLEFT;
        }

        if (angle >= 247.5 && angle < 292.5) {
            return AnimationDirection.DOWN;
        }

        if (angle >= 292.5 && angle < 337.5) {
            return AnimationDirection.DOWNRIGHT;
        }

        return AnimationDirection.DOWN;
    }
}
