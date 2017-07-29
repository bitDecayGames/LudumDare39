package com.bitdecay.game.task;

import com.bitdecay.game.room.AbstractRoom;

public abstract class AbstractTask {
    public float timeToWait = 0;
    protected boolean hasStarted = false;

    public void start(AbstractRoom room){ hasStarted = true; }
    public boolean hasStarted(){ return hasStarted; }
    public void update(float delta, AbstractRoom room) {}
    public abstract boolean isDone(AbstractRoom room);
}
