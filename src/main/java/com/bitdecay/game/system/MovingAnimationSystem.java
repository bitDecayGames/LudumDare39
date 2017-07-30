package com.bitdecay.game.system;

import com.bitdecay.game.animation.Animation;
import com.bitdecay.game.animation.AnimationDirection;
import com.bitdecay.game.animation.AnimationState;
import com.bitdecay.game.animation.AnimationUtils;
import com.bitdecay.game.component.MovingAnimationComponent;
import com.bitdecay.game.component.ShootComponent;
import com.bitdecay.game.component.VelocityComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractForEachUpdatableSystem;

/**
 * Created by Monday on 7/29/2017.
 */
public class MovingAnimationSystem extends AbstractForEachUpdatableSystem {
    public MovingAnimationSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        gob.forEachComponentDo(VelocityComponent.class, velocity -> {
            gob.forEachComponentDo(MovingAnimationComponent.class, animation -> {
                AnimationState state = AnimationUtils.getStateFromVelocity(velocity.toVector2());
                AnimationDirection moveDirection = AnimationUtils.getDirectionFromInfo(velocity.lastIntended);

                boolean flipped = false;
                if (gob.hasComponents(ShootComponent.class)) {
                    ShootComponent shoot = gob.getComponent(ShootComponent.class).get();
                    AnimationDirection shootDirection = AnimationUtils.getDirectionFromInfo(shoot.lastIntended);

                    if (AnimationDirection.moreThanNinetyOff(moveDirection, shootDirection)) {
                        moveDirection = AnimationDirection.flip(moveDirection);
                    }
                }
                Animation chosenAnimation = animation.animations.get(state).get(moveDirection);
                animation.activeAnimation = chosenAnimation;

                if (flipped) {
                    chosenAnimation.update(-delta);
                } else {
                    chosenAnimation.update(delta);

                }
            });
        });
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(
                MovingAnimationComponent.class,
                VelocityComponent.class
        );
    }
}
