package pl.killermenpl.game;

import com.badlogic.gdx.Game;

import pl.killermenpl.game.screens.Screens;

public class UnnamedRPG extends Game {
	@Override
	public void create() {
		setScreen(Screens.LOADING_SCREEN);
	}

}