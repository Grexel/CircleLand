/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Attacks;

import circleland.CircleAttack;
import circleland.CircleEntity;
import circleland.CircleLandUtility;
import circleland.CircleMap;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Jeff
 */
public class BounceAttack extends CircleAttack{
    private static final Color OUTLINE_COLOR = Color.WHITE;
    private int bounceRadius;
    private double bounceSpeed;
    private Color bulletColor;
    private CircleEntity nextBounce;
    public BounceAttack(CircleEntity owner, int life,int dmg, int pierce, double x, double y,
            double velX, double velY, double bSpeed,int bounceR,int bSize, Color bColor){
        super(owner,life,dmg,pierce,x,y,velX,velY,bSize,bColor);
        hitSound = "sounds/Hit6.wav";
        bounceRadius = bounceR;
        bounceSpeed = bSpeed;
    }
    
    public void update(long deltaTime, CircleMap world){
        //move attack;
        life -= deltaTime;
        if(nextBounce == null)
        {
            position.x += velocity.x * deltaTime/1000;
            position.y += velocity.y * deltaTime/1000;
        } else {
            double deltaY = nextBounce.position().y - position.y;
            double deltaX = nextBounce.position().x - position.x;
            double heading = Math.atan2(deltaY, deltaX); 
            velocity.x = bounceSpeed * Math.cos(heading);
            velocity.y = bounceSpeed * Math.sin(heading);
            position.x += velocity.x * deltaTime/1000;
            position.y += velocity.y * deltaTime/1000;
        }
        //check for collision with player or monster
        if(team == CircleLandUtility.MONSTER_SIDE) {// monsters attacking players
            boolean hitCircle = false;
            for(CircleEntity player : world.players()) {
                if(intersectsCircle(player)) {
                    if(!hitAlready.contains(player)) {
                        player.hitByAttack(this,world);
                        hitAlready.add(player);
                        piercingLeft -= 1;
                        hitCircle = true;
                        if(nextBounce != null)
                            nextBounce = null;
                        break;
                    }
                }
            }
            if(hitCircle){ // hit a player, target next player
                for(CircleEntity player : world.players()) {
                    //player is within bounce radius
                    if(distanceBetweenPoints(player.position(),position) <= bounceRadius)
                    {
                        if(!hitAlready.contains(player))
                            nextBounce = player;
                    }
                }
            }
        }
        
        if(team == CircleLandUtility.PLAYER_SIDE) {// players attacking monsters
            boolean hitCircle = false;
            for(CircleEntity monster : world.monsters()) {
                if(intersectsCircle(monster)) {
                    if(!hitAlready.contains(monster)) {
                        monster.hitByAttack(this,world);
                        hitAlready.add(monster);
                        piercingLeft -= 1;
                        hitCircle = true;
                        if(nextBounce != null)
                            nextBounce = null;
                        break;
                    }
                }
            }
            if(hitCircle){ // hit a player, target next player
                for(CircleEntity monster : world.monsters()) {
                    //player is within bounce radius
                    if(distanceBetweenPoints(monster.position(),position) <= bounceRadius)
                    {
                        if(!hitAlready.contains(monster))
                            nextBounce = monster;
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
