package com.bitdecay.game.ai;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.component.VelocityComponent;
import com.bitdecay.game.gameobject.MyGameObject;

public class WaitAiCommand implements IAiCommand {
    @Override
    public void update(float delta, MyGameObject self, Vector2 playerPos) {
        self.forEach(VelocityComponent.class, vel -> vel.set(0, 0));
    }
}
