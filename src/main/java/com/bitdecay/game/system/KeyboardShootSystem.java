package com.bitdecay.game.system;

import com.badlogic.gdx.Input;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.system.abstracted.AbstractUpdatableSystem;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.component.PlayerInputComponent;
import com.bitdecay.game.component.ShootComponent;
import com.bitdecay.game.util.InputHelper;

/**
 * This system will handle player keyboard input to modify the rotation of the player
 */
public class KeyboardShootSystem extends AbstractUpdatableSystem {
    public KeyboardShootSystem(AbstractRoom room) { super(room); }

    @Override
    public void update(float delta) {
        int desiredShootY = 0;
        int desiredShootX = 0;

        if (InputHelper.isKeyPressed(Input.Keys.UP)) {
            desiredShootY = 1;
        }
        if (InputHelper.isKeyPressed(Input.Keys.LEFT)) {
            desiredShootX = -1;
        }
        if (InputHelper.isKeyPressed(Input.Keys.DOWN)) {
            desiredShootY = -1;
        }
        if (InputHelper.isKeyPressed(Input.Keys.RIGHT)) {
            desiredShootX = 1;
        }

        final int finalDesiredShootY = desiredShootY;
        final int finalDesiredShootX = desiredShootX;

        gobs.forEach(gob -> gob.forEachComponentDo(PlayerInputComponent.class, pi ->
            gob.forEachComponentDo(ShootComponent.class, shoot -> {
                shoot.x = finalDesiredShootX;
                shoot.y = finalDesiredShootY;
            }))
        );
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(PlayerInputComponent.class);
    }
}
