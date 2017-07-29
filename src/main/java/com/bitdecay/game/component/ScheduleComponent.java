package com.bitdecay.game.component;

import com.bitdecay.game.task.AbstractTask;
import com.bitdecay.game.task.TaskFactory;
import com.typesafe.config.Config;

import java.util.List;
import java.util.stream.Collectors;

public class ScheduleComponent extends AbstractComponent {
    public List<AbstractTask> taskList;

    public ScheduleComponent(List<AbstractTask> taskList){ this.taskList = taskList; }

    public ScheduleComponent(Config conf) {
        taskList = conf.getConfigList("taskList").stream().map(TaskFactory::buildTaskFromConf).collect(Collectors.toList());
    }
}
