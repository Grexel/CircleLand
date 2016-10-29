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
import circleland.Display.ColorSchematic;
import circleland.Display.ParticleEmitter;
import circleland.Effects.SlowEffect;
import circleland.Effects.StunnedEffect;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Jeff
 */
public class ChargeSkillAttack extends CircleAttack{
    private static final Color OUTLINE_COLOR = Color.GRAY;
    private ParticleEmitter particleEm;
    private Point2D.Double attackOffset;
    private double startingHealth;
    public ChargeSkillAttack(CircleEntity owner, int life, int dmg, double x, double y,
            double velX, double velY, int bSize, Color bColor){
        super(owner,life,dmg,1,x,y,velX,velY,bSize,bColor);
        
        startingHealth = owner.health();
        particleEm = new ParticleEmitter(position,5,6,12,200,300,10,ColorSchematic.STEEL_COLORSCHEME);
    }
    
    public void update(long deltaTime, CircleMap world){
        //move attack;
        particleEm.update(deltaTime, world);
        life -= deltaTime;
        attackOwner.health(startingHealth);
        attackOwner.position().x += velocity.x * deltaTime / 1000;
        attackOwner.position().y += velocity.y * deltaTime / 1000;
        position.x = attackOwner.position().x;
        position.y = attackOwner.position().y;
        //check for collision with player or monster
        if(team == CircleLandUtility.MONSTER_SIDE) // monsters attacking players
        {
            for(CircleEntity player : world.players())
            {
                if(intersectsCircle(player))
                {
                    if(!hitAlready.contains(player))
                    {
                        player.effects().add(new SlowEffect(2000, 0.5));
                        player.hitByAttack(this,world);
                        hitAlready.add(player);
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
                        monster.effects().add(new SlowEffect(2000, 0.5));
                        monster.hitByAttack(this,world);
                        hitAlready.add(monster);
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
