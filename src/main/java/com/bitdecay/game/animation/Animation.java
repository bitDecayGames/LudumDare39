package com.bitdecay.game.animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bytebreakstudios.animagic.animation.AnimationListener;
import com.bytebreakstudios.animagic.animation.FrameRate;
import com.bytebreakstudios.animagic.animation.IFrameByFrameAnimation;
import com.bytebreakstudios.animagic.utils.AnimagicException;

import java.util.ArrayList;
import java.util.List;

public class Animation implements IFrameByFrameAnimation {
    private String name;
    private List<AnimationListener> listeners = new ArrayList<>();
    private TextureRegion[] textures;
    private int[] keyframes;
    private float totalFrameRateDuration;
    private float perFrameRateDuration;
    private float currentDuration = 0;
    private com.bytebreakstudios.animagic.animation.Animation.AnimationPlayState playState = com.bytebreakstudios.animagic.animation.Animation.AnimationPlayState.ONCE;
    private float playDirectionModifier = 1;
    private boolean finishedPlaying = false;
    private float percentagePerFrame;

    public Animation(String name, com.bytebreakstudios.animagic.animation.Animation.AnimationPlayState playState, FrameRate frameRate, TextureRegion[] textureArray, int[] keyframes) {
        if (name == null) throw new AnimagicException("Animation.name cannot be null");
        if (name.trim().equalsIgnoreCase("")) throw new AnimagicException("Animation.name cannot be ''");
        if (frameRate == null) throw new AnimagicException("Animation.frameRate cannot be null");
        if (textureArray == null) throw new AnimagicException("Animation.textures cannot be null");
        if (textureArray.length == 0) throw new AnimagicException("Animation.textures cannot be empty");
        if (keyframes == null) throw new AnimagicException("Animation.keyframes cannot be null");

        this.name = name;
        this.playState = playState;
        this.textures = textureArray;
        this.keyframes = keyframes;

        setFrameRate(frameRate);

        percentagePerFrame = 1f / textures.length;
    }

    public Animation(String name, com.bytebreakstudios.animagic.animation.Animation.AnimationPlayState playState, FrameRate frameRate, TextureRegion[] textureArray) {
        this(name, playState, frameRate, textureArray, new int[0]);
    }

    public String name(){
        return name;
    }

    public float totalDuration() {
        return totalFrameRateDuration;
    }

    public float perFrameDuration() {
        return perFrameRateDuration;
    }

    public Animation setFrameRate(FrameRate frameRate){
        if (frameRate.total()) {
            this.totalFrameRateDuration = frameRate.seconds();
            this.perFrameRateDuration = frameRate.seconds() / textures.length;
        } else {
            this.totalFrameRateDuration = frameRate.seconds() * textures.length;
            this.perFrameRateDuration = frameRate.seconds();
        }
        return this;
    }

    public int totalFrames(){
        return textures.length;
    }

    @Override
    public void reset(){
        currentDuration = 0;
        finishedPlaying = false;
    }

    public Animation setFrameIndex(int index) {
        if (index < 0) throw new AnimagicException("Cannot set the frame index to less than 0");
        if (index >= totalFrames()) throw new AnimagicException("Cannot set the frame index to more than the totalFrames(" + totalFrames() + ") - 1");
        currentDuration = (percentagePerFrame * index) * totalDuration();
        return this;
    }

    @Override
    public void update(float delta){
        currentDuration += delta * playDirectionModifier;
        if (currentDuration <= 0) {
            currentDuration += totalDuration();
        }
        if (currentDuration >= totalDuration() || currentDuration <= 0) {
            switch (playState){
                case ONCE:
                    currentDuration = totalDuration();
                    if (!finishedPlaying){
                        notify(com.bytebreakstudios.animagic.animation.Animation.AnimationListenerState.FINISHED);
                    }
                    finishedPlaying = true;
                    break;
                case REPEAT:
                    currentDuration = 0;
                    notify(com.bytebreakstudios.animagic.animation.Animation.AnimationListenerState.REPEATED);
                    break;
                case PINGPONG:
                    if (playDirectionModifier == 1){
                        currentDuration = totalDuration();
                        notify(com.bytebreakstudios.animagic.animation.Animation.AnimationListenerState.PINGED);
                    } else {
                        currentDuration = 0;
                        notify(com.bytebreakstudios.animagic.animation.Animation.AnimationListenerState.PONGED);
                    }
                    playDirectionModifier *= -1;
                    break;
            }
        }

        if (keyframes != null){
            int curFrame = getFrameIndex();
            boolean isKeyframe = false;
            for (int keyframe : keyframes){
                if (keyframe == curFrame){
                    isKeyframe = true;
                    break;
                }
            }
            if (isKeyframe) notify(com.bytebreakstudios.animagic.animation.Animation.AnimationListenerState.KEYFRAME);
        }
    }

    @Override
    public int getFrameIndex(){
        if (percentagePerFrame <= 0) return 0;
        float percComp = percentComplete();
        if (percComp == 1) return totalFrames() - 1;
        return (int)Math.floor(percComp / percentagePerFrame);
    }

    @Override
    public TextureRegion getFrame(){
        return textures[getFrameIndex()];
    }

    public TextureRegion[] getFrames(){ return textures; }

    public float percentComplete(){
        if (currentDuration <= 0) return 0;
        else if (currentDuration >= totalDuration()) return 1;
        else return currentDuration / totalDuration();
    }

    public Animation listen(AnimationListener listener) {
        if (listener == null){
            throw new AnimagicException("Animation listener cannot be null");
        }
        listeners.add(listener);
        return this;
    }

    public Animation stopListening(AnimationListener listener){
        if (listeners.contains(listener)) listeners.remove(listener);
        return this;
    }

    private void notify(com.bytebreakstudios.animagic.animation.Animation.AnimationListenerState listenerState){
        for (AnimationListener listener : listeners) listener.animationNotification(this, listenerState);
    }
}
