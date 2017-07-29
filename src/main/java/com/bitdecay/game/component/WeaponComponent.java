package com.bitdecay.game.component;

import com.typesafe.config.Config;

public class WeaponComponent extends AbstractComponent {
    public String bullet;
    public int ammo;
    public boolean unlimitedAmmo = false;
    public float bulletsPerSecond = 2;
    public int maxBullets = 10;

    private WeaponComponent(String bullet, float bulletsPerSecond, int maxBullets, int ammo, Boolean unlimitedAmmo){
        this.bullet = bullet;
        this.bulletsPerSecond = bulletsPerSecond;
        this.maxBullets = maxBullets;
        this.ammo = ammo;
        this.unlimitedAmmo = unlimitedAmmo;
    }

    public WeaponComponent(Config conf) {
        bullet = conf.getString("bullet");
        bulletsPerSecond = (float) conf.getDouble("bulletsPerSecond");
        maxBullets = conf.getInt("maxBullets");
        ammo = conf.getInt("ammo");
        unlimitedAmmo = conf.getBoolean("unlimitedAmmo");
    }
}
