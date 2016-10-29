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
import circleland.Display.ParticleEffectCreator;
import circleland.TerrainGenerator;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Jeff
 */
public class BoneBlastSkillAttack extends CircleAttack {
    private static final Color OUTLINE_COLOR = Color.WHITE;
    public BoneBlastSkillAttack(CircleEntity owner, int life, int dmg,int pierce, double x, double y,
            double velX, double velY, int bSize, Color bColor){
        super(owner,life,dmg,pierce,x,y,velX,velY,bSize,bColor);
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
                        //attackdirection = 
                        double heading = Math.atan2(-velocity.y, -velocity.x);
                        ParticleEffectCreator.createParticleBlowOut(world, 40,
                                (int)player.position().x, (int)player.position().y, 40,heading, 500, 4, 8, TerrainGenerator.magicColorScheme());
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
                        double heading = Math.atan2(-velocity.y, -velocity.x);
                        ParticleEffectCreator.createParticleBlowOut(world, 40,
                                (int)monster.position().x, (int)monster.position().y, 80,heading, 1000, 6, 12, TerrainGenerator.magicColorScheme());
                        hitAlready.add(monster);
                        piercingLeft -= 1;
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

