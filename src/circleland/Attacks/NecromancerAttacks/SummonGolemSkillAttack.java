/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Attacks.NecromancerAttacks;

import circleland.CircleAttack;
import circleland.CircleClass;
import circleland.CircleEntity;
import circleland.CircleLandUtility;
import circleland.CircleMap;
import circleland.CircleSummoned;
import circleland.Classes.Summons.SummonedGolem;
import circleland.Classes.Summons.SummonedSkeleton;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Jeff
 */

public class SummonGolemSkillAttack extends CircleAttack {
    private static final Color OUTLINE_COLOR = Color.WHITE;
    private int level;
    private ArrayList<CircleSummoned> golem;
    public SummonGolemSkillAttack(CircleEntity owner, int life, int lvl, double x, double y,
            double velX, double velY, int bSize, Color bColor,ArrayList<CircleSummoned> spawnedGolem){
        super(owner,life,0,0,x,y,velX,velY,bSize,bColor);
        level = lvl;
        golem = spawnedGolem;
    }
    
    public void update(long deltaTime, CircleMap world){
        //move attack;
        life -= deltaTime;
        position.x += velocity.x * deltaTime/1000;
        position.y += velocity.y * deltaTime/1000;
        //check for collision with player or monster
        if(team == CircleLandUtility.MONSTER_SIDE) // monsters attacking players
        {
            CircleSummoned cE = new SummonedGolem(level);
            cE.owner(attackOwner);
            cE.team(CircleLandUtility.MONSTER_SIDE);
            cE.position().x = attackOwner.aim().x;
            cE.position().y = attackOwner.aim().y;
            if(golem.size() == 1){
                //kill off first summons and add cE
                CircleSummoned cS  = golem.remove(0);
                cS.removeSummon();
                cS.health(0);
                world.monsters().remove(cS);
            }
            golem.add(cE);
            world.spawningMonsters().add(cE);
            life = 0;
        }
        if(team == CircleLandUtility.PLAYER_SIDE) // players attacking monsters
        {
            CircleSummoned cE = new SummonedGolem(level);
            cE.owner(attackOwner);
            cE.team(CircleLandUtility.PLAYER_SIDE);
            cE.position().x = attackOwner.aim().x;
            cE.position().y = attackOwner.aim().y;
            if(golem.size() == 1){
                //kill off first summons and add cE
                CircleSummoned cS  = golem.remove(0);
                cS.removeSummon();
                cS.health(0);
                world.players().remove(cS);
            }
            golem.add(cE);
            world.spawningPlayers().add(cE);
            life = 0;
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

