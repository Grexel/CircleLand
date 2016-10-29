/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Items;

import circleland.CircleContainer;
import circleland.RectangleContainer;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Jeff
 */
public class Crate extends CircleContainer{
    public Crate(){
        super();
        level = 5;
        name = "Crate";
    }
    public Crate(int x, int y){
        super();
        level = 5;
        name = "Crate";
        position.x = x;
        position.y = y;
    }
    public void draw(Graphics2D graphics, int x, int y)
    {
        graphics.setColor(Color.BLACK);
        graphics.fillOval((int)(x - (size+4)/ 2), (int)(y - (size+4) / 2),
            (int)size+4, (int)size+4);
        graphics.setColor(Color.ORANGE);
        graphics.fillOval((int)(x - size / 2), (int)(y - size / 2),
            (int)size, (int)size);
    }
}
