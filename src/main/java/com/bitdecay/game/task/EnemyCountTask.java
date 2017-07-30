package com.bitdecay.game.task;

import com.bitdecay.game.component.AiComponent;
import com.bitdecay.game.room.AbstractRoom;
import com.typesafe.config.Config;

import java.util.ArrayList;
import java.util.List;

public class EnemyCountTask extends AbstractTask {
    private int currentTask = 0;
    public List<AbstractTask> taskList;
    private int maxEnemies = 0;

    public EnemyCountTask(Config conf) {
        timeToWait = conf.hasPath("time") ? (float) conf.getDouble("time") : 0;
        maxEnemies = conf.getInt("maxEnemies");
        taskList = new ArrayList<>();

        List<? extends Config> tasks = conf.getConfigList("tasks");
        for (Config taskConf : tasks) {
            taskList.add(TaskFactory.buildTaskFromConf(taskConf));
        }
    }

    @Override
    public void start(AbstractRoom room) {
        super.start(room);
        if (taskList.size() > 0) {
            taskList.get(currentTask).start(room);
        }
    }

    @Override
    public void update(float delta, AbstractRoom room) {
        super.update(delta, room);
        if (room.getGameObjects().count(AiComponent.class) < maxEnemies){
            currentTask++;
            if (isDone(room)) {
                return;
            } else if (currentTask < taskList.size()){
                taskList.get(currentTask).start(room);
            }
        }

        if (taskList.size() > 0 && currentTask < taskList.size()) {
            taskList.get(currentTask).update(delta, room);
        } else {
            return;
        }
    }

    @Override
    public boolean isDone(AbstractRoom room) {
        return taskList.stream().filter(t -> {
            if (!t.isDone(room)) System.out.println("task is not done: " + t);
            return !t.isDone(room);
        }).count() == 0;
    }
}
