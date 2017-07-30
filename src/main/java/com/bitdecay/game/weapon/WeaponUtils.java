package com.bitdecay.game.weapon;

import com.bitdecay.game.component.WeaponComponent;
import com.bitdecay.game.component.WeaponSelectionComponent;
import com.bitdecay.game.gameobject.MyGameObject;

/**
 * Created by Monday on 7/29/2017.
 */
public class WeaponUtils {
    public static WeaponComponent getSelectedWeaponComponent(MyGameObject gob) {
        WeaponSelectionComponent weaponSelection = gob.getComponent(WeaponSelectionComponent.class).get();
        int weaponCount = gob.getComponentCount(WeaponComponent.class);

        int selectedWeaponIndex = weaponSelection.selectedIndex % weaponCount;
        return (WeaponComponent) gob.getComponentByIndex(WeaponComponent.class, selectedWeaponIndex).get();
    }
}
