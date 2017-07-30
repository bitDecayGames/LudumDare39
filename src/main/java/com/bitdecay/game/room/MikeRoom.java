package com.bitdecay.game.room;


import com.bitdecay.game.gameobject.MyGameObjectFactory;
import com.bitdecay.game.gameobject.MyGameObjectFromConf;
import com.bitdecay.game.screen.GameScreen;
import com.bitdecay.game.system.*;
import com.bitdecay.game.system.physics.CollisionSystem;
import com.bitdecay.game.system.physics.FrictionSystem;
import com.bitdecay.game.system.physics.ManifoldResolutionSystem;
import com.bitdecay.game.system.physics.VelocitySystem;

/**
 * The demo room is just a super simple example of how to add systems and game objects to a room.
 */
public class MikeRoom extends AbstractRoom {

    public MikeRoom(GameScreen gameScreen) {
        super(gameScreen);

        // systems must be added before game objects
        new InitializationSystem(this);
        new TimerSystem(this);
        new CameraUpdateSystem(this);
        new RespawnSystem(this, Integer.MIN_VALUE, Integer.MAX_VALUE, -1000, Integer.MAX_VALUE);
        new DespawnSystem(this, Integer.MIN_VALUE, Integer.MAX_VALUE, -1000, Integer.MAX_VALUE);
        new DrawSystem(this);
        new ShapeDrawSystem(this);
        new RemovalSystem(this);
        new CollisionSystem(this);
        new TrapDoorSystem(this);
        new ManifoldResolutionSystem(this);
        new ScheduleSystem(this);
        new KeyboardMoveSystem(this);
        new KeyboardShootSystem(this);
        new KeyboardWeaponChangeSystem(this);
        new ShootSystem(this);
        new DamageSystem(this);
        new ExplodeSystem(this);
        new DeathSystem(this);
        new VelocitySystem(this);
        new FrictionSystem(this);
        new FloorLoadSystem(this);
        new ChangeFloorsSystem(this);
        new AimingAnimationSystem(this);
        new MovingAnimationSystem(this);
        new WeaponAnimationSystem(this);
        new SoundSystem(this);
        new AiSystem(this);
        new PopupSystem(this);
        new ComboSystem(this);

        gobs.add(MyGameObjectFromConf.objectFromConf("NorthMoveBoundary", 0, 0));
        gobs.add(MyGameObjectFromConf.objectFromConf("NorthBulletBoundary", 0, 0));
        gobs.add(MyGameObjectFromConf.objectFromConf("WestMoveBoundary", 0, 0));
        gobs.add(MyGameObjectFromConf.objectFromConf("WestBulletBoundary", 0, 0));
        gobs.add(MyGameObjectFromConf.objectFromConf("SouthMoveBoundary", 0, 0));
        gobs.add(MyGameObjectFromConf.objectFromConf("SouthBulletBoundary", 0, 0));
        gobs.add(MyGameObjectFromConf.objectFromConf("EastMoveBoundary", 0, 0));
        gobs.add(MyGameObjectFromConf.objectFromConf("EastBulletBoundary", 0, 0));
        gobs.add(MyGameObjectFactory.objectFromConf("floor10", 0, 0));
        gobs.add(MyGameObjectFactory.objectFromConf("Player", 0, 0));
        // this is required to be at the end here so that the systems have the latest gobs
        systemManager.cleanup();
    }
}
