package com.bitdecay.game.task;

import com.typesafe.config.Config;

public class TaskFactory {
    private TaskFactory(){}

    public static AbstractTask buildTaskFromConf(Config conf){
        String name = conf.getString("name").replaceAll("Task", "");
        switch (name){
            case "SimpleSpawn":
                return new SimpleSpawnTask(conf);
            case "MultiSpawn":
                return new MultiSpawnTask(conf);
            case "BoundarySetup":
                return new BoundarySetupTask(conf);
            default:
                throw new RuntimeException("Could not find Task named: " + name);
        }
    }
}
