package com.bitdecay.game.system;

import com.badlogic.gdx.Input;
import com.bitdecay.game.component.PlayerInputComponent;
import com.bitdecay.game.component.ShootComponent;
import com.bitdecay.game.component.WeaponSelectionComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractUpdatableSystem;
import com.bitdecay.game.util.InputHelper;

/**
 * Created by tristan on 7/29/17.
 */
public class KeyboardWeaponChangeSystem extends AbstractUpdatableSystem {
    public KeyboardWeaponChangeSystem(AbstractRoom room) { super(room); }

    @Override
    public void update(float delta) {

        gobs.forEach(gob -> gob.forEachComponentDo(PlayerInputComponent.class, pi ->
                gob.forEachComponentDo(WeaponSelectionComponent.class, ws -> {
                    if (InputHelper.isKeyJustPressed(Input.Keys.SHIFT_LEFT)) {
                        ws.selectedIndex += 1;
                    }
                }))
        );
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(PlayerInputComponent.class);
    }

}


