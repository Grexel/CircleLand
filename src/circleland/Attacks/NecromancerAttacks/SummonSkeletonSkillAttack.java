/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Attacks.NecromancerAttacks;

import circleland.CircleAttack;
import circleland.CircleClass;
import circleland.CircleEntity;
import circleland.CircleItem;
import circleland.CircleLandUtility;
import circleland.CircleMap;
import circleland.CircleSummoned;
import circleland.Classes.Summons.SummonedSkeleton;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Jeff
 */
public class SummonSkeletonSkillAttack extends CircleAttack {
    private static final Color OUTLINE_COLOR = Color.WHITE;
    private int level;
    private ArrayList<CircleSummoned> summons;
    public SummonSkeletonSkillAttack(CircleEntity owner, int life, int lvl, double x, double y,
            double velX, double velY, int bSize, Color bColor,ArrayList<CircleSummoned> sums){
        super(owner,life,0,1,x,y,velX,velY,bSize,bColor);
        level = lvl;
        summons = sums;
    }
    
    public void update(long deltaTime, CircleMap world){
        //move attack;
        life -= deltaTime;
        position.x += velocity.x * deltaTime/1000;
        position.y += velocity.y * deltaTime/1000;
        //check for collision with player or monster
        if(team == CircleLandUtility.MONSTER_SIDE) // monsters attacking players
        {
        //check for collision with player or monster
            for (Iterator<CircleEntity> iterator = world.corpses().iterator(); iterator.hasNext();) {
                CircleEntity corpse = iterator.next();
                if(intersectsCircle(corpse) && !(corpse instanceof CircleSummoned))
                {
                    //remove corpse from world;
                    //add items to world
                    //destroy attack
                    CircleSummoned cE = new SummonedSkeleton(level);
                    cE.owner(attackOwner);
                    cE.team(CircleLandUtility.MONSTER_SIDE);
                    cE.position().x = corpse.position().x;
                    cE.position().y = corpse.position().y;
                    if(summons.size() == level){
                        //kill off first summons and add cE
                        CircleSummoned remEnt = summons.remove(0);
                        remEnt.removeSummon();
                        remEnt.health(0);
                        world.monsters().remove(remEnt);
                    }
                    summons.add(cE);
                    world.spawningMonsters().add(cE);
                    life = 0;
                    iterator.remove();
                }
            }
        }
        if(team == CircleLandUtility.PLAYER_SIDE) // players attacking monsters
        {
            //check for collision with player or monster
            for (Iterator<CircleEntity> iterator = world.corpses().iterator(); iterator.hasNext();) {
                CircleEntity corpse = iterator.next();
                if(intersectsCircle(corpse)&& !(corpse instanceof CircleSummoned))
                {
                    //remove corpse from world;
                    //add items to world
                    //destroy attack
                    CircleSummoned cE = new SummonedSkeleton(level);
                    cE.owner(attackOwner);
                    cE.team(CircleLandUtility.PLAYER_SIDE);
                    cE.position().x = corpse.position().x;
                    cE.position().y = corpse.position().y;
                    if(summons.size() == level){
                        //kill off first summons and add cE
                        CircleSummoned remEnt = summons.remove(0);
                        remEnt.removeSummon();
                        remEnt.health(0);
                        world.players().remove(remEnt);
                    }
                    summons.add(cE);
                    world.spawningPlayers().add(cE);
                    life = 0;
                    iterator.remove();
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

