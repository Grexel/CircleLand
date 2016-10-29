/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Items;

import circleland.CircleItem;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Jeff
 */
public class Gold extends CircleItem{
    private static final Color CENTER_COLOR = Color.BLACK;
    private int goldAmount;
    public int goldAmount(){return goldAmount;}
    public void goldAmount(int i){goldAmount = i;}
    
    public Gold(int gold, int x,int y){
        name = "Gold";
        goldAmount = gold;
        position.x = x;
        position.y = y;
        size = 15;
    }
    public void draw(Graphics2D graphics)
    {
        draw(graphics,(int)position.x,(int)position.y);
    }
    public void draw(Graphics2D graphics, int x, int y)
    {
        graphics.setColor(Color.YELLOW);
        graphics.fillOval((int)(x - (size)/4), (int)(y- size/2),
            (int)size/2+4, (int)size/2+4);
        graphics.fillOval((int)(x - (size)/ 2), (int)(y - size/4),
            (int)size/2+4, (int)size/2+4);
        graphics.fillOval((int)(x - size/1.5), (int)(y - (size) / 4),
            (int)size/2+4, (int)size/2+4);
    }
    public void drawDetails(Graphics2D graphics, int x, int y){
        graphics.setColor(Color.WHITE);
        graphics.drawString(name, (int)position.x, (int)position.y);
    }
    
}
