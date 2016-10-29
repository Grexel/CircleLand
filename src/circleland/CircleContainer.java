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
public class CircleContainer extends CircleItem{

    protected boolean isOpened;
    public boolean isOpened(){return isOpened;}
    public void isOpened(boolean b){isOpened = b;}
    protected int level;
    public void level(int m){level = m;}
    public int level(){ return level;}
    public CircleContainer(){
        super();
        name = "Container";
        isOpened = false;
        level = 1;
    }
    public void openContainer(CircleMap world, CircleEntity opener){
        //world.lootGenerator().gener
        world.spawningItems().addAll(world.lootGenerator().generateLoot(opener, this));
        isOpened = true;
    }
    public void draw(Graphics2D graphics)
    {
        draw(graphics,(int)position.x,(int)position.y);
    }
    public void draw(Graphics2D graphics, int x, int y)
    {
        graphics.setColor(rarityColor);
        graphics.fillOval((int)(x - (size+4)/ 2), (int)(y - (size+4) / 2),
            (int)size+4, (int)size+4);
        graphics.setColor(Color.YELLOW);
        graphics.fillOval((int)(x - size / 2), (int)(y - size / 2),
            (int)size, (int)size);
        graphics.setColor(CENTER_COLOR);
        graphics.fillOval((int)(x - 4), (int)(y - 4),
            (int)8, (int)8);
    }
    
    @Override
    public void drawDetails(Graphics2D graphics, int x, int y) {
        graphics.setColor(Color.WHITE);
        graphics.drawString(name, (int)position.x, (int)position.y);
    }
    
}
