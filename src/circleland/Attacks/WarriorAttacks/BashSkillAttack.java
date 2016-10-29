/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Attacks.WarriorAttacks;

import circleland.CircleAttack;
import circleland.CircleEntity;
import circleland.CircleLandUtility;
import circleland.CircleMap;
import circleland.Effects.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Jeff
 */
public class BashSkillAttack extends CircleAttack{
    private static final Color OUTLINE_COLOR = Color.GRAY;
    private Point2D.Double attackOffset;
    private double startingHealth;
    public BashSkillAttack(CircleEntity owner, int life, int dmg, double x, double y,
            double velX, double velY, int bSize, Color bColor){
        super(owner,life,dmg,1,x,y,velX,velY,bSize,bColor);
        
    }
    
    public void update(long deltaTime, CircleMap world){
        //move attack;
        life -= deltaTime;
        position.x += velocity.x *deltaTime/1000;
        position.y += velocity.y * deltaTime/1000;
        //check for collision with player or monster
        if(team == CircleLandUtility.MONSTER_SIDE) // monsters attacking players
        {
            for(CircleEntity player : world.players())
            {
                if(intersectsCircle(player))
                {
                    player.position().x += velocity.x * deltaTime / 1000;
                    player.position().y += velocity.y * deltaTime / 1000;
                    if(!hitAlready.contains(player))
                    {
                        player.effects().add(new FearEffect(3000));
                        player.hitByAttack(this,world);
                        hitAlready.add(player);
                        //player.hitByAttack(this,world);
                        //hitAlready.add(player);
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
                    monster.position().x += velocity.x * deltaTime / 1000;
                    monster.position().y += velocity.y * deltaTime / 1000;
                    if(!hitAlready.contains(monster))
                    {
                        monster.effects().add(new FearEffect(3000));
                        monster.hitByAttack(this,world);
                        hitAlready.add(monster);
                        //monster.hitByAttack(this,world);
                        //hitAlready.add(monster);
                    }
                }
            }
        }
    }
    public void draw(Graphics2D graphics){
        graphics.setColor(OUTLINE_COLOR);
        graphics.fillOval((int)(position.x - (size+4)/ 2), (int)(position.y - (size+4) / 2),
             (int)size+4,  (int)size+4);
        graphics.setColor(bulletColor);
        graphics.fillOval((int)(position.x - size / 2), (int)(position.y - size / 2),
             (int)size,  (int)size);
    }
    
    
}
