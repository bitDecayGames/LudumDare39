package com.bitdecay.game.ai;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.gameobject.MyGameObject;

import java.util.Collections;

public class SimpleAiBehavior extends AbstractAiBehavior {
    public SimpleAiBehavior(IAiCommand command){
        this.commands = Collections.singletonList(command);
        validate();
        curCommand = command;
    }
    public void update(float delta, MyGameObject self, Vector2 playerPos){
        super.update(delta, self, playerPos);
    }
}
