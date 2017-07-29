package com.bitdecay.game.system;

import com.bitdecay.game.component.*;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.gameobject.MyGameObjectFromConf;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractForEachUpdatableSystem;

public class ShootSystem extends AbstractForEachUpdatableSystem {
    public ShootSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponent(PositionComponent.class) && gob.hasComponent(ShootComponent.class) && gob.hasComponent(WeaponComponent.class);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        PositionComponent pos = gob.getComponent(PositionComponent.class).get();
        ShootComponent shoot = gob.getComponent(ShootComponent.class).get();
        WeaponComponent weapon = gob.getComponent(WeaponComponent.class).get();

        if (shoot.x != 0 || shoot.y != 0){

            // TODO: do some logic to only shoot the weapon at the correct speed and don't shoot if there are more than the max bullets
            MyGameObject bullet = MyGameObjectFromConf.objectFromConf(weapon.bullet, pos.x, pos.y);
            bullet.forEach(VelocityComponent.class, velocity -> bullet.forEach(SpeedComponent.class, speed -> {
                velocity.x = shoot.x * speed.speed;
                velocity.y = shoot.y * speed.speed;
            }));
            room.getGameObjects().add(bullet);
        }
    }
}
