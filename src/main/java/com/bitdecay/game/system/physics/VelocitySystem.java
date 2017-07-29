package com.bitdecay.game.system.physics;

import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.component.VelocityComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractForEachUpdatableSystem;

public class VelocitySystem extends AbstractForEachUpdatableSystem {
    public VelocitySystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponent(PositionComponent.class) && gob.hasComponent(VelocityComponent.class);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        PositionComponent pos = gob.getComponent(PositionComponent.class).get();
        VelocityComponent vel = gob.getComponent(VelocityComponent.class).get();
        pos.x += vel.x * delta;
        pos.y += vel.y * delta;
    }
}
