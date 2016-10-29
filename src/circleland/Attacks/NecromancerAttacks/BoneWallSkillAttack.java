/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Attacks.NecromancerAttacks;

import circleland.CircleAttack;
import circleland.CircleEntity;
import circleland.CircleLandUtility;
import circleland.CircleMap;
import circleland.CircleSummoned;
import circleland.Classes.Summons.SummonedBoneWall;
import circleland.Classes.Summons.SummonedGolem;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author Jeff
 */

public class BoneWallSkillAttack extends CircleAttack {
    private static final Color OUTLINE_COLOR = Color.WHITE;
    private int level;
    public BoneWallSkillAttack(CircleEntity owner, int life, int lvl, double x, double y,
            double velX, double velY, int bSize, Color bColor){
        super(owner,life,0,1,x,y,velX,velY,bSize,bColor);
        level = lvl;
    }
    
    public void update(long deltaTime, CircleMap world){
        //move attack;
        life -= deltaTime;
        position.x += velocity.x * deltaTime/1000;
        position.y += velocity.y * deltaTime/1000;
        //check for collision with player or monster
        if(team == CircleLandUtility.MONSTER_SIDE) // monsters attacking players
        {
            //spawn walls
            CircleSummoned cE = new SummonedBoneWall(level);
            cE.owner(attackOwner);
            cE.team(CircleLandUtility.MONSTER_SIDE);
            cE.position(new Point2D.Double(attackOwner.aim().x,attackOwner.aim().y));
            world.spawningMonsters().add(cE);
            life = 0;
        }
        if(team == CircleLandUtility.PLAYER_SIDE) // players attacking monsters
        {
            CircleSummoned cE = new SummonedBoneWall(level);
            cE.owner(attackOwner);
            cE.team(CircleLandUtility.PLAYER_SIDE);
            cE.position(new Point2D.Double(attackOwner.aim().x,attackOwner.aim().y));
            world.spawningPlayers().add(cE);
            life = 0;
        }
    }
    @Override
    public void draw(Graphics2D graphics){
        //dont draw
    }
}