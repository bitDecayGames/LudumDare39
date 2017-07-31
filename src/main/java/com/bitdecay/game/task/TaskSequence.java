package com.bitdecay.game.task;

import com.bitdecay.game.room.AbstractRoom;
import com.typesafe.config.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Monday on 7/29/2017.
 */
public class TaskSequence extends AbstractTask {
    private int currentTask = 0;
    public List<AbstractTask> taskList;

    public TaskSequence(Config conf) {
        timeToWait = conf.hasPath("time") ? (float) conf.getDouble("time") : 0;;
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
        if (taskList.size() > 0) {
            taskList.get(currentTask).update(delta, room);
        } else {
            return;
        }

        if (taskList.get(currentTask).isDone(room)) {
            currentTask++;
            if (isDone(room)) {
                return;
            } else {
                taskList.get(currentTask).start(room);
            }
        }
    }

    @Override
    public boolean isDone(AbstractRoom room) {
        return currentTask >= taskList.size();
    }
}
