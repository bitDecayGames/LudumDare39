package com.bitdecay.game.event;

import com.bitdecay.game.trait.IEvent;
import com.bitdecay.game.trait.IEventListener;
import com.bitdecay.game.trait.IUpdate;

import java.util.ArrayList;
import java.util.List;

public class EventReactor implements IUpdate {
    private static EventReactor instance = null;
    private EventReactor(){}
    public static EventReactor getInstance(){
        if (instance == null) instance = new EventReactor();
        return instance;
    }

    private List<IEventListener> listeners = new ArrayList<>();
    private List<IEvent> eventsToFire = new ArrayList<>();

    public static EventReactor fireEvent(IEvent event){
        getInstance().eventsToFire.add(event);
        return getInstance();
    }

    public static EventReactor listenForEvents(IEventListener listener){
        getInstance().listeners.add(listener);
        return getInstance();
    }

    public static EventReactor staticUpdate(float delta){
        getInstance().update(delta);
        return getInstance();
    }

    public static EventReactor clearListeners(){
        getInstance().listeners.clear();
        getInstance().eventsToFire.clear();
        return getInstance();
    }

    @Override
    public void update(float delta) {
        eventsToFire.forEach(evt -> listeners.forEach(listener -> listener.handleEvent(evt)));
        eventsToFire.clear();
    }
}
