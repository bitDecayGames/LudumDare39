package com.bitdecay.game.ai;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.component.SpeedComponent;
import com.bitdecay.game.component.VelocityComponent;
import com.bitdecay.game.gameobject.MyGameObject;

public class ChargeAiCommand implements IAiCommand {
    private float speedBoost = 1;

    public ChargeAiCommand(float speedBoost){
        this.speedBoost = speedBoost;
    }

    @Override
    public void start(MyGameObject self, Vector2 playerPos) {
        System.out.println("Charge AI command start!");
        self.forEach(PositionComponent.class, pos ->
                self.forEach(VelocityComponent.class, vel ->
                        self.forEach(SpeedComponent.class, speed -> {
                            vel.set(playerPos.cpy().sub(pos.x, pos.y).nor().scl(speed.speed * speedBoost));
                        })));
    }

    @Override
    public void update(float delta, MyGameObject self, Vector2 playerPos) {}
}
