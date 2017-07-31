package com.bitdecay.game.task;

import com.bitdecay.game.room.AbstractRoom;
import com.typesafe.config.Config;

import java.util.ArrayList;
import java.util.List;

public class ListTask extends AbstractTask {
    private int currentTask = 0;
    public List<AbstractTask> taskList;

    public ListTask(Config conf) {
        timeToWait = conf.hasPath("time") ? (float) conf.getDouble("time") : 0;
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
        taskList.forEach(task -> {
            task.timeToWait -= delta;
            if (task.timeToWait <= 0){
                if (!task.hasStarted) task.start(room);
                task.update(delta, room);
            }
        });
    }

    @Override
    public boolean isDone(AbstractRoom room) {
        return taskList.stream().filter(task -> !task.isDone(room)).count() == 0;
    }
}
