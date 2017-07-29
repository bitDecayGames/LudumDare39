package com.bitdecay.game.room;


import com.bitdecay.game.gameobject.MyGameObjectFactory;
import com.bitdecay.game.screen.GameScreen;
import com.bitdecay.game.system.*;
import com.bitdecay.game.system.physics.CollisionSystem;
import com.bitdecay.game.system.physics.ManifoldResolutionSystem;

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


        gobs.add(MyGameObjectFactory.objectFromConf("D1", 0, -20));

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                gobs.add(MyGameObjectFactory.objectFromConf("D2", i, j));
            }
        }

        gobs.add(MyGameObjectFactory.objectFromConf("leftBound", 0, 0));
        gobs.add(MyGameObjectFactory.objectFromConf("northBound", 0, 0));
        gobs.add(MyGameObjectFactory.objectFromConf("D3", 0, 0));

        // this is required to be at the end here so that the systems have the latest gobs
        systemManager.cleanup();
    }
}
