/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Attacks.NecromancerAttacks;

import circleland.CircleAttack;
import circleland.CircleEffect;
import circleland.CircleEntity;
import circleland.CircleLandUtility;
import circleland.CircleMap;
import circleland.Effects.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;

/**
 *
 * @author Jeff
 */

public class LeechLifeCurseSkillAttack extends CircleAttack{
    private static final Color OUTLINE_COLOR = Color.RED;
    private double growingLife,growingDuration, maxSize;
    private int level, curseLife;
    public LeechLifeCurseSkillAttack(CircleEntity owner, int lf, int dmg, double x, double y,
            double velX, double velY, int bSize, Color bColor,int lvl){
        super(owner,lf,dmg,1,x,y,velX,velY,bSize,bColor);
        maxSize = bSize;
        size = 0;
        growingLife = 500;
        life = 500;
        growingDuration = growingLife;
        level = lvl;
        curseLife = 5000 + level*1000;
    }
    
    public void update(long deltaTime, CircleMap world){
        //move attack;
        life -= deltaTime;
        growingLife -= deltaTime;
        if(growingLife < 0) growingLife = 0;
        size = maxSize * (1 - growingLife/growingDuration);
        //check for collision with player or monster
        if(team == CircleLandUtility.MONSTER_SIDE) // players attacking monsters
        {
            for(CircleEntity player : world.players())
            {
                if(intersectsCircle(player))
                {
                    if(!hitAlready.contains(player))
                    {
                        boolean addEffect = true;
                        for (Iterator<CircleEffect> iterator = player.effects().iterator(); iterator.hasNext();) {
                            CircleEffect effect = iterator.next();
                            if(effect instanceof LeechLifeEffect){
                                if( effect.life() < curseLife){
                                   effect.life(curseLife);
                                }
                                addEffect = false;
                            }
                        }
                        if(addEffect)
                            player.effects().add(new LeechLifeEffect(curseLife));
                        hitAlready.add(player);
                    }
                }
            }
        }
        if(team == CircleLandUtility.PLAYER_SIDE) // monsters attacking players
        {
            for(CircleEntity monster : world.monsters())
            {
                if(intersectsCircle(monster))
                {
                    if(!hitAlready.contains(monster))
                    {
                        boolean addEffect = true;
                        for (Iterator<CircleEffect> iterator = monster.effects().iterator(); iterator.hasNext();) {
                            CircleEffect effect = iterator.next();
                            if(effect instanceof LeechLifeEffect){
                                if( effect.life() < curseLife){
                                   effect.life(curseLife);
                                }
                                addEffect = false;
                            }
                        }
                        if(addEffect)
                            monster.effects().add(new LeechLifeEffect(curseLife));
                        hitAlready.add(monster);
                    }
                }
            }
        }
    }
    public void draw(Graphics2D graphics){
        graphics.setStroke(new BasicStroke(4));
        graphics.setColor(OUTLINE_COLOR);
        graphics.drawOval((int)(position.x - (size+4)/ 2), (int)(position.y - (size+4) / 2),
             (int)size+4,  (int)size+4);
        graphics.setColor(bulletColor);
        graphics.fillOval((int)(position.x - size / 2), (int)(position.y - size / 2),
             (int)size,  (int)size);
    }
}

