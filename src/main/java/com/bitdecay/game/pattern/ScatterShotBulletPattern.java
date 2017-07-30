package com.bitdecay.game.pattern;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.component.ShootComponent;
import com.bitdecay.game.component.WeaponComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.util.VectorMath;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ScatterShotBulletPattern extends AbstractBulletPattern {
    private Random rnd = new Random();

    @Override
    public List<MyGameObject> generateBulletPattern(PositionComponent pos, ShootComponent shoot, WeaponComponent weapon){
        Vector2 shotForward = shoot.toVector2();
        int numberOfBullets = 5 + (int) (rnd.nextFloat() * 10f);
        List<MyGameObject> bullets = new ArrayList<>();
        for (int i = 0; i < numberOfBullets; i++){
            bullets.add(createBullet(weapon.bullet, pos.x, pos.y, VectorMath.rotatePointByDegreesAroundZero(shotForward.cpy(), (rnd.nextFloat() * 2 - 1) * 25)));
        }
        return bullets;
    }


}