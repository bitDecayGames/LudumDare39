package com.bitdecay.game.pattern;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.component.*;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.gameobject.MyGameObjectFromConf;
import com.bitdecay.game.util.VectorMath;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SingleRandomSprayBulletPattern extends AbstractBulletPattern {
    private float sprayDegrees = 25;
    private Random rnd = new Random();

    public SingleRandomSprayBulletPattern(){ }

    public SingleRandomSprayBulletPattern(float sprayDegrees){
        this.sprayDegrees = sprayDegrees;
    }

    @Override
    public List<MyGameObject> generateBulletPattern(PositionComponent pos, ShootComponent shoot, WeaponComponent weapon){
        Vector2 direction = VectorMath.rotatePointByDegreesAroundZero(shoot.toVector2(), (0.5f - rnd.nextFloat()) * sprayDegrees);

        MyGameObject bullet = MyGameObjectFromConf.objectFromConf(weapon.bullet, pos.x, pos.y);
        bullet.forEach(VelocityComponent.class, velocity -> bullet.forEach(SpeedComponent.class, speed -> {
            velocity.x = direction.x * speed.speed;
            velocity.y = direction.y * speed.speed;
        }));
        return Collections.singletonList(bullet);
    }
}