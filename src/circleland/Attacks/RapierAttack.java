/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Attacks;

import circleland.CircleAttack;
import circleland.CircleEntity;
import circleland.CircleLandUtility;
import circleland.CircleMap;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 *  use position and velocity as the start and end point of the attack
 * @author Jeff
 */
public class RapierAttack extends CircleAttack{
    private static final Color OUTLINE_COLOR = Color.BLACK;
    private double startingLife;
    private double direction;
    private Point2D.Double attackOffset;
    
    public RapierAttack(CircleEntity owner, int life, int dmg,int pierce, double x, double y,
            double velX, double velY, int bSize, Color bColor){
        super(owner,life,dmg,pierce,x,y,velX,velY,bSize,bColor);
        
        hitSound = "sounds/Hit4.wav";
        direction = attackOwner.heading();
        double offX = attackOwner.size()/2 * Math.cos(attackOwner.heading());
        double offY = attackOwner.size()/2 * Math.sin(attackOwner.heading());
        attackOffset = new Point2D.Double(offX,offY);
        startingLife = life;
        this.life = life;
    }
    
    public void update(long deltaTime, CircleMap world){
        //move attack;
        life -= deltaTime;
        double ratio = 1 - (life / startingLife);
        double offX = attackOwner.size()/2 * Math.cos(attackOwner.heading());
        double offY = attackOwner.size()/2 * Math.sin(attackOwner.heading());
        position.x = attackOwner.position().x + offX;
        position.y = attackOwner.position().y + offY;
        velocity.x = position.x + (size*ratio) * Math.cos(attackOwner.heading());
        velocity.y = position.y + (size*ratio) * Math.sin(attackOwner.heading());
        
        //check for collision with player or monster
        if(team == CircleLandUtility.MONSTER_SIDE) // monsters attacking players
        {
            for(CircleEntity player : world.players())
            {
                if(intersectsCircle(player))
                {
                    if(!hitAlready.contains(player))
                    {
                        player.hitByAttack(this,world);
                        hitAlready.add(player);
                    }
                }
            }
        }
        if(team == CircleLandUtility.PLAYER_SIDE) // players attacking monsters
        {
            for(CircleEntity monster : world.monsters())
            {
                if(intersectsCircle(monster))
                {
                    if(!hitAlready.contains(monster))
                    {
                        monster.hitByAttack(this,world);
                        hitAlready.add(monster);
                    }
                }
            }
        }
    }
    //only intersect at the tip... Just the tip.
    public boolean intersectsCircle(CircleEntity circle){
        double distance = Point2D.distance(velocity.x, velocity.y, circle.position().x, circle.position().y);
        if(distance <= circle.size()/2)
            return true;
        return false;
    }
    public void draw(Graphics2D graphics){
        graphics.setColor(OUTLINE_COLOR);
        graphics.setStroke(new BasicStroke(4));
        graphics.drawLine((int)position.x, (int)position.y, (int)velocity.x, (int)velocity.y);
        graphics.setColor(bulletColor);
        graphics.setStroke(new BasicStroke(2));
        graphics.drawLine((int)position.x, (int)position.y, (int)velocity.x, (int)velocity.y);
    }
    
}
