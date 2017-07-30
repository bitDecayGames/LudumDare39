package com.bitdecay.game.system;

import com.bitdecay.game.component.*;
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
        int width = 705;
        int height = 675;
        gob.forEachComponentDo(FloorComponent.class, floor -> {
            if (floor.floorLoaded) {
                return;
            } else {
                gob.addComponent(MyGameObjectFactory.objectFromConf(floor.schedule, 0, 0).getComponent(ScheduleComponent.class).get());
                gob.addComponent(new StaticImageComponent(floor.bgImage));
                gob.forEachComponentDo(SizeComponent.class, size -> {
                    size.set(width, height);
                });
                gob.forEachComponentDo(PositionComponent.class, pos -> {
                    pos.x = -width / 2 + 10;
                    pos.y = -height / 2 + 33;
                });
                room.camera.zoom += .1f;
                room.camera.position.set(0, 20, 0);
                floor.floorLoaded = true;
            }
        });
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponent(FloorComponent.class);
    }
}
