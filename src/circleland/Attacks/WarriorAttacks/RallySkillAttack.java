/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Attacks.WarriorAttacks;

import circleland.CircleAttack;
import circleland.CircleEffect;
import circleland.CircleEntity;
import circleland.CircleLandUtility;
import circleland.CircleMap;
import circleland.Effects.RallyEffect;
import circleland.Effects.TauntEffect;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;

/**
 *
 * @author Jeff
 */
public class RallySkillAttack extends CircleAttack{
    private static final Color OUTLINE_COLOR = Color.RED;
    private double startingLife, maxSize;
    private int level,duration, bonusHealth,bonusAttack,bonusDefense;
    public RallySkillAttack(CircleEntity owner, int life, int dmg, double x, double y,
            double velX, double velY, int bSize, Color bColor,int level){
        super(owner,life,dmg,1,x,y,velX,velY,bSize,bColor);
        maxSize = bSize;
        size = 0;
        startingLife = life;
        duration = 120000 + level * 15000; // 2 minutes
        bonusHealth = 30 + level * 30;
        bonusAttack = 10 + level * 10;
        bonusDefense = 10 + level * 10;
    }
    
    public void update(long deltaTime, CircleMap world){
        //move attack;
        life -= deltaTime;
        position.x = attackOwner.position().x;
        position.y = attackOwner.position().y;
        size = maxSize * (1 - life/startingLife);
        //check for collision with player or monster
        if(team == CircleLandUtility.MONSTER_SIDE) // monsters attacking players
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
                            if(effect instanceof RallyEffect){
                                if(((RallyEffect) effect).level() <= level){
                                    iterator.remove();
                                }
                                else
                                    addEffect = false;
                            }
                        }
                        if(addEffect)
                            monster.effects().add(new RallyEffect(level,duration,bonusHealth,bonusAttack,bonusDefense));
                        hitAlready.add(monster);
                    }
                }
            }
        }
        if(team == CircleLandUtility.PLAYER_SIDE) // players attacking monsters
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
                            if(effect instanceof RallyEffect){
                                if(((RallyEffect) effect).level() <= level){
                                    iterator.remove();
                                }
                                else
                                    addEffect = false;
                            }
                        }
                        if(addEffect)
                            player.effects().add(new RallyEffect(level,duration,bonusHealth,bonusAttack,bonusDefense));
                        hitAlready.add(player);
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
        graphics.drawOval((int)(position.x - size / 2), (int)(position.y - size / 2),
             (int)size,  (int)size);
    }
}
