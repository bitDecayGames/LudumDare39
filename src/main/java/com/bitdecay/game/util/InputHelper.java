package com.bitdecay.game.util;

import com.badlogic.gdx.Gdx;

public final class InputHelper {
    private InputHelper(){}

    public static boolean isKeyJustPressed(int... keyboardKeys){
        for (int keyboardKey : keyboardKeys) if (Gdx.input.isKeyJustPressed(keyboardKey)) return true;
        return false;
    }

    public static boolean isKeyPressed(int... keyboardKeys){
        for (int keyboardKey : keyboardKeys) if (Gdx.input.isKeyPressed(keyboardKey)) return true;
        return false;
    }
}