package com.bitdecay.game.event;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.trait.IEvent;

public class PlayerKillEvent implements IEvent {
    public Vector2 position;
    public PlayerKillEvent(Vector2 position){
        this.position = position;
    }
}
