package com.bitdecay.game.task;

import com.bitdecay.game.gameobject.MyGameObjectFromConf;
import com.bitdecay.game.room.AbstractRoom;
import com.typesafe.config.Config;

public class BoundarySetupTask extends AbstractTask {

    public BoundarySetupTask(Config conf) {
        timeToWait = (float) conf.getDouble("time");
    }

    @Override
    public void start(AbstractRoom room) {
        super.start(room);
        room.getGameObjects().add(MyGameObjectFromConf.objectFromConf("NorthBoundary", 0, 0));
        room.getGameObjects().add(MyGameObjectFromConf.objectFromConf("WestBoundary", 0, 0));
        room.getGameObjects().add(MyGameObjectFromConf.objectFromConf("SouthBoundary", 0, 0));
        room.getGameObjects().add(MyGameObjectFromConf.objectFromConf("EastBoundary", 0, 0));
    }

    @Override
    public boolean isDone(AbstractRoom room) { return true; }
}
