package com.bitdecay.game.ai;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.gameobject.MyGameObject;

import java.util.List;

public class RotatingAiBehavior extends AbstractAiBehavior {
    private float secondsBetweenCommands = 1;
    private float currentTimer = 0;
    private int currentIndex = 0;
    private boolean startedCurrent = false;
    public RotatingAiBehavior(float secondsBetweenCommands, List<IAiCommand> commands){
        this.secondsBetweenCommands = secondsBetweenCommands;
        currentTimer = this.secondsBetweenCommands;
        this.commands = commands;
        validate();
        curCommand = commands.get(currentIndex);
    }
    public void update(float delta, MyGameObject self, Vector2 playerPos){
        if (!startedCurrent && curCommand != null) {
            curCommand.start(self, playerPos);
            startedCurrent = true;
        }
        if (currentTimer < 0){
            currentTimer = secondsBetweenCommands;
            currentIndex++;
            if (currentIndex >= commands.size()) currentIndex = 0;
            curCommand = commands.get(currentIndex);
            startedCurrent = false;
        } else currentTimer -= delta;
        super.update(delta, self, playerPos);
    }
}
