package com.bitdecay.game.system.physics;

import com.bitdecay.game.component.FrictionComponent;
import com.bitdecay.game.component.VelocityComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractForEachUpdatableSystem;

public class FrictionSystem extends AbstractForEachUpdatableSystem {
    public FrictionSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponent(FrictionComponent.class) && gob.hasComponent(VelocityComponent.class);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        float friction = gob.getComponent(FrictionComponent.class).get().friction;
        VelocityComponent vel = gob.getComponent(VelocityComponent.class).get();
        vel.scale(1 - friction);
    }
}
