/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainScreens;

import circleland.CircleClass;
import circleland.CircleLoader;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

/**
 *
 * @author Jeff
 */
public class PlayerSelectState extends GameState{

    List<CircleClass> availablePlayers;
    CircleClass selectedPlayer;
    int selectedPlayerNumber;
    public PlayerSelectState(GameStateManager gsm){
        super(gsm);
        availablePlayers = CircleLoader.listCharacters();
        selectedPlayerNumber = 0;
        selectedPlayer = availablePlayers.get(selectedPlayerNumber);
        
        //load all players from player folder and put in availablePlayers
        //draw players with name and level, mouseclick on player puts them as selected player
        //add button for new player
        //new player chooses class and name.
        //add button for play game, changes state to new CircleLandState
    }
    
    @Override
    public void handleInput(InputStore input) {
        if(input.getMouseWheelScrolled() > 0){
            //scroll up
            if(selectedPlayerNumber == availablePlayers.size() - 1){
                selectedPlayerNumber = 0;
            }
            else{
                selectedPlayerNumber++;
            }
            selectedPlayer = availablePlayers.get(selectedPlayerNumber);
        }else if(input.getMouseWheelScrolled() < 0){
            
            if(selectedPlayerNumber == 0){
                selectedPlayerNumber = availablePlayers.size() - 1;
            }
            else{
                selectedPlayerNumber--;
            }
            selectedPlayer = availablePlayers.get(selectedPlayerNumber);
        }
        if(input.isSpacePressed()){
            if(selectedPlayer != null)
                this.getGameStateManager().pushState(
                        new CircleLandState(this.getGameStateManager(),selectedPlayer));
        }
        input.endFrame();
    }

    @Override
    public void update(long delta) {
       selectedPlayer.position().x = 100;
       selectedPlayer.position().y = 100;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.BLUE);
        graphics.fillRect(0, 0, 
                (int)graphics.getDeviceConfiguration().getBounds().getMaxX(),
                (int)graphics.getDeviceConfiguration().getBounds().getMaxY());
        selectedPlayer.draw(graphics);
        graphics.drawString(selectedPlayer.name() + " : " + selectedPlayer.className(),
                (int)selectedPlayer.position().getX()+50,(int)selectedPlayer.position().getY());
    }

    @Override
    public void close() {
       
    }
    
}
