package com.bitdecay.game.component;

import com.bitdecay.game.gameobject.MyGameObject;

/**
 * Created by Monday on 7/30/2017.
 */
public class GobRefComponent extends AbstractComponent {
    public final MyGameObject gob;

    public GobRefComponent(MyGameObject gob) {
        this.gob = gob;
    }
}
