package com.bitdecay.game.system;

import com.bitdecay.game.component.*;
import com.bitdecay.game.event.EventReactor;
import com.bitdecay.game.event.TrapdoorTouchEvent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.gameobject.MyGameObjectFactory;
import com.bitdecay.game.physics.Manifold;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractForEachUpdatableSystem;

/**
 * Created by Monday on 7/30/2017.
 */
public class TrapDoorSystem extends AbstractForEachUpdatableSystem {
    public TrapDoorSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        // add the new floor if the player has collided with the trap door.
        gob.forEachComponentDo(NextFloorComponent.class, next -> {
            gob.forEachComponentDo(CollisionComponent.class, collision -> {
                gob.forEachComponentDo(GobRefComponent.class, floorRef -> {
                    for (Manifold manifold : collision.manifolds) {
                        if (!next.triggered && isPlayer(manifold.other)) {
                            next.triggered = true;
                            // we've collided with the player! (I think)
                            floorRef.gob.removeComponent(FloorComponent.class);
                            floorRef.gob.removeComponent(ScheduleComponent.class);
                            floorRef.gob.removeComponent(StaticImageComponent.class);
                            floorRef.gob.addComponent(MyGameObjectFactory.objectFromConf(next.name, 0, 0).getComponent(FloorComponent.class).get());
                            gob.addComponent(RemoveNowComponent.class);
                            EventReactor.fireEvent(new TrapdoorTouchEvent());
                            return;
                        }
                    }
                });
            });
        });
    }

    private boolean isPlayer(MyGameObject gob) {
        return gob.hasComponents(
                PlayerInputComponent.class
        );
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(
                NextFloorComponent.class,
                GobRefComponent.class,
                CollisionComponent.class
        );
    }
}
