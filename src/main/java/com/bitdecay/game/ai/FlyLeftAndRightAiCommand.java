package com.bitdecay.game.ai;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.component.SpeedComponent;
import com.bitdecay.game.component.VelocityComponent;
import com.bitdecay.game.gameobject.MyGameObject;

public class FlyLeftAndRightAiCommand implements IAiCommand {
    @Override
    public void start(MyGameObject self, Vector2 playerPos) {}

    @Override
    public void update(float delta, MyGameObject self, Vector2 playerPos) {
        self.forEach(PositionComponent.class, pos -> self.forEach(VelocityComponent.class, vel -> self.forEach(SpeedComponent.class, speed -> {
            vel.set(vel.x + (0-pos.x)*speed.speed, 0);
        })));
    }
}
