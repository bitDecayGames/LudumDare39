package com.bitdecay.game.component;

import com.badlogic.gdx.math.Vector2;
import com.typesafe.config.Config;

/**
 * Created by Monday on 7/30/2017.
 */
public class BulletOffsetComponent extends AbstractComponent {
    public Vector2 offset = new Vector2();

    public BulletOffsetComponent(Config conf) {
        offset.set((float)conf.getDouble("x"), (float)conf.getDouble("y"));
    }
}
