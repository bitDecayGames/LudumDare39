package com.bitdecay.game.system;

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
        return gob.hasComponent(HealthComponent.class) && (
                gob.hasComponent(DamagePerSecondComponent.class) // || other damage components
                );
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        gob.forEach(HealthComponent.class, health -> {

            gob.forEach(DamagePerSecondComponent.class, dmgPerSec -> health.hp -= dmgPerSec.damage * delta);
        });
    }
}
