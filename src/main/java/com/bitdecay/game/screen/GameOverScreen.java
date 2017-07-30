package com.bitdecay.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bitdecay.game.Launcher;
import com.bitdecay.game.MyGame;
import com.bitdecay.game.util.SoundLibrary;

import java.util.ArrayList;
import java.util.List;

public class GameOverScreen implements Screen {

    private List<Runnable> runnables = new ArrayList<>();
    private Stage stage;
    private MyGame game;

    public GameOverScreen(MyGame game){
        this.game = game;
        stage = new Stage();
    }

    @Override
    public void show() {
        SoundLibrary.stopMusic(Launcher.conf.getString("game.music"));
        SoundLibrary.loopMusic(Launcher.conf.getString("gameOver.music"));
    }

    @Override
    public void render(float delta) {
        MyGame.ASSET_MANAGER.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) nextScreen();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private void nextScreen() {
        game.setScreen(new MainMenuScreen(game));
    }
}
