package com.bitdecay.game.ai;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.gameobject.MyGameObject;

public interface IAiCommand {
    void update(float delta, MyGameObject self, Vector2 playerPos);
}
