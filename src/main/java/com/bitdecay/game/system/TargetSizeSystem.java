package com.bitdecay.game.system;

import com.bitdecay.game.component.*;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractForEachUpdatableSystem;

public class TargetSizeSystem extends AbstractForEachUpdatableSystem {
    public TargetSizeSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(TargetSizeComponent.class, HealthComponent.class, SizeComponent.class);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        TargetSizeComponent targetSize = gob.getComponent(TargetSizeComponent.class).get();
        SizeComponent size = gob.getComponent(SizeComponent.class).get();
        HealthComponent health = gob.getComponent(HealthComponent.class).get();

        float percentage = 1 - (health.hp / health.maxHp);
        size.w = targetSize.w * percentage;
        size.h = targetSize.h * percentage;
    }
}
