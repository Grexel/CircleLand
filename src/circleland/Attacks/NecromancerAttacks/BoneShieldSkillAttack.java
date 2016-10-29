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
import circleland.Effects.TauntEffect;
import circleland.TerrainGenerator;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Jeff
 */

public class BoneShieldSkillAttack extends CircleAttack{
    private static final Color OUTLINE_COLOR = Color.RED;
    private double damageReduction;
    private double direction;
    private ArrayList<CircleAttack> attackHitAlready;
    public BoneShieldSkillAttack(CircleEntity owner, int life, int dmg, double x, double y,
            double velX, double velY, int bSize, Color bColor,double percentReduction){
        super(owner,life,dmg,1,x,y,velX,velY,bSize,bColor);
        direction =0;
        attackHitAlready = new ArrayList<>();
        damageReduction = percentReduction;
    }
    
    public void update(long deltaTime, CircleMap world){
        //move attack;
        life -= deltaTime;
        direction += Math.PI * deltaTime/1000;
        position.x = attackOwner.position().x;
        position.y = attackOwner.position().y;
        
        //check for collision with player or monster
        if(team == CircleLandUtility.MONSTER_SIDE) // monsters attacking players
        {
            
            for(CircleAttack playerAttack : world.playerAttacks())
            {
                if(intersectsCircle(playerAttack))
                {
                    if(!attackHitAlready.contains(playerAttack))
                    {
                        playerAttack.damage(playerAttack.damage()*(1-damageReduction));
                        //particleEffects
                        ParticleEffectCreator.createParticleExplosion(world, 40,
                                (int)playerAttack.position().x, (int)playerAttack.position().y, 40, 500, 4, 8, TerrainGenerator.magicColorScheme());
                        attackHitAlready.add(playerAttack);
                    }
                }
            }
        }
        if(team == CircleLandUtility.PLAYER_SIDE) // players attacking monsters
        {
            for(CircleAttack monsterAttack : world.monsterAttacks())
            {
                if(intersectsCircle(monsterAttack))
                {
                    if(!attackHitAlready.contains(monsterAttack))
                    {
                        monsterAttack.damage(monsterAttack.damage()*(1-damageReduction));
                        //particleEffects
                        ParticleEffectCreator.createParticleExplosion(world, 40,
                                (int)monsterAttack.position().x, (int)monsterAttack.position().y, 40, 500, 4, 8, TerrainGenerator.magicColorScheme());
                        attackHitAlready.add(monsterAttack);
                    }
                }
            }
        }
    }
    @Override
    public void draw(Graphics2D graphics){
        int skullSize = 10;
        graphics.setStroke(new BasicStroke(4));
        graphics.setColor(OUTLINE_COLOR);
        graphics.setColor(bulletColor);
        graphics.drawOval((int)(position.x - size / 2), (int)(position.y - size / 2),
             (int)size,  (int)size);
        int x2 = (int)position.x + (int) Math.round((size/2)
                * Math.cos(direction)-skullSize/2);
        int y2 = (int)position.y + (int) Math.round((size/2)
                * Math.sin(direction)-skullSize/2);
        graphics.fillOval(x2, y2, skullSize, skullSize);
        x2 = (int)position.x + (int) Math.round((size/2)
                * Math.cos(direction+Math.PI/2)-skullSize/2);
        y2 = (int)position.y + (int) Math.round((size/2)
                * Math.sin(direction+Math.PI/2)-skullSize/2);
        graphics.fillOval(x2, y2, skullSize, skullSize);
        x2 = (int)position.x + (int) Math.round((size/2)
                * Math.cos(direction+Math.PI)-skullSize/2);
        y2 = (int)position.y + (int) Math.round((size/2)
                * Math.sin(direction+Math.PI)-skullSize/2);
        graphics.fillOval(x2, y2, skullSize, skullSize);
        x2 = (int)position.x + (int) Math.round((size/2)
                * Math.cos(direction-Math.PI/2)-skullSize/2);
        y2 = (int)position.y + (int) Math.round((size/2)
                * Math.sin(direction-Math.PI/2)-skullSize/2);
        graphics.fillOval(x2, y2, skullSize, skullSize);
        
    }
}
