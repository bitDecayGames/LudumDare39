package com.bitdecay.game.system;

import com.bitdecay.game.component.AimingAnimationComponent;
import com.bitdecay.game.component.WeaponComponent;
import com.bitdecay.game.component.WeaponSelectionComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractForEachUpdatableSystem;
import com.bitdecay.game.weapon.WeaponUtils;

/**
 * Created by Monday on 7/29/2017.
 */
public class WeaponAnimationSystem extends AbstractForEachUpdatableSystem {
    public WeaponAnimationSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        WeaponComponent selectedWeaponComponent = WeaponUtils.getSelectedWeaponComponent(gob);
        gob.forEachComponentDo(AimingAnimationComponent.class, animation -> {
            animation.animations = selectedWeaponComponent.animationMap;
        });
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(
                WeaponComponent.class,
                WeaponSelectionComponent.class,
                AimingAnimationComponent.class
                );
    }
}
