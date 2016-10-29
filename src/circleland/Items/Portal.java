/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Items;

import circleland.CircleEntity;
import circleland.CircleItem;
import circleland.CircleMap;
import static circleland.Weapons.RapierWeapon.CENTER_COLOR;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Jeff
 */
public class Portal extends CircleItem {
    private static final Color CENTER_COLOR = Color.WHITE;
    
    public Portal(String n, double x, double y){
        name = n;
        position.x = x;
        position.y = y;
        size = 100;
    }
    public void draw(Graphics2D graphics)
    {
        draw(graphics,(int)position.x,(int)position.y);
    }
    public void draw(Graphics2D graphics, int x, int y)
    {
        graphics.setColor(Color.BLACK);
        graphics.fillOval((int)(x - (size+4)/ 2), (int)(y - (size+4) / 2),
            (int)size+4, (int)size+4);
        graphics.setColor(CENTER_COLOR);
        graphics.fillOval((int)(x - size / 2), (int)(y - size / 2),
            (int)size, (int)size);
        graphics.setColor(Color.BLACK);
        graphics.drawString(name, (int)position.x, (int)position.y);
    }
    public void drawDetails(Graphics2D graphics, int x, int y){
        graphics.setColor(Color.WHITE);
        graphics.drawString(name, (int)position.x, (int)position.y);
    }
    
}
