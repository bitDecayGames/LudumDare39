package com.bitdecay.game.trait;

public interface IEvent {
    default String name() {
        return this.getClass().getSimpleName().replaceAll("Event", "");
    }
}
