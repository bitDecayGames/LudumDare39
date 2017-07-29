package com.bitdecay.game.pattern;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.component.*;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.gameobject.MyGameObjectFromConf;
import com.bitdecay.game.util.VectorMath;

import java.util.Arrays;
import java.util.List;

public class TriShotBulletPattern extends AbstractBulletPattern {
    @Override
    public List<MyGameObject> generateBulletPattern(PositionComponent pos, ShootComponent shoot, WeaponComponent weapon){
        Vector2 shotForward = shoot.toVector2();
        Vector2 shotThatWay = VectorMath.rotatePointByDegreesAroundZero(shotForward.cpy(), 20);
        Vector2 shotTheOtherWay = VectorMath.rotatePointByDegreesAroundZero(shotForward.cpy(), -20);
        return Arrays.asList(
                createBullet(weapon.bullet, pos.x, pos.y, shotForward),
                createBullet(weapon.bullet, pos.x, pos.y, shotThatWay),
                createBullet(weapon.bullet, pos.x, pos.y, shotTheOtherWay)
        );
    }

    private MyGameObject createBullet(String bulletName, float x, float y, Vector2 direction){
        MyGameObject bullet = MyGameObjectFromConf.objectFromConf(bulletName, x, y);
        bullet.forEach(VelocityComponent.class, velocity -> bullet.forEach(SpeedComponent.class, speed -> {
            velocity.x = direction.x * speed.speed;
            velocity.y = direction.y * speed.speed;
        }));
        return bullet;
    }
}