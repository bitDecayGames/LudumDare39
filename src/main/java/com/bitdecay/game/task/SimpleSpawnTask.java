package com.bitdecay.game.task;

import com.bitdecay.game.gameobject.MyGameObjectFromConf;
import com.bitdecay.game.room.AbstractRoom;
import com.typesafe.config.Config;

public class SimpleSpawnTask extends AbstractTask {

    private String gameObjectNameToSpawn;
    private float x;
    private float y;

    public SimpleSpawnTask(Config conf){
        timeToWait = (float) conf.getDouble("time");
        gameObjectNameToSpawn = conf.getString("gob");
        x = (float) conf.getDouble("x");
        y = (float) conf.getDouble("y");
    }

    @Override
    public void start(AbstractRoom room) {
        room.getGameObjects().add(MyGameObjectFromConf.objectFromConf(gameObjectNameToSpawn, x, y));
    }

    @Override
    public boolean isDone(AbstractRoom room) { return true; }
}
