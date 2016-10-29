/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Jeff
 */
public class ColorRectangle extends RectangleObject{
    private Color color;
    public ColorRectangle(Rectangle2D.Double r, Color c)
    {
        super("Color Rect",r);
        color = c;
    }
    public Color color(){return color;}
    public void color(Color c){color = c;}
    public void draw(Graphics2D graphics)
    {
        graphics.setColor(color);
        graphics.fillRect((int)rect.x,(int)rect.y,(int)rect.width,(int)rect.height);
    }
    
}
