/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainScreens;

import circleland.CircleClass;
import circleland.CircleLoader;
import circleland.CircleMap;
import circleland.CircleMouse;
import circleland.CircleSummoned;
import circleland.Classes.WarriorCircle;
import circleland.Display.Camera;
import static circleland.DrawingPanel.PANEL_HEIGHT;
import static circleland.DrawingPanel.PANEL_WIDTH;
import circleland.LookupAtan2;
import circleland.MapLoader;
import circleland.MapManager;
import circleland.SoundManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Jeff
 */
public class CircleLandState extends GameState{

    private CircleMap currentMap;
    private Camera camera;
    private CircleMouse mouse;
    private CircleClass player;
    
    public CircleLandState(GameStateManager gsm){
        super(gsm);
        //initializeSounds
        SoundManager.initializeSoundManager();
        MapLoader mL = new MapLoader();
        //mL.loadMap("images/Act1MapData.xml");
        
        
        player = new WarriorCircle();
        //player = new NecromancerCircle();
        camera = new Camera(0,0,PANEL_WIDTH,PANEL_HEIGHT, player);
        mouse = new CircleMouse(camera,player);
        camera.mouse(mouse);
        
        currentMap = mL.enterMap("images/Act1.xml", camera, mouse, player);
        currentMap.name("Act1");
    }
    public CircleLandState(GameStateManager gsm, CircleClass circle){
        super(gsm);
        //initializeSounds
        SoundManager.initializeSoundManager();
        MapLoader mL = new MapLoader();
        //mL.loadMap("images/Act1MapData.xml");
        
        
        player = circle;
        camera = new Camera(0,0,PANEL_WIDTH,PANEL_HEIGHT, player);
        mouse = new CircleMouse(camera,player);
        camera.mouse(mouse);
        
        currentMap = mL.enterMap("images/Act1.xml", camera, mouse, player);
        currentMap.name("Act1");
    }
    @Override
    public void handleInput(InputStore input) {
        if(input.iswPressed()){player.moveUp(true);}
            else {player.moveUp(false);}
        if(input.isaPressed()){player.moveLeft(true);}
            else {player.moveLeft(false);}
        if(input.issPressed()){player.moveDown(true);}
            else {player.moveDown(false);}
        if(input.isdPressed()){player.moveRight(true);}
            else {player.moveRight(false);}
        if(input.isLeftMouseDown()){player.doAttack(true);}
            else {player.doAttack(false);}
        if(input.isRightMouseDown()){player.doSkill(true);}
            else {player.doSkill(false);}
        if(input.isSpacePressed() == false && input.isLastSpacePressed() == true)
        {
            player.pickUp(true);
        }
        if(input.iscPressed() == false && input.isLastCPressed() == true)
        {
            camera.showCharacterStats(!camera.showCharacterStats());
        }
        if(input.isiPressed() == false && input.isLastIPressed() == true)
        {
            camera.showInventory(!camera.showInventory());
        }
        if(input.iskPressed() == false && input.isLastKPressed() == true)
        {
            camera.showSkills(!camera.showSkills());
        }
        if(input.ispPressed() == false && input.isLastPPressed() == true)
        {
            int i = 0;
            for(CircleSummoned cS : currentMap.player().summoned())
            {
                System.out.println("Sum " + i + ": " + cS.name());
                i++;
            }
        }
        if(input.isLeftMouseDown() == false && input.isLastLeftMouseDown() == true)
        {
            mouse.mouseLeftClicked(true);
        } else {
            mouse.mouseLeftClicked(false);
        }
        if(input.isRightMouseDown() == false && input.isLastRightMouseDown() == true)
        {
            mouse.mouseRightClicked(true);
        } else {
            mouse.mouseRightClicked(false);
        }
        if(input.getMouseWheelScrolled() != 0){
            System.out.println("SCROLLING");
            //scroll through player skills
            if(input.getMouseWheelScrolled() > 0)
            {
                player.skills().add(player.equippedSkill());
                player.equippedSkill(player.skills().remove(0));
            }
            if(input.getMouseWheelScrolled() < 0)
            {
                player.skills().add(0,player.equippedSkill());
                player.equippedSkill(player.skills().remove(player.skills().size()-1));
            }
            input.setMouseWheelScrolled(0);
        }
        player.aim().x = camera.rect().x + input.getMousePosition().x;
        player.aim().y = camera.rect().y + input.getMousePosition().y;
        mouse.position().x = input.getMousePosition().x;
        mouse.position().y = input.getMousePosition().y;
        
        input.endFrame();
    }

    @Override
    public void update(long delta) {
        
        currentMap.update(delta);
        if(currentMap.switchToMap() != null)
        {
            switchMap();
        }
    }
    public void switchMap(){
        System.out.println("SWITCH MAP");
        //save current map
        MapManager.saveMap(currentMap);
        //add player to new map

        //set new map as current map;
        currentMap = MapManager.switchMap(currentMap);
        player.pickUp(false);
    }

    @Override
    public void draw(Graphics2D graphics) {
        
        currentMap.draw(graphics);
    }

    @Override
    public void close() {
        CircleLoader.saveCharacter(player);
    }
}
