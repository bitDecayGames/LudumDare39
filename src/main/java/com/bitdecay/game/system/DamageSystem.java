package com.bitdecay.game.system;

import com.bitdecay.game.component.DamageComponent;
import com.bitdecay.game.component.DamagePerSecondComponent;
import com.bitdecay.game.component.HealthComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractForEachUpdatableSystem;

public class DamageSystem extends AbstractForEachUpdatableSystem {
    public DamageSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponent(HealthComponent.class) && (gob.hasComponent(DamagePerSecondComponent.class) || gob.hasComponent(DamageComponent.class));
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        gob.forEach(HealthComponent.class, health -> {
            gob.forEach(DamageComponent.class, dmg -> {
                if (health.hp > 0) health.hp -= dmg.damage;
                gob.removeComponent(dmg);
            });
            gob.forEach(DamagePerSecondComponent.class, dmgPerSec -> {
                if (health.hp > 0) health.hp -= dmgPerSec.damage * delta;
            });
        });
    }
}
