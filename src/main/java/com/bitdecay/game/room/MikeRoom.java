package com.bitdecay.game.room;


import com.bitdecay.game.gameobject.MyGameObjectFactory;
import com.bitdecay.game.gameobject.MyGameObjectFromConf;
import com.bitdecay.game.screen.GameScreen;
import com.bitdecay.game.system.*;
import com.bitdecay.game.system.physics.CollisionSystem;
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
        new ShapeDrawSystem(this);
        new DrawSystem(this);
        new RemovalSystem(this);
        new CollisionSystem(this);
        new ManifoldResolutionSystem(this);
        new ScheduleSystem(this);
        new KeyboardMoveSystem(this);
        new KeyboardShootSystem(this);
        new ShootSystem(this);
        new DamageSystem(this);
        new DeathSystem(this);
        new VelocitySystem(this);
        new FloorLoadSystem(this);
        new ChangeFloorsSystem(this);
        new SoundSystem(this);


        gobs.add(MyGameObjectFromConf.objectFromConf("NorthBoundary", 0, 0));
        gobs.add(MyGameObjectFromConf.objectFromConf("WestBoundary", 0, 0));
        gobs.add(MyGameObjectFromConf.objectFromConf("SouthBoundary", 0, 0));
        gobs.add(MyGameObjectFromConf.objectFromConf("EastBoundary", 0, 0));
        gobs.add(MyGameObjectFactory.objectFromConf("Player", 0, 0));
        gobs.add(MyGameObjectFactory.objectFromConf("floor10", 0, 0));
        // this is required to be at the end here so that the systems have the latest gobs
        systemManager.cleanup();
    }
}
