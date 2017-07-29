package com.bitdecay.game.system;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.animation.AnimationDirection;
import com.bitdecay.game.animation.AnimationState;
import com.bitdecay.game.component.AnimationComponent;
import com.bitdecay.game.component.ShootComponent;
import com.bitdecay.game.component.VelocityComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractForEachUpdatableSystem;
import com.bytebreakstudios.animagic.animation.Animation;

/**
 * Created by Monday on 7/29/2017.
 */
public class CharacterAnimationSystem extends AbstractForEachUpdatableSystem {
    public CharacterAnimationSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        gob.forEachComponentDo(VelocityComponent.class, velocity -> {
            gob.forEachComponentDo(ShootComponent.class, shoot -> {
                gob.forEachComponentDo(AnimationComponent.class, animation -> {
                    AnimationState state = getStateFromVelocity(velocity.toVector2());
                    AnimationDirection direction = getDirectionFromInfo(velocity.toVector2(), shoot.toVector2());

                    Animation chosenAnimation = animation.animations.get(state).get(direction);
                    animation.activeAnimation = chosenAnimation;
                    chosenAnimation.update(delta);
                });
            });
        });
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(
                AnimationComponent.class,
                VelocityComponent.class,
                ShootComponent.class
        );
    }

    private AnimationState getStateFromVelocity(Vector2 velocity) {
        if (velocity.len() > 0.2f) {
            return AnimationState.WALK;
        } else {
            return AnimationState.IDLE;
        }
    }

    private AnimationDirection getDirectionFromInfo(Vector2 moveSpeed, Vector2 aimDir) {
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
