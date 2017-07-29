package com.bitdecay.game.component;

import com.typesafe.config.Config;

public class DamagePerSecondComponent extends AbstractComponent {
    public float damage;

    public DamagePerSecondComponent(float damage){
        this.damage = damage;
    }

    public DamagePerSecondComponent(Config conf){
        damage = (float) conf.getDouble("damage");
    }
}