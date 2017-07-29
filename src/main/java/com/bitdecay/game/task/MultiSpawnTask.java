package com.bitdecay.game.task;

import com.bitdecay.game.component.DeadComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.gameobject.MyGameObjectFromConf;
import com.bitdecay.game.room.AbstractRoom;
import com.typesafe.config.Config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MultiSpawnTask extends AbstractTask {

    private Random rnd = new Random();
    private String gameObjectNameToSpawn;
    private float x;
    private float y;
    private int count;

    // keep track of the objects we spawned
    List<MyGameObject> myObjects = new ArrayList<>();

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
        for (int i = 0; i < count; i++) {
            MyGameObject obj = MyGameObjectFromConf.objectFromConf(gameObjectNameToSpawn, x + rnd.nextFloat() * 5, y + rnd.nextFloat() * 5);
            myObjects.add(obj);
            room.getGameObjects().add(obj);
        }
    }

    @Override
    public void update(float delta, AbstractRoom room) {
        super.update(delta, room);
        Iterator<MyGameObject> iterator = myObjects.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().hasComponent(DeadComponent.class)) {
                iterator.remove();
            }
        }
    }

    @Override
    public boolean isDone(AbstractRoom room) { return myObjects.size() <= 0; }
}
