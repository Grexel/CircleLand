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
 *
 * @author Jeff
 */

public class SwordAttack extends CircleAttack{
    private static final Color OUTLINE_COLOR = Color.GRAY;
    private static final int attackMoveSpeed = 500;
    private double startingLife;
    private double directionOffset;
    
    public SwordAttack(CircleEntity owner, int life, int dmg,int pierce, double x, double y,
            double velX, double velY, int bSize, Color bColor){
        super(owner,life,dmg,pierce,x,y,velX,velY,bSize,bColor);
        hitSound = "sounds/Hit3.wav";
        directionOffset =0;
        startingLife = attackMoveSpeed;
        this.life = attackMoveSpeed;
    }
    
    public void update(long deltaTime, CircleMap world){
        //move attack;
        life -= deltaTime;
        directionOffset = ((startingLife - life)/ startingLife) * 3* Math.PI/4 - 3*Math.PI/8;
        double offX = attackOwner.size()/2 * Math.cos(attackOwner.heading() + directionOffset);
        double offY = attackOwner.size()/2 * Math.sin(attackOwner.heading() + directionOffset);
        position.x = attackOwner.position().x + offX;
        position.y = attackOwner.position().y + offY;
        velocity.x = position.x + (size) * Math.cos(attackOwner.heading() + directionOffset);
        velocity.y = position.y + (size) * Math.sin(attackOwner.heading() + directionOffset);
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
    public boolean intersectsCircle(CircleEntity circle){
        double distance = Line2D.ptSegDist(position.x, position.y, 
                velocity.x, velocity.y, circle.position().x, circle.position().y);
        if(distance <= circle.size()/2)
            return true;
        return false;
    }
    public void draw(Graphics2D graphics){
        graphics.setColor(OUTLINE_COLOR);
        graphics.setStroke(new BasicStroke(6));
        graphics.drawLine((int)position.x, (int)position.y, (int)velocity.x, (int)velocity.y);
        graphics.setColor(bulletColor);
        graphics.setStroke(new BasicStroke(3));
        graphics.drawLine((int)position.x, (int)position.y, (int)velocity.x, (int)velocity.y);
    }
    
}
