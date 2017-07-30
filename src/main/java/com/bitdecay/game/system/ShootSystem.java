package com.bitdecay.game.system;

import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.component.ShootComponent;
import com.bitdecay.game.component.WeaponComponent;
import com.bitdecay.game.event.EventReactor;
import com.bitdecay.game.event.MachineGunFireEvent;
import com.bitdecay.game.event.ReloadShotgunEvent;
import com.bitdecay.game.event.ShotgunFireEvent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractForEachUpdatableSystem;
import com.bitdecay.game.weapon.WeaponUtils;

import java.util.List;

public class ShootSystem extends AbstractForEachUpdatableSystem {
    public ShootSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(PositionComponent.class, ShootComponent.class, WeaponComponent.class);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        PositionComponent pos = gob.getComponent(PositionComponent.class).get();
        ShootComponent shoot = gob.getComponent(ShootComponent.class).get();

        WeaponComponent weapon = WeaponUtils.getSelectedWeaponComponent(gob);

        // TODO: do some logic to only shoot the weapon at the correct speed and don't shoot if there are more than the max bullets
        if ((shoot.x != 0 || shoot.y != 0) && weapon.cooldown <= 0 && (weapon.unlimitedAmmo || weapon.ammo > 0)){
            List<MyGameObject> bullets = weapon.pattern.generateBulletPattern(pos, shoot, weapon);
            bullets.forEach(bullet -> {
                room.getGameObjects().add(bullet);

                switch(weapon.weaponName){
                    case "MachineGun":
                        EventReactor.fireEvent(new MachineGunFireEvent());
                        break;
                    case "Shotgun":
                        EventReactor.fireEvent(new ShotgunFireEvent());
                        EventReactor.fireDelayedEvent(0.5f, new ReloadShotgunEvent());
                        break;
                    case "Rocket":
                        EventReactor.fireEvent(new ShotgunFireEvent());
                        break;
                    case "Thrower":
                        EventReactor.fireEvent(new MachineGunFireEvent());
                        break;
                }
            });
            weapon.cooldown = weapon.secondsPerBullet;
            if (!weapon.unlimitedAmmo) weapon.ammo -= bullets.size();
        } else if (weapon.cooldown > 0){
            weapon.cooldown -= delta;
        }
    }
}
