package com.bitdecay.game.ai;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.component.SpeedComponent;
import com.bitdecay.game.component.VelocityComponent;
import com.bitdecay.game.gameobject.MyGameObject;

import java.util.Random;

public class CrawlOnWallAiCommand implements IAiCommand {
    private Random rnd = new Random();
    private int similarFrameTrigger = 15;
    private int similarPositionFrames = 0;
    private Vector2 lastPosition = new Vector2(0, 0);
    private float fuzzy = 0.5f;
    private int currentDirection = 0; // up
    private int rotationDirection = 1; // clockwise

    public CrawlOnWallAiCommand(int rotationDirection){
        if (rotationDirection < 0) this.rotationDirection = -1;
    }

    @Override
    public void start(MyGameObject self, Vector2 playerPos) {
        currentDirection = rnd.nextInt(4);
        self.forEach(VelocityComponent.class, vel -> self.forEach(SpeedComponent.class, speed -> updateVelocityWithDirection(vel, speed)));
    }

    @Override
    public void update(float delta, MyGameObject self, Vector2 playerPos) {
        self.forEach(PositionComponent.class, pos -> {
            if (lastPosition.x + fuzzy > pos.x && lastPosition.x - fuzzy < pos.x && lastPosition.y + fuzzy > pos.y && lastPosition.y - fuzzy < pos.y){
                similarPositionFrames++;
                if (similarPositionFrames > similarFrameTrigger){
                    currentDirection += rotationDirection;
                    if (currentDirection < 0) currentDirection = 3;
                    else if (currentDirection >= 4) currentDirection = 0;

                    self.forEach(VelocityComponent.class, vel -> self.forEach(SpeedComponent.class, speed -> updateVelocityWithDirection(vel, speed)));
                }
            } else similarPositionFrames = 0;
            lastPosition = pos.toVector2();
        });
    }

    private void updateVelocityWithDirection(VelocityComponent vel, SpeedComponent speed){
        float spd = Math.abs(speed.speed);
        switch(currentDirection){
            case 0:
                vel.set(0, spd); // up
                break;
            case 1:
                vel.set(spd, 0); // right
                break;
            case 2:
                vel.set(0, -spd); // down
                break;
            case 3:
                vel.set(-spd, 0); // left
                break;
        }
    }
}
