package com.bitdecay.game.ai;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.gameobject.MyGameObject;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAiBehavior {
    protected List<IAiCommand> commands = new ArrayList<>();
    protected IAiCommand curCommand = null;
    public void update(float delta, MyGameObject self, Vector2 playerPos){
        if (curCommand != null) curCommand.update(delta, self, playerPos);
    }
    protected void validate(){
        if (commands.size() == 0) throw new RuntimeException("Cannot create an ai behavior with no commands");
    }
}
