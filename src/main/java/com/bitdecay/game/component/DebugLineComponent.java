package com.bitdecay.game.component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.trait.IShapeDraw;
import com.typesafe.config.Config;

/**
 * Created by Monday on 7/29/2017.
 */
public class DebugLineComponent extends AbstractComponent implements IShapeDraw {
    private Color color;
    private Vector2 point1;
    private Vector2 point2;

    private DebugLineComponent(Color color, Vector2 point1, Vector2 point2){
        this.color = color.cpy();
        this.point1 = point1;
        this.point2 = point2;
    }

    public DebugLineComponent(Config conf) {
        point1 = new Vector2((float) conf.getDouble("x1"),(float) conf.getDouble("y1"));
        point2 = new Vector2((float) conf.getDouble("x2"),(float) conf.getDouble("y2"));
        Config colorConf = conf.getConfig("color");
        this.color = new Color(
                (float) colorConf.getDouble("r"),
                (float) colorConf.getDouble("g"),
                (float) colorConf.getDouble("b"),
                (float) colorConf.getDouble("a")
        );
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer, Vector2 position) {
        shapeRenderer.line(point1.x, point1.y, point2.x, point2.y);
    }
}
