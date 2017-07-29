package com.bitdecay.game.component;

import com.typesafe.config.Config;

public class DamageComponent extends AbstractComponent {
    public float damage;

    public DamageComponent(float damage){
        this.damage = damage;
    }

    public DamageComponent(Config conf){
        damage = (float) conf.getDouble("damage");
    }
}