package com.bitdecay.game.component;

import com.typesafe.config.Config;

/**
 * Created by Monday on 7/29/2017.
 */
public class FloorComponent extends AbstractComponent {

    public final String displayName;
    public final String schedule;
    public final String bgImage;
    public final String next;
    public final String music;
    public boolean floorLoaded;


    public FloorComponent(Config conf) {
        this.displayName = conf.getString("displayName");
        this.bgImage = conf.getString("background");
        this.schedule = conf.getString("schedule");
        this.next = conf.getString("next");
        this.music = conf.getString("music");
        floorLoaded = false;
    }


}
