package com.bitdecay.game.pattern;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.component.ShootComponent;
import com.bitdecay.game.component.WeaponComponent;
import com.bitdecay.game.gameobject.MyGameObject;
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

        return Collections.singletonList(createBullet(weapon.bullet, pos.x, pos.y, direction));
    }
}