/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 *
 * @author Jeff
 */
abstract public class CircleItem extends CircleObject{
    protected int rarity;
    public int rarity(){return rarity;}
    public void rarity(int s){
        rarity = s;
        if(rarity == 1) rarityColor = Color.WHITE;
        if(rarity == 2) rarityColor = Color.GREEN;
        if(rarity == 3) rarityColor = Color.BLUE;
        if(rarity == 4) rarityColor = Color.MAGENTA;}
    protected Color rarityColor;
    public Color rarityColor(){return rarityColor;}
    public void rarityColor(Color s){rarityColor = s;}
    protected Color itemColor;
    public Color itemColor(){return itemColor;}
    public void itemColor(Color s){itemColor = s;}
    protected Color specialColor;
    public Color specialColor(){return specialColor;}
    public void specialColor(Color s){specialColor = s;}
    protected Color color;
    
    
    public CircleItem(){
        name = "item";
        position = new Point2D.Double(0,0);
        size = 20;
        rarity = 1;
        rarityColor = Color.WHITE;
        itemColor = Color.YELLOW;
        specialColor = Color.BLACK;
        color = Color.GREEN;
    }
    public void update(long deltaTime, CircleMap world){
        
        for(CircleItem cI : world.itemsOnGround()){
            if(intersectsCircle(cI))
            collideWithCircleEntity(cI);
            
        }
    }
    abstract public void draw(Graphics2D graphics);
    abstract public void draw(Graphics2D graphics, int x, int y);
    abstract public void drawDetails(Graphics2D graphics, int x, int y);
    public void setPosition(double x, double y) {
        position.x = x;
        position.y = y;
    }
}
