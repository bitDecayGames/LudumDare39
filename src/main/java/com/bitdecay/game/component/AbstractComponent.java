package com.bitdecay.game.component;

import com.bitdecay.game.gameobject.MyGameObject;

/**
 * All components should extend this class
 */
public abstract class AbstractComponent {
    protected final MyGameObject obj;
    public AbstractComponent(MyGameObject obj){
        this.obj = obj;
    }

    @Override
    public String toString(){return this.getClass().getSimpleName();}
}