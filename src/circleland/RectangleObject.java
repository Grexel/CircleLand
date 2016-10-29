/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Jeff
 */
public class RectangleObject {
    protected String name;
    public String name(){return name;}
    public void name(String s){name = s;}
    protected Rectangle2D.Double rect;
    public Rectangle2D.Double rect(){return rect;}
    public void rect(Rectangle2D.Double r){rect = r;}
    
    public RectangleObject(String nm, int xPos, int yPos, int width, int height)
    {
        name = nm;
        rect = new Rectangle2D.Double(xPos,yPos, width,height);
    }
    public RectangleObject(String nm,Rectangle2D.Double r)
    {
        name = nm;
        rect = r;
    }
    public boolean intersects(CircleObject cE)
    {
        double xHolder, yHolder,radiusHolder;
        xHolder = cE.position().x;
        yHolder = cE.position().y;
        radiusHolder = cE.size()/2;
        return (xHolder + radiusHolder > rect.x && xHolder - radiusHolder < rect.x + rect.width
                && yHolder + radiusHolder > rect.y && yHolder - radiusHolder < rect.y + rect.height);
    }
    
    public boolean intersects(RectangleObject cR)
    {
        
        return (cR.rect().x + cR.rect().width > rect.x && cR.rect().x < rect.x + rect.width
                && cR.rect().y + cR.rect().height > rect.y && cR.rect().y < rect.y + rect.height);
    }
    public boolean intersects(Rectangle r){
        if(rect.intersects(r.x, r.y, r.width, r.height)){
            return true;
        }
        return false;
    }
}
