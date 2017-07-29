package com.bitdecay.game.component;

import com.bitdecay.game.physics.Manifold;

/**
 * Created by Monday on 7/28/2017.
 */
public class ManifoldComponent extends AbstractComponent {
    public Manifold manifold;

    public ManifoldComponent(Manifold manifold) {
        this.manifold = manifold;
    }
}
