/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Jeff
 */
public abstract class RectangleItem extends RectangleObject{
    protected int rarity;
    public int rarity(){return rarity;}
    public void rarity(int s){rarity = s;}
    protected Color rarityColor;
    public Color rarityColor(){return rarityColor;}
    public void rarityColor(Color s){rarityColor = s;}
    protected Color color;
    
    public RectangleItem(){
        super("item",0,0,50,50);
        rarity = 1;
        rarityColor = Color.WHITE;
        color = Color.GREEN;
    }
    abstract public void draw(Graphics2D graphics);
    abstract public void draw(Graphics2D graphics, int x, int y);
    abstract public void drawDetails(Graphics2D graphics, int x, int y);
    public void setPosition(double x, double y) {
        rect.x = x;
        rect.y = y;
    }
    
}
