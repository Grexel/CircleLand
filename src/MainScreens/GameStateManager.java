/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainScreens;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jeff
 */
public class GameStateManager {
    //boolean isRunning;
    List<GameState> gameStateStack;
    public GameStateManager(){
        gameStateStack = new ArrayList<GameState>();
    }
    public void handleInput(InputStore input){
        if(gameStateStack.size() > 0)
            gameStateStack.get(gameStateStack.size()-1).handleInput(input);
        
    }
    public void update(long delta){
        if(gameStateStack.size() > 0)
            gameStateStack.get(gameStateStack.size()-1).update(delta);
    }
    public void draw(Graphics2D graphics){
        if(gameStateStack.size() > 0)
            gameStateStack.get(gameStateStack.size()-1).draw(graphics);
    }
    public void changeState(GameState gS){
        if(gameStateStack.size() > 0){
            gameStateStack.remove(gameStateStack.size()-1);
        }
        gameStateStack.add(gS);
    }
    public void pushState(GameState gS){
        gameStateStack.add(gS);
    }
    public void popState(){
        if(gameStateStack.size() > 0)
            gameStateStack.remove(gameStateStack.size()-1);
    }
    public boolean isRunning(){
        return gameStateStack.size() > 0;
    }
    public void quit(){
        gameStateStack.clear();
    }
}
