package com.bitdecay.game.system;

import com.bitdecay.game.component.FloorComponent;
import com.bitdecay.game.component.ScheduleComponent;
import com.bitdecay.game.component.StaticImageComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.gameobject.MyGameObjectFactory;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractForEachUpdatableSystem;

/**
 * Created by Monday on 7/29/2017.
 */
public class FloorLoadSystem extends AbstractForEachUpdatableSystem {
    public FloorLoadSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        gob.forEachComponentDo(FloorComponent.class, floor -> {
            if (floor.floorLoaded) {
                return;
            } else {
                gob.addComponent(MyGameObjectFactory.objectFromConf(floor.schedule, 0, 0).getComponent(ScheduleComponent.class).get());
                gob.addComponent(new StaticImageComponent(floor.bgImage));
                floor.floorLoaded = true;
            }
        });
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponent(FloorComponent.class);
    }
}
