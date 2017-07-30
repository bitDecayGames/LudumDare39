package com.bitdecay.game.pattern;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.component.ShootComponent;
import com.bitdecay.game.component.WeaponComponent;
import com.bitdecay.game.gameobject.MyGameObject;
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
}