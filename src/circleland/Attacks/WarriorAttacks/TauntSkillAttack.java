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
import circleland.Effects.StunnedEffect;
import circleland.Effects.TauntEffect;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Jeff
 */
public class TauntSkillAttack extends CircleAttack{
    private static final Color OUTLINE_COLOR = Color.RED;
    private double startingLife, maxSize;
    public TauntSkillAttack(CircleEntity owner, int life, int dmg, double x, double y,
            double velX, double velY, int bSize, Color bColor){
        super(owner,life,dmg,1,x,y,velX,velY,bSize,bColor);
        maxSize = bSize;
        size = 0;
        startingLife = life;
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
            for(CircleEntity player : world.players())
            {
                if(intersectsCircle(player))
                {
                    if(!hitAlready.contains(player))
                    {
                        player.hitByAttack(this,world);
                        player.effects().add(new TauntEffect(2000,attackOwner));
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
                        monster.hitByAttack(this,world);
                        monster.effects().add(new TauntEffect(2000,attackOwner));
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
        graphics.drawOval((int)(position.x - size / 2), (int)(position.y - size / 2),
             (int)size,  (int)size);
    }
}
