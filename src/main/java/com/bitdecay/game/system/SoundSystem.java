package com.bitdecay.game.system;

import com.badlogic.gdx.audio.Music;
import com.bitdecay.game.Launcher;
import com.bitdecay.game.event.EventReactor;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractUpdatableSystem;
import com.bitdecay.game.trait.IEvent;
import com.bitdecay.game.trait.IEventListener;
import com.bitdecay.game.util.SoundLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SoundSystem extends AbstractUpdatableSystem implements IEventListener, Music.OnCompletionListener {
    private List<SoundEventer> soundEventers = new ArrayList<>();
    private Random rnd = new Random();
    private List<SoundEventer> musicEvents = new ArrayList<>();

    public SoundSystem(AbstractRoom room) {
        super(room);
        EventReactor.listenForEvents(this);
        soundEventers = Launcher.conf.getConfigList("sounds.events").stream().map(conf -> new SoundEventer(conf.getString("name"), (conf.hasPath("fx") ? conf.getString("fx") : null), (conf.hasPath("music") ? conf.getString("music") : null))).collect(Collectors.toList());
        musicEvents = soundEventers.stream().filter(s -> s.name.startsWith("GameMusic")).collect(Collectors.toList());
        //log.info("Built sound eventers: " + soundEventers);
        onCompletion(null); // start up a random music
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return false;//gob.hasComponent(SoundComponent.class);
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void handleEvent(IEvent event) {
        soundEventers.forEach(s -> {
            if (s.name.equalsIgnoreCase(event.name())) {
                s.play(null);
            }
        });
    }

    @Override
    public void onCompletion(Music music) {
        int next = rnd.nextInt(musicEvents.size());
        if (music != null) music.stop();
        musicEvents.get(next).play(this);
    }

    private class SoundEventer {
        public String name = null;
        public String fx = null;
        public String music = null;
        public SoundEventer(String name, String fx, String music){
            this.name = name;
            if (fx != null && SoundLibrary.hasSound(fx)) this.fx = fx;
            if (music != null && SoundLibrary.hasMusic(music)) this.music = music;
        }
        public void play(Music.OnCompletionListener onCompletion){
            //log.info("Play sound: " + fx + " or " + music);
            if (fx != null) SoundLibrary.playSound(fx);
            if (music != null) SoundLibrary.playMusic(music, onCompletion);
        }

        @Override
        public String toString() {
            return name + ":" + (fx != null ? fx : music);
        }
    }
}
