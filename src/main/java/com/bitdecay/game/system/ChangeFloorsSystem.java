package com.bitdecay.game.system;

import com.bitdecay.game.component.FloorComponent;
import com.bitdecay.game.component.GobRefComponent;
import com.bitdecay.game.component.NextFloorComponent;
import com.bitdecay.game.component.ScheduleComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.gameobject.MyGameObjectFromConf;
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
                    System.out.println("TRIGGERING TRAP DOOR");
                    gob.removeComponent(ScheduleComponent.class);
                    System.out.println("Changing floors to " + floor.next);
                    MyGameObject trapDoor = MyGameObjectFromConf.objectFromConf("trapDoor", 0, 0);
                    trapDoor.addComponent(new GobRefComponent(gob));
                    trapDoor.addComponent(new NextFloorComponent(floor.next));
                    room.getGameObjects().add(trapDoor);
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
