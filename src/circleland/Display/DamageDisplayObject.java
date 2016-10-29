/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Display;

import circleland.CircleMap;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Jeff
 */
public class DamageDisplayObject {
    private static final Font boldFont = new Font("Monospaced",Font.BOLD,20);
    private static final Font normalFont = new Font("Monospaced",Font.PLAIN,20);
    protected Point2D.Double position;
    public Point2D.Double position(){return position;}
    protected double damage;
    public double damage(){return damage;}
    public void damage(double d){damage = d;}
    protected double life;
    public double life(){return life;}
    public void life(double d){life = d;}
    public DamageDisplayObject(int x,int y, double dmg, double lf){
        position = new Point2D.Double(x,y);
        damage = dmg;
        life = lf;
    }
    public void update(long deltaTime,CircleMap world){
        life -= deltaTime;
        position.y -=  100.0 * deltaTime/1000; // move ten pixels per second
        //position.y -= .001;
    }
    public void draw(Graphics2D graphics)
    {
        draw(graphics,(int)position.x,(int)position.y);
    }
    public void draw(Graphics2D graphics,int x, int y)
    {
        graphics.setFont(boldFont);
        graphics.setColor(Color.BLACK);
        graphics.drawString("" + (int)damage, x, y);
        graphics.setFont(normalFont);
        graphics.setColor(Color.RED);
        graphics.drawString("" + (int)damage, x, y);
        
    }
    
}
