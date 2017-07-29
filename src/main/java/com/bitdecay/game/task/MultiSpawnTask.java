package com.bitdecay.game.task;

import com.bitdecay.game.gameobject.MyGameObjectFromConf;
import com.bitdecay.game.room.AbstractRoom;
import com.typesafe.config.Config;

import java.util.Random;

public class MultiSpawnTask extends AbstractTask {

    private Random rnd = new Random();
    private String gameObjectNameToSpawn;
    private float x;
    private float y;
    private int count;

    public MultiSpawnTask(Config conf) {
        timeToWait = (float) conf.getDouble("time");
        gameObjectNameToSpawn = conf.getString("gob");
        x = (float) conf.getDouble("x");
        y = (float) conf.getDouble("y");
        count = conf.getInt("count");
    }

    @Override
    public void start(AbstractRoom room) {
        super.start(room);
        for (int i = 0; i < count; i++) room.getGameObjects().add(MyGameObjectFromConf.objectFromConf(gameObjectNameToSpawn, x + rnd.nextFloat() * 5, y + rnd.nextFloat() * 5));
    }

    @Override
    public boolean isDone(AbstractRoom room) { return true; }
}
