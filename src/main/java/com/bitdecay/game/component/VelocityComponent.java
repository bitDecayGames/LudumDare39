package com.bitdecay.game.component;

import com.badlogic.gdx.math.Vector2;
import com.typesafe.config.Config;

public class VelocityComponent extends AbstractComponent {
    public float x = 0;
    public float y = 0;

    public VelocityComponent(){ }

    public VelocityComponent(Config conf){
        x = (float) conf.getDouble("x");
        y = (float) conf.getDouble("y");
    }

    /**
     * Immutable
     * @return new Vector2
     */
    public Vector2 toVector2(){
        return new Vector2(x, y);
    }


}
