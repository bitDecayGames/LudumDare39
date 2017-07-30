package com.bitdecay.game.pattern;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.component.ShootComponent;
import com.bitdecay.game.component.SpeedComponent;
import com.bitdecay.game.component.VelocityComponent;
import com.bitdecay.game.component.WeaponComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.gameobject.MyGameObjectFromConf;

import java.util.List;

public abstract class AbstractBulletPattern {
    public abstract List<MyGameObject> generateBulletPattern(Vector2 pos, ShootComponent shoot, WeaponComponent weapon);

    protected MyGameObject createBullet(String bulletName, float x, float y, Vector2 direction){
        MyGameObject bullet = MyGameObjectFromConf.objectFromConf(bulletName, x, y);
        bullet.forEach(VelocityComponent.class, velocity -> bullet.forEach(SpeedComponent.class, speed -> {
            velocity.set(direction.cpy().nor().scl(speed.speed));
//            velocity.x = direction.x * speed.speed;
//            velocity.y = direction.y * speed.speed;
        }));
        return bullet;
    }
}