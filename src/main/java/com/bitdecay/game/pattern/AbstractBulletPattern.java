package com.bitdecay.game.pattern;

import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.component.ShootComponent;
import com.bitdecay.game.component.WeaponComponent;
import com.bitdecay.game.gameobject.MyGameObject;

import java.util.List;

public abstract class AbstractBulletPattern {
    public abstract List<MyGameObject> generateBulletPattern(PositionComponent pos, ShootComponent shoot, WeaponComponent weapon);
}