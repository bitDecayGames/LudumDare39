package com.bitdecay.game.component;

import com.badlogic.gdx.math.Vector2;
import com.typesafe.config.Config;

public class ArtOffsetComponent extends AbstractComponent {
    public float x = 0;
    public float y = 0;

    public ArtOffsetComponent(Config conf) {
        this((float) conf.getDouble("x"), (float) conf.getDouble("y"));
    }

    public ArtOffsetComponent(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * Immutable
     * @return new Vector2
     */
    public Vector2 toVector2(){
        return new Vector2(x, y);
    }
}
