/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import static circleland.Weapons.NovaWeapon.CENTER_COLOR;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Jeff
 */
public class RectangleContainer extends RectangleItem{

    protected boolean isOpened;
    public boolean isOpened(){return isOpened;}
    public void isOpened(boolean b){isOpened = b;}
    protected int level;
    public void level(int m){level = m;}
    public int level(){ return level;}
    public RectangleContainer(){
        super();
        name = "Rect Container";
        isOpened = false;
        level = 1;
    }
    public void openContainer(CircleMap world, CircleEntity opener){
        //world.lootGenerator().gener
        //world.spawningItems().addAll(world.lootGenerator().generateLoot(opener, this));
        isOpened = true;
    }
    public void draw(Graphics2D graphics)
    {
        draw(graphics,(int)rect.x,(int)rect.y);
    }
    public void draw(Graphics2D graphics, int x, int y)
    {
        graphics.setColor(rarityColor);
        graphics.fillRect((int)(x)+2, (int)(y)+2,
            (int)(rect.width)-4, (int)(rect.height)-4);
        graphics.setColor(Color.YELLOW);
        graphics.fillRect((int)(x), (int)(y),
            (int)(rect.width), (int)(rect.height));
        graphics.setColor(CENTER_COLOR);
        graphics.fillRect((int)(x), (int)(y),
            (int)(rect.width), (int)(8));
    }
    
    @Override
    public void drawDetails(Graphics2D graphics, int x, int y) {
        graphics.setColor(Color.WHITE);
        graphics.drawString(name, (int)x, (int)y);
    }
    
    
}
