package com.bitdecay.game.component;

import com.badlogic.gdx.math.Vector2;
import com.typesafe.config.Config;

public class SpeedComponent extends AbstractComponent {
    public float x = 0;
    public float y = 0;

    public SpeedComponent(float x, float y){
        this.x = x;
        this.y = y;
    }

    public SpeedComponent(Config conf){
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
