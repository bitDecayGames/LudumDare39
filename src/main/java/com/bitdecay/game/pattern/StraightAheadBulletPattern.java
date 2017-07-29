package com.bitdecay.game.pattern;

import com.bitdecay.game.component.*;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.gameobject.MyGameObjectFromConf;

import java.util.Collections;
import java.util.List;

public class StraightAheadBulletPattern extends AbstractBulletPattern {
    @Override
    public List<MyGameObject> generateBulletPattern(PositionComponent pos, ShootComponent shoot, WeaponComponent weapon){
        MyGameObject bullet = MyGameObjectFromConf.objectFromConf(weapon.bullet, pos.x, pos.y);
        bullet.forEach(VelocityComponent.class, velocity -> bullet.forEach(SpeedComponent.class, speed -> {
            velocity.x = shoot.x * speed.speed;
            velocity.y = shoot.y * speed.speed;
        }));
        return Collections.singletonList(bullet);
    }
}