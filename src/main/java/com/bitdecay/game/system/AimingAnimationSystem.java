package com.bitdecay.game.system;

import com.bitdecay.game.animation.AnimationDirection;
import com.bitdecay.game.animation.AnimationState;
import com.bitdecay.game.animation.AnimationUtils;
import com.bitdecay.game.component.AimingAnimationComponent;
import com.bitdecay.game.component.ShootComponent;
import com.bitdecay.game.component.VelocityComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractForEachUpdatableSystem;
import com.bytebreakstudios.animagic.animation.Animation;

/**
 * Created by Monday on 7/29/2017.
 */
public class AimingAnimationSystem extends AbstractForEachUpdatableSystem {
    public AimingAnimationSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        gob.forEachComponentDo(VelocityComponent.class, velocity -> {
            gob.forEachComponentDo(ShootComponent.class, shoot -> {
                gob.forEachComponentDo(AimingAnimationComponent.class, animation -> {
                    AnimationState state = AnimationUtils.getStateFromVelocity(velocity.toVector2());
                    AnimationDirection direction = AnimationUtils.getDirectionFromInfo(shoot.toVector2());

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
                AimingAnimationComponent.class,
                ShootComponent.class,
                VelocityComponent.class
        );
    }
}
