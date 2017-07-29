package com.bitdecay.game.system;

import com.bitdecay.game.component.ScheduleComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractForEachUpdatableSystem;
import com.bitdecay.game.task.AbstractTask;

public class ScheduleSystem extends AbstractForEachUpdatableSystem {
    public ScheduleSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(ScheduleComponent.class);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        gob.forEach(ScheduleComponent.class, scheduler -> {
            boolean allTasksComplete = true;
            for (AbstractTask task : scheduler.taskList) {
                if (!task.hasStarted()){
                    task.timeToWait -= delta;
                    if (task.timeToWait <= 0) task.start(room);
                    allTasksComplete = false;
                } else if (!task.isDone(room)) {
                    task.update(delta, room);
                    allTasksComplete = false;
                }
            }
            scheduler.isComplete = allTasksComplete;
        });
    }

}
