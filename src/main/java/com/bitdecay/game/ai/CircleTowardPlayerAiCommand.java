package com.bitdecay.game.ai;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.component.SpeedComponent;
import com.bitdecay.game.component.VelocityComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.util.VectorMath;

public class CircleTowardPlayerAiCommand implements IAiCommand {
    private int rotationDirection = 1; // clockwise

    public CircleTowardPlayerAiCommand(int rotationDirection){
        if (rotationDirection < 0) this.rotationDirection = -1;
    }

    @Override
    public void start(MyGameObject self, Vector2 playerPos) {
    }

    @Override
    public void update(float delta, MyGameObject self, Vector2 playerPos) {
        self.forEach(PositionComponent.class, pos -> self.forEach(VelocityComponent.class, vel -> self.forEach(SpeedComponent.class, speed -> {
            vel.setAndIntended(VectorMath.rotatePointByDegreesAroundZero(playerPos.cpy().sub(pos.x, pos.y).nor(), 70 * rotationDirection).scl(speed.speed));
        })));
    }
}
