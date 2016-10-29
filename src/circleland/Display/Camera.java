/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Display;

import circleland.CircleEntity;
import circleland.CircleMap;
import circleland.CircleMouse;
import circleland.CircleSkill;
import circleland.CircleWeapon;
import circleland.Display.SkillScreen;
import circleland.Display.InventoryScreen;
import circleland.Display.CharacterScreen;
import circleland.RectangleObject;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Jeff
 */
public class Camera extends RectangleObject{
    private boolean strictFollow;
    private Rectangle mapRect;
    private CircleEntity focus;
    public void focus(CircleEntity circle){
        inventory.player(circle);
        characterStats.player(circle);
        skillScreen.player(circle);
    }
    private CircleMouse mouse;
    public CircleMouse mouse(){return mouse;}
    public void mouse(CircleMouse c){mouse = c;}
    private InventoryScreen inventory;
    private CharacterScreen characterStats;
    private SkillScreen skillScreen;
    private MarketScreen marketScreen;
    public MarketScreen marketScreen(){return marketScreen;}
    public void marketScreen(MarketScreen ms){marketScreen = ms;}
    
    private boolean showCharacterStats;
    private boolean showInventory;
    private boolean showSkills;
    public boolean showCharacterStats(){return showCharacterStats;}
    public void showCharacterStats(boolean c){showCharacterStats = c;}
    public boolean showInventory(){return showInventory;}
    public void showInventory(boolean c){showInventory = c;if(c)showSkills = false;}
    public boolean showSkills(){return showSkills;}
    public void showSkills(boolean c){showSkills = c;if(c)showInventory = false;}
    
    public Camera(int x, int y, int width, int height,CircleEntity player)
    {
        super("Camera",x,y,width,height);
        focus = player;
        strictFollow = true;
        Rectangle2D.Double inventoryRect = new Rectangle2D.Double(rect().width - 200,rect().height - 300,200,300);
        inventory = new InventoryScreen(inventoryRect,focus);
        Rectangle2D.Double characterRect = new Rectangle2D.Double(0,rect().height - 300,200,300);
        characterStats = new CharacterScreen(characterRect,focus);
        Rectangle2D.Double skillRect = new Rectangle2D.Double(rect().width - 200,rect().height - 300,200,300);
        skillScreen = new SkillScreen(skillRect,focus);
    }
    public void update(long deltaTime, CircleMap world)
    {
        //keep camera center on focus entity
        if(focus != null && strictFollow)
        {
           rect.x = (int)(focus.position().x - rect.width/2);
           rect.y = (int)(focus.position().y - rect.height/2);
        }
        //keep camera within map bounds
        if(rect.x < mapRect.x)
            rect.x = mapRect.x;
        if(rect.y < mapRect.y)
            rect.y = mapRect.y;
        if(rect.x+rect.width > mapRect.x + mapRect.width)
            rect.x = mapRect.x + mapRect.width - rect.width;
        if(rect.y + rect.height > mapRect.y + mapRect.height)
            rect.y = mapRect.y + mapRect.height - rect.height;
        if(showInventory){
            if(inventory.rect().contains(mouse.position()))
            {
                inventory.hover(mouse);
            }
        }
        if(showSkills){
            if(skillScreen.rect().contains(mouse.position()))
            {
                skillScreen.hover(mouse);
            }
        }
        if(marketScreen != null){
            if(marketScreen.rect().contains(mouse.position()))
            {
                marketScreen.hover(mouse);
            }
        }
        
        if(mouse.mouseLeftClicked()) {
            if(marketScreen != null && marketScreen.rect().contains(mouse.position())){
                marketScreen.click(mouse);
                mouse.mouseLeftClicked(false);
            }
            else if(showInventory && inventory.rect().contains(mouse.position())){
                inventory.click(mouse);
                mouse.mouseLeftClicked(false);
                
            }
            else if(showSkills && skillScreen.rect().contains(mouse.position())){
                skillScreen.click(mouse);
                mouse.mouseLeftClicked(false);
            }
            else if(mouse.holding() != null)
            {
                if(mouse.holding() instanceof CircleWeapon){
                    double xPos= focus.position().x + Math.random()*3-3;
                    double yPos= focus.position().y + Math.random()*3-3;
                    mouse.holding().setPosition(xPos ,yPos);
                    world.itemsOnGround().add(mouse.holding());
                    mouse.holding(null);
                    mouse.mouseLeftClicked(false);
                }
                if(mouse.holding() instanceof CircleSkill){
                    focus.skills().add((CircleSkill)mouse.holding());
                    mouse.holding(null);
                    mouse.mouseLeftClicked(false);
                }
            }
            else{
                marketScreen = null;
            }
            
        }
    }
    
    public Rectangle mapRect(){return mapRect;}
    public void mapRect(Rectangle r){mapRect = r;}
    public void setFollowing(CircleEntity circle)
    {
        focus = circle;
        inventory.player(circle);
        characterStats.player(circle);
        skillScreen.player(circle);
    }
    public void draw(Graphics2D graphics){
        if(marketScreen != null)marketScreen.draw(graphics);
        if(showCharacterStats)characterStats.draw(graphics);
        if(showInventory)inventory.draw(graphics);
        if(showSkills)skillScreen.draw(graphics);
    }
}
