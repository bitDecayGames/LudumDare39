package com.bitdecay.game.component;

import com.typesafe.config.Config;

public class DamagerComponent extends AbstractComponent {
    public float damage;

    public DamagerComponent(float damage){
        this.damage = damage;
    }

    public DamagerComponent(Config conf){
        damage = (float) conf.getDouble("damage");
    }
}