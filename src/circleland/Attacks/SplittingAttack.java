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
import java.util.ArrayList;

/**
 *
 * @author Jeff
 */
public class SplittingAttack extends CircleAttack{
    private static final Color OUTLINE_COLOR = Color.GRAY;
    private double splitAngle;
    private int maxLife;
    private int maxPiercing;
    public SplittingAttack(CircleEntity owner, int life, double dmg,int pierce, double x, double y,
            double velX, double velY, int bSize, Color bColor){
        super(owner,life,dmg,pierce,x,y,velX,velY,bSize,bColor);
        maxPiercing = pierce;
        maxLife = life;
        splitAngle = Math.PI/8;
    }
    
    public void update(long deltaTime, CircleMap world){
        //move attack;
        life -= deltaTime;
        position.x += velocity.x * deltaTime/1000;
        position.y += velocity.y * deltaTime/1000;
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
                        //split
                        split(world,world.spawningMonsterAttacks(),hitAlready);
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
                        split(world,world.spawningPlayerAttacks(),hitAlready);
                    }
                }
            }
        }
    }
    public void split(CircleMap world, ArrayList<CircleAttack> attacks,ArrayList<CircleEntity> alreadyHit){
            double splitSpeed = Math.abs(velocity.x) + Math.abs(velocity.y);
        
            double heading = Math.atan2(velocity.y, velocity.x); 
            double velX = splitSpeed * Math.cos(heading-splitAngle);
            double velY = splitSpeed * Math.sin(heading-splitAngle);
            SplittingAttack sA = new SplittingAttack(attackOwner,maxLife/2, damage/2, maxPiercing - 1, 
                    position.x, position.y, velX, velY, (int)(size/2), bulletColor);
            for(CircleEntity circle : alreadyHit)
            {
                sA.hitAlready().add(circle);
            }
            attacks.add(sA);
            velocity.x = splitSpeed * Math.cos(heading);
            velocity.y = splitSpeed * Math.sin(heading);
            velX = splitSpeed * Math.cos(heading+splitAngle);
            velY = splitSpeed * Math.sin(heading+splitAngle);
            SplittingAttack sB = new SplittingAttack(attackOwner,maxLife/2, damage/2, maxPiercing - 1, 
                    position.x, position.y, velX, velY, (int)(size/2), bulletColor);
            for(CircleEntity circle : alreadyHit)
            {
                sB.hitAlready().add(circle);
            }
            attacks.add(sB);
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
