package com.bitdecay.game.system;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.component.ArtOffsetComponent;
import com.bitdecay.game.component.DrawableComponent;
import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.component.SizeComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractDrawableSystem;

/**
 * The draw system is one of the few systems that extends AbstractSystem directly.  The reason for this is that there is a call to spritebatch begin and end within the process method.  You will notice however that the forEach method is called in between the begin and end calls.  When you extend the AbstractForEachUpdatableSystem, that forEach call isn't necessary because it happens behind the scenes in the AbstractForEachUpdatableSystem.process method.
 */
public class DrawSystem extends AbstractDrawableSystem {
    public DrawSystem(AbstractRoom room) { super(room); }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(DrawableComponent.class, PositionComponent.class, SizeComponent.class);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, OrthographicCamera camera) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        gobs.forEach(gob ->
                gob.forEach(DrawableComponent.class, drawable ->
                        gob.forEach(PositionComponent.class, pos ->
                                gob.forEach(SizeComponent.class, size -> {
                                    Vector2 offset = gob.getComponent(ArtOffsetComponent.class).map(ArtOffsetComponent::toVector2).orElseGet(()->new Vector2(0, 0));
                                    spriteBatch.draw(drawable.image(), pos.x + offset.x, pos.y + offset.y, size.w, size.h);
                                }))));
        spriteBatch.end();
    }
}
