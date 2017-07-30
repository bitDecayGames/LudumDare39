package com.bitdecay.game.task;

import com.bitdecay.game.component.DeadComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.gameobject.MyGameObjectFromConf;
import com.bitdecay.game.room.AbstractRoom;
import com.typesafe.config.Config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DownTheLineSpawnTask extends AbstractTask {
    private String gameObjectNameToSpawn;
    private float secondsToComplete;
    private float secondsToCompleteCounter;
    private float x1;
    private float y1;
    private float x2;
    private float y2;
    private int count;
    private int currentSpawnNumber = 0;
    private float percentagePerSpawn;

    // keep track of the objects we spawned
    List<MyGameObject> myObjects = new ArrayList<>();

    public DownTheLineSpawnTask(Config conf) {
        timeToWait = (float) conf.getDouble("time");
        gameObjectNameToSpawn = conf.getString("gob");
        MyGameObjectFromConf.objectFromConf(gameObjectNameToSpawn, 0, 0); // run this at initialization to verify that the conf is clean
        secondsToComplete = (float) conf.getDouble("secondsToComplete");
        if (secondsToComplete <= 0) throw new RuntimeException("SecondsToComplete must be > 0");
        secondsToCompleteCounter = secondsToComplete;
        x1 = (float) conf.getDouble("x1");
        y1 = (float) conf.getDouble("y1");
        x2 = (float) conf.getDouble("x2");
        y2 = (float) conf.getDouble("y2");
        count = conf.getInt("count");
        if (count <= 0) throw new RuntimeException("Count must be > 0");
    }

    @Override
    public void start(AbstractRoom room) {
        super.start(room);
        percentagePerSpawn = 1f / (float) count;
        MyGameObject obj = MyGameObjectFromConf.objectFromConf(gameObjectNameToSpawn, x1, y1);
        myObjects.add(obj);
        room.getGameObjects().add(obj); // must spawn first game object or else nothing works
        currentSpawnNumber++;
    }

    @Override
    public void update(float delta, AbstractRoom room) {
        super.update(delta, room);
        if (secondsToCompleteCounter >= 0){
            secondsToCompleteCounter -= delta;
            float percentDone = 1 - (secondsToCompleteCounter / secondsToComplete);
            if (percentagePerSpawn * currentSpawnNumber < percentDone){
                MyGameObject obj = MyGameObjectFromConf.objectFromConf(gameObjectNameToSpawn, ((x2 - x1) * percentDone) + x1, ((y2 - y1) * percentDone) + y1);
                myObjects.add(obj);
                room.getGameObjects().add(obj);
                currentSpawnNumber++;
            }
        }

        // remove dead objects
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
