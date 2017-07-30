package com.bitdecay.game.system;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.component.PlayerInputComponent;
import com.bitdecay.game.component.SpeedComponent;
import com.bitdecay.game.component.VelocityComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractUpdatableSystem;
import com.bitdecay.game.util.InputHelper;

/**
* This system will handle player keyboard input to modify the rotation of the player
*/
public class KeyboardMoveSystem extends AbstractUpdatableSystem {
    public KeyboardMoveSystem(AbstractRoom room) { super(room); }

    @Override
    public void update(float delta) {
        int desiredMoveY = 0;
        int desiredMoveX = 0;

        if (InputHelper.isKeyPressed(Input.Keys.W)) {
            desiredMoveY = 1;
        }
        if (InputHelper.isKeyPressed(Input.Keys.A)) {
            desiredMoveX = -1;
        }
        if (InputHelper.isKeyPressed(Input.Keys.S)) {
            desiredMoveY = -1;
        }
        if (InputHelper.isKeyPressed(Input.Keys.D)) {
            desiredMoveX = 1;
        }

        final int finalDesiredMoveX = desiredMoveX;
        final int finalDesiredMoveY = desiredMoveY;

        gobs.forEach(gob -> gob.forEach(VelocityComponent.class, velocity -> gob.forEach(SpeedComponent.class, speed -> {
            Vector2 intendedDirection = new Vector2(finalDesiredMoveX, finalDesiredMoveY);
            if (intendedDirection.len() != 0) {
                velocity.lastIntended.set(intendedDirection);
            }
            velocity.x = speed.speed * finalDesiredMoveX;
            velocity.y = speed.speed * finalDesiredMoveY;
        })));
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(PlayerInputComponent.class, VelocityComponent.class, SpeedComponent.class);
    }
}
