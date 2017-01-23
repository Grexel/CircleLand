/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainScreens;

import java.awt.Graphics2D;

/**
 *
 * @author Jeff
 */
public abstract class GameState {
    private GameStateManager gameStateManager;
    public GameState(GameStateManager gsm){
        this.gameStateManager = gsm;
    }
    public abstract void handleInput(InputStore input);
    public abstract void update(long delta);
    public abstract void draw(Graphics2D graphics);
    public abstract void close();

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public void setGameStateManager(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }
    
}
