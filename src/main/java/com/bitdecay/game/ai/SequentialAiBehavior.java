package com.bitdecay.game.ai;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.gameobject.MyGameObject;

import java.util.List;
import java.util.stream.Collectors;

public class SequentialAiBehavior extends AbstractAiBehavior {
    private int currentIndex = -1;
    public SequentialAiBehavior(List<ICompletableAiCommand> commands){
        this.commands = commands.stream().map(IAiCommand.class::cast).collect(Collectors.toList());
        validate();
        commands.get(currentIndex);
    }
    public void update(float delta, MyGameObject self, Vector2 playerPos){
        if (curCommand == null || ((ICompletableAiCommand)curCommand).isComplete()){
            currentIndex++;
            if (currentIndex >= commands.size()) return;
            else {
                curCommand = commands.get(currentIndex);
                curCommand.start(self, playerPos);
            }
        }
        super.update(delta, self, playerPos);
    }
}
