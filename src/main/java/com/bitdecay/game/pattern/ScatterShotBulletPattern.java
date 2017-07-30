package com.bitdecay.game.pattern;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.component.ShootComponent;
import com.bitdecay.game.component.VelocityComponent;
import com.bitdecay.game.component.WeaponComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.util.VectorMath;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ScatterShotBulletPattern extends AbstractBulletPattern {
    private Random rnd = new Random();

    @Override
    public List<MyGameObject> generateBulletPattern(Vector2 pos, ShootComponent shoot, WeaponComponent weapon){
        Vector2 shotForward = shoot.toVector2();
        int numberOfBullets = 20 + (int) (rnd.nextFloat() * 10f);
        List<MyGameObject> bullets = new ArrayList<>();
        for (int i = 0; i < numberOfBullets; i++){
            bullets.add(createBullet(weapon.bullet, pos.x, pos.y, VectorMath.rotatePointByDegreesAroundZero(shotForward.cpy(), (rnd.nextFloat() * 2 - 1) * 25)));
        }
        bullets.forEach(gob -> gob.forEach(VelocityComponent.class, vel -> vel.set(vel.toVector2().scl(rnd.nextFloat() + 0.5f))));
        return bullets;
    }


}