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
public class ParticleDisplayObject {
    protected Point2D.Double position;
    public Point2D.Double position(){return position;}
    protected Point2D.Double velocity;
    public Point2D.Double velocity(){return velocity;}
    protected double life;
    public double life(){return life;}
    public void life(double d){life = d;}
    protected int size;
    public int size(){return size;}
    public void size(int d){size = d;}
    protected Color color;
    public Color color(){return color;}
    public void color(Color d){color = d;}
    public ParticleDisplayObject(int x,int y, double velX, double velY,int lf,int s,Color c){
        position = new Point2D.Double(x,y);
        velocity = new Point2D.Double(velX,velY);
        size = s;
        life = lf;
        color = c;
    }
    public void update(long deltaTime,CircleMap world){
        life -= deltaTime;
        position.x -=  velocity.x * deltaTime/1000; 
        position.y -=  velocity.y * deltaTime/1000;
        //position.y -= .001;
    }
    public void draw(Graphics2D graphics)
    {
        draw(graphics,(int)position.x,(int)position.y);
    }
    public void draw(Graphics2D graphics,int x, int y)
    {
        graphics.setColor(color);
        graphics.fillOval(x, y, size, size);
    }
   
}
