/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Attacks;

import circleland.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Jeff
 */
public class BombAttack extends CircleAttack {
    private static final Color OUTLINE_COLOR = Color.WHITE;
    private static final int RESET_DELAY = 250;
    private int blowUpDelay,originalDelay;
    private int resetHitTimer, resetHitDelay;
    private Point2D.Double originalVelocity;

    public BombAttack(CircleEntity owner, int life, int dmg, int pierce, double x, double y,
            double velX, double velY, int bUDelay,int bSize, Color bColor){
        super(owner,life,dmg,pierce,x,y,velX,velY,bSize,bColor);
        blowUpDelay = originalDelay = bUDelay;
        originalVelocity = new Point2D.Double(velocity.x,velocity.y);
        resetHitTimer = 0; resetHitDelay = RESET_DELAY;
    }
    
    public void update(long deltaTime, CircleMap world){
        //move attack;
        blowUpDelay -= deltaTime;
        //slow Bomb down to a stop
        if(blowUpDelay > 0)
        {
            velocity.x = originalVelocity.x * blowUpDelay/originalDelay;
            velocity.y = originalVelocity.y * blowUpDelay/originalDelay;
            position.x += velocity.x * deltaTime/1000;
            position.y += velocity.y * deltaTime/1000;
        }
        //check for collision with player or monster
        if(blowUpDelay <= 0)
        {
            resetHitTimer += deltaTime;
            if(resetHitTimer >= resetHitDelay){
                hitAlready.clear();
                resetHitTimer = 0;
            }
            life -= deltaTime;
            size += 200 * deltaTime/1000;
            if(team == CircleLandUtility.MONSTER_SIDE){ // monsters attacking players
                for(CircleEntity player : world.players()) {
                    if(intersectsCircle(player)) {
                        if(!hitAlready.contains(player)) {
                            player.hitByAttack(this,world);
                            hitAlready.add(player);
                        }
                    }
                }
            }
            if(team == CircleLandUtility.PLAYER_SIDE) { // players attacking monsters
                for(CircleEntity monster : world.monsters()) {
                    if(intersectsCircle(monster)) {
                        if(!hitAlready.contains(monster)) {
                            monster.hitByAttack(this,world);
                            hitAlready.add(monster);
                        }
                    }
                }
            }
        }
    }
    public void draw(Graphics2D graphics){
        graphics.setColor(OUTLINE_COLOR);
        graphics.fillOval((int)(position.x - (size+4)/ 2), (int)(position.y - (size+4) / 2),
            (int)size+4, (int)size+4);
        graphics.setColor(bulletColor);
        graphics.fillOval((int)(position.x - size / 2), (int)(position.y - size / 2),
            (int)size, (int)size);
    }
    
}
