package com.bitdecay.game.event;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.trait.IEvent;

public abstract class PopupEvent implements IEvent {
    public abstract String name();
    public abstract Vector2 position();
}
