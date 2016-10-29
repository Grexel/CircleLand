/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author Jeff
 */
abstract public class CircleAttack extends CircleObject
{
    public static final int PHYSICAL = 0;
    public static final int FIRE = 1;
    protected Point2D.Double velocity;
    
    
    protected String hitSound;
    public String hitSound(){return hitSound;}
    public void hitSound(String l){hitSound = l;}
    protected int life;
    public int life(){return life;}
    public void life(int l){life = l;}
    protected int piercingLeft;
    public int piercingLeft(){return piercingLeft;}
    public void piercingLeft(int m){piercingLeft = m;}
    protected double damage;
    public double damage(){return damage;}
    public void damage(double d){damage = d;}
    protected int type;
    public int type(){return type;}
    public void type(int d){type = d;}
    protected int team;
    public int team(){return team;}
    protected Color bulletColor;
    public Color bulletColor(){return bulletColor;}
    public void bulletColor(Color d){bulletColor = d;}
    
    protected ArrayList<CircleEntity> hitAlready;
    public ArrayList<CircleEntity> hitAlready(){return hitAlready;}
    
    protected CircleEntity attackOwner;
    public CircleEntity attackOwner(){return attackOwner;}
    
    public CircleAttack(CircleEntity owner, int lf, double dmg, int pierce, double x, double y, double velX, double velY,double bSize,Color bColor)
    {
        type = PHYSICAL;
        attackOwner = owner;
        team = owner.team();
        hitAlready = new ArrayList<CircleEntity>();
        life = lf;
        piercingLeft = pierce;
        damage = dmg;
        size = bSize;
        position = new Point2D.Double(x,y);
        velocity = new Point2D.Double(velX,velY);
        bulletColor = bColor;
        hitSound = "sounds/Hit1.wav";
    }
    abstract public void update(long deltaTime, CircleMap world);
    abstract public void draw(Graphics2D graphics);
}
