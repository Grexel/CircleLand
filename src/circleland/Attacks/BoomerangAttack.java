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
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Jeff
 */
public class BoomerangAttack extends CircleAttack{
    private static final Color OUTLINE_COLOR = Color.WHITE;
    private double returnSpeed;
    private double returnAcceleration = 50;
    public BoomerangAttack(CircleEntity owner, int life, int dmg,int pierce, double x, double y,
            double velX, double velY, int bSize, Color bColor){
        super(owner,life,dmg,pierce,x,y,velX,velY,bSize,bColor);
        returnSpeed = Math.abs(velocity.x) + Math.abs(velocity.y);
    }
    
    public void update(long deltaTime, CircleMap world){
        //move attack;
        life -= deltaTime;
        if(life > 1)
        {
            position.x += velocity.x * deltaTime/1000;
            position.y += velocity.y * deltaTime/1000;
        }
        else{ // life is <= 1
            life = 1;
            double deltaY = attackOwner.position().y - position.y;
            double deltaX = attackOwner.position().x - position.x;
            double heading = Math.atan2(deltaY, deltaX);
            returnSpeed += returnAcceleration * deltaTime/1000;
            velocity.x = returnSpeed * Math.cos(heading);
            velocity.y = returnSpeed * Math.sin(heading);
            position.x += velocity.x * deltaTime/1000;
            position.y += velocity.y * deltaTime/1000;
        }
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
                        piercingLeft -= 1;
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
                        piercingLeft -= 1;
                    }
                }
            }
        }
        if(intersectsCircle(attackOwner) && life == 1){
            life = 0;
        }
    }
    public void draw(Graphics2D graphics){
        graphics.setColor(OUTLINE_COLOR);
        graphics.fillOval((int)(position.x - (size+4)/ 2), (int)(position.y - (size+4) / 2),
            (int)size+4, (int)size+4);
        graphics.setColor(bulletColor);
        graphics.fillOval((int)(position.x - size / 2), (int)(position.y - size / 2),
             (int)size,  (int)size);
    }
    
}
