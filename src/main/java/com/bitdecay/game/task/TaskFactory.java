package com.bitdecay.game.task;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;

public class TaskFactory {
    private TaskFactory(){}

    public static AbstractTask buildTaskFromConf(Config conf){
        String name = conf.getString("name").replaceAll("Task", "");
        switch (name){
            case "SimpleSpawn":
                return new SimpleSpawnTask(conf);
            case "SpawnAndDie":
                return new SpawnAndDieTask(conf);
            case "MultiSpawn":
                return new MultiSpawnTask(conf);
            case "MultiSpawnNorth":
                return new MultiSpawnTask(conf.withValue("x", ConfigValueFactory.fromAnyRef(0)).withValue("y", ConfigValueFactory.fromAnyRef(190)));
            case "MultiSpawnEast":
                return new MultiSpawnTask(conf.withValue("x", ConfigValueFactory.fromAnyRef(190)).withValue("y", ConfigValueFactory.fromAnyRef(0)));
            case "MultiSpawnSouth":
                return new MultiSpawnTask(conf.withValue("x", ConfigValueFactory.fromAnyRef(0)).withValue("y", ConfigValueFactory.fromAnyRef(-190)));
            case "MultiSpawnWest":
                return new MultiSpawnTask(conf.withValue("x", ConfigValueFactory.fromAnyRef(-190)).withValue("y", ConfigValueFactory.fromAnyRef(0)));
            case "BoundarySetup":
                return new BoundarySetupTask(conf);
            case "Sequence":
                return new TaskSequence(conf);
            case "DownTheLine":
                return new DownTheLineSpawnTask(conf);
            case "EnemyCount":
                return new EnemyCountTask(conf);
            default:
                throw new RuntimeException("Could not find Task named: " + name);
        }
    }
}
