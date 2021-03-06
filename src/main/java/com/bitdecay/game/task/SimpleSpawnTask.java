package com.bitdecay.game.task;

import com.bitdecay.game.gameobject.MyGameObjectFromConf;
import com.bitdecay.game.room.AbstractRoom;
import com.typesafe.config.Config;

public class SimpleSpawnTask extends AbstractTask {

    private String gameObjectNameToSpawn;
    private float x;
    private float y;


    public SimpleSpawnTask(Config conf) {
        timeToWait = conf.hasPath("time") ? (float) conf.getDouble("time") : 0;
        gameObjectNameToSpawn = conf.getString("gob");
        MyGameObjectFromConf.objectFromConf(gameObjectNameToSpawn, 0, 0); // run this at initialization to verify that the conf is clean
        x = (float) conf.getDouble("x");
        y = (float) conf.getDouble("y");
    }

    @Override
    public void start(AbstractRoom room) {
        super.start(room);
        room.getGameObjects().add(MyGameObjectFromConf.objectFromConf(gameObjectNameToSpawn, x, y));
    }

    @Override
    public boolean isDone(AbstractRoom room) { return true; }
}
