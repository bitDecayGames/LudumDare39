package com.bitdecay.game.animation;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Monday on 7/29/2017.
 */
public class AnimationUtils {

    public static AnimationState getStateFromVelocity(Vector2 velocity) {
        if (velocity.len() > 0.2f) {
            return AnimationState.WALK;
        } else {
            return AnimationState.IDLE;
        }
    }

    public static AnimationDirection getDirectionFromInfo(Vector2 aimDir) {
        if (aimDir.x > 0) {
            if (aimDir.y > 0) {
                return AnimationDirection.UPRIGHT;
            } else if (aimDir.y < 0) {
                return AnimationDirection.DOWNRIGHT;
            } else {
                return AnimationDirection.RIGHT;
            }
        } else if (aimDir.x < 0) {
            if (aimDir.y > 0) {
                return AnimationDirection.UPLEFT;
            } else if (aimDir.y < 0) {
                return AnimationDirection.DOWNLEFT ;
            } else {
                return AnimationDirection.LEFT;
            }
        } else {
            if (aimDir.y > 0) {
                return AnimationDirection.UP;
            } else {
                return AnimationDirection.DOWN;
            }
        }
    }
}
