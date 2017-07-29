package com.bitdecay.game.task;

import com.bitdecay.game.component.DeadComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.gameobject.MyGameObjectFromConf;
import com.bitdecay.game.room.AbstractRoom;
import com.typesafe.config.Config;

public class SpawnAndDieTask extends AbstractTask {

    private String gameObjectNameToSpawn;
    private float x;
    private float y;

    MyGameObject obj;

    public SpawnAndDieTask(Config conf) {
        timeToWait = (float) conf.getDouble("time");
        gameObjectNameToSpawn = conf.getString("gob");
        x = (float) conf.getDouble("x");
        y = (float) conf.getDouble("y");
    }

    @Override
    public void start(AbstractRoom room) {
        super.start(room);
        obj = MyGameObjectFromConf.objectFromConf(gameObjectNameToSpawn, x, y);
        room.getGameObjects().add(obj);
    }

    @Override
    public boolean isDone(AbstractRoom room) { return obj.hasComponent(DeadComponent.class); }
}
