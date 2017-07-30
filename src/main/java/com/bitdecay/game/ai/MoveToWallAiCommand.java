package com.bitdecay.game.ai;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.component.SpeedComponent;
import com.bitdecay.game.component.VelocityComponent;
import com.bitdecay.game.gameobject.MyGameObject;

import java.util.Random;

public class MoveToWallAiCommand implements ICompletableAiCommand {
    private Random rnd = new Random();
    private int similarFrameTrigger = 30;
    private int similarPositionFrames = 0;
    private Vector2 lastPosition = new Vector2(0, 0);
    private float fuzzy = 0.1f;

    @Override
    public boolean isComplete() {
        return similarPositionFrames > similarFrameTrigger;
    }

    @Override
    public void start(MyGameObject self, Vector2 playerPos) {
        self.forEach(VelocityComponent.class, vel -> self.forEach(SpeedComponent.class, speed -> {
            float spd = Math.abs(speed.speed);
            switch(rnd.nextInt(4)){
                case 0:
                    vel.setAndIntended(0, spd); // up
                    break;
                case 1:
                    vel.setAndIntended(0, -spd); // down
                    break;
                case 2:
                    vel.setAndIntended(spd, 0); // right
                    break;
                case 3:
                    vel.setAndIntended(-spd, 0); // left
                    break;
            }
        }));
    }

    @Override
    public void update(float delta, MyGameObject self, Vector2 playerPos) {
        self.forEach(PositionComponent.class, pos -> {
            if (lastPosition.x + fuzzy > pos.x && lastPosition.x - fuzzy < pos.x && lastPosition.y + fuzzy > pos.y && lastPosition.y - fuzzy < pos.y){
                similarPositionFrames++;
            } else similarPositionFrames = 0;
        });
    }
}
