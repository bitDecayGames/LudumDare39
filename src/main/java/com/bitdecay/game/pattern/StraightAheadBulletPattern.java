package com.bitdecay.game.pattern;

import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.component.ShootComponent;
import com.bitdecay.game.component.WeaponComponent;
import com.bitdecay.game.gameobject.MyGameObject;

import java.util.Collections;
import java.util.List;

public class StraightAheadBulletPattern extends AbstractBulletPattern {
    @Override
    public List<MyGameObject> generateBulletPattern(PositionComponent pos, ShootComponent shoot, WeaponComponent weapon){
        return Collections.singletonList(createBullet(weapon.bullet, pos.x, pos.y, shoot.toVector2()));
    }
}