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
public class ChangeFloorsSystem extends AbstractForEachUpdatableSystem {
    public ChangeFloorsSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        gob.forEachComponentDo(FloorComponent.class, floor -> {
            gob.forEachComponentDo(ScheduleComponent.class, schedule -> {
                if (schedule.isComplete) {
                    // change floors automatically
                    System.out.println("Changing floors to " + floor.next);
                    gob.removeComponent(FloorComponent.class);
                    gob.removeComponent(ScheduleComponent.class);
                    gob.removeComponent(StaticImageComponent.class);
                    gob.addComponent(MyGameObjectFactory.objectFromConf(floor.next, 0, 0).getComponent(FloorComponent.class).get());
                }
            });
        });
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(
                FloorComponent.class,
                ScheduleComponent.class
        );
    }
}
