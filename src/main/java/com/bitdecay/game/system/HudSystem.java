package com.bitdecay.game.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.bitdecay.game.Launcher;
import com.bitdecay.game.component.*;
import com.bitdecay.game.event.EventReactor;
import com.bitdecay.game.event.MachineGunFireEvent;
import com.bitdecay.game.event.ReloadShotgunEvent;
import com.bitdecay.game.event.ShotgunFireEvent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractForEachUpdatableSystem;
import com.bitdecay.game.weapon.WeaponUtils;

import java.util.List;

public class HudSystem extends AbstractForEachUpdatableSystem {
    private Label lblAmmo;
    private Label lblGun;
    private Label lblHealth;
    private Label lblFloor;
    private Stage stage = new Stage();


    public HudSystem(AbstractRoom room) {
        super(room);

        Skin skin = new Skin(Gdx.files.classpath(Launcher.conf.getString("credits.skin")));
        lblGun = new Label("Gun", skin);
        lblGun.setFontScale(1.5f);
        lblGun.setFillParent(true);
        lblGun.setX(825);
        lblGun.setY(263);
        lblGun.setColor(Color.GOLDENROD);

        lblAmmo = new Label("Ammo", skin);
        lblAmmo.setFontScale(1.5f);
        lblAmmo.setFillParent(true);
        lblAmmo.setX(825);
        lblAmmo.setY(250);
        lblAmmo.setColor(Color.GOLDENROD);

        lblHealth = new Label("Health", skin);
        lblHealth.setFontScale(1.5f);
        lblHealth.setFillParent(true);
        lblHealth.setX(825);
        lblHealth.setY(230);
        lblHealth.setColor(Color.RED);

        lblFloor = new Label("Floor", skin);
        lblFloor.setFontScale(1.5f);
        lblFloor.setFillParent(true);
        lblFloor.setX(0);
        lblFloor.setY(263);
        lblFloor.setColor(Color.BLUE);


        stage.addActor(lblFloor);
        stage.addActor(lblHealth);
        stage.addActor(lblAmmo);
        stage.addActor(lblGun);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(HealthComponent.class, PlayerInputComponent.class, WeaponComponent.class) || gob.hasComponent(FloorComponent.class);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {

        if (gob.hasComponent(PlayerInputComponent.class)) {
            // gob is the Player
            HealthComponent health = gob.getComponent(HealthComponent.class).get();
            WeaponComponent weapon = WeaponUtils.getSelectedWeaponComponent(gob);

            String ammo;
            if (weapon.unlimitedAmmo) {
                ammo = "Unlimited";
            } else {
                ammo = Integer.toString(weapon.ammo);
            }

            lblAmmo.setText("Ammo: " + ammo);
            lblGun.setText("Gun: " + weapon.weaponName);
            lblHealth.setText("Heath: " + (int) health.hp + "/" + (int) health.maxHp);
        }
        else {
            // gob is the floor
            FloorComponent floor = gob.getComponent(FloorComponent.class).get();
            lblFloor.setText(floor.displayName);
        }

        stage.act();
        stage.draw();
    }
}
