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
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Jeff
 */
public class BiteAttack extends CircleAttack{
    private static final Color OUTLINE_COLOR = Color.GRAY;
    private double startingLife;
    //need 4 lines for each teeth
    private Line2D.Double[] teeth;
    private double direction;
    
    public BiteAttack(CircleEntity owner, int life, int dmg,int pierce, double x, double y,
            double velX, double velY, int bSize, Color bColor){
        super(owner,life,dmg,pierce,x,y,velX,velY,bSize,bColor);
        
        startingLife = life;
        direction = owner.heading();
        teeth = new Line2D.Double[4];
        teeth[0] = new Line2D.Double();
        teeth[1] = new Line2D.Double();
        teeth[2] = new Line2D.Double();
        teeth[3] = new Line2D.Double();
    }
    
    public void update(long deltaTime, CircleMap world){
        //move attack;
        life -= deltaTime;
        position.x = attackOwner.position().x ;
        position.y = attackOwner.position().y ;
        direction = attackOwner.heading();
        double ratio = 1 - (life / startingLife);
        //grow Teeth, position
        double x, y = 0;
        x = position.x + (attackOwner.size()/2 * Math.cos(direction + 0.6));
        y = position.y + (attackOwner.size()/2 * Math.sin(direction + 0.6));
        teeth[0].setLine(x, y,
                x + (size*ratio) * Math.cos(direction), 
                y + (size*ratio) * Math.sin(direction));
        x = position.x + (attackOwner.size()/2 * Math.cos(direction + 0.2));
        y = position.y + (attackOwner.size()/2 * Math.sin(direction + 0.2));
        teeth[1].setLine(x, y,
                x + (size*ratio)/1.5 * Math.cos(direction), 
                y + (size*ratio)/1.5 * Math.sin(direction));
        x = position.x + (attackOwner.size()/2 * Math.cos(direction - 0.2));
        y = position.y + (attackOwner.size()/2 * Math.sin(direction - 0.2));
        teeth[2].setLine(x, y,
                x + (size*ratio)/1.5 * Math.cos(direction), 
                y + (size*ratio)/1.5 * Math.sin(direction));
        x = position.x + (attackOwner.size()/2 * Math.cos(direction - 0.6));
        y = position.y + (attackOwner.size()/2 * Math.sin(direction - 0.6));
        teeth[3].setLine(x, y,
                x + (size*ratio) * Math.cos(direction), 
                y + (size*ratio) * Math.sin(direction));
//        teeth[1].setLine(position.x + (attackOwner.size()/2 * Math.cos(direction + 0.1)), 
//                position.y + (attackOwner.size()/2 * Math.sin(direction + 0.1)), 
//                position.x + (size/1.3*ratio) * Math.cos(direction + 0.1), 
//                position.y + (size/1.3*ratio) * Math.sin(direction + 0.1));
//        teeth[2].setLine(position.x + (attackOwner.size()/2 * Math.cos(direction - 0.1)), 
//                position.y + (attackOwner.size()/2 * Math.sin(direction - 0.1)), 
//                position.x + (size/1.3*ratio) * Math.cos(direction - 0.1), 
//                position.y + (size/1.3*ratio) * Math.sin(direction - 0.1));
//        teeth[3].setLine(position.x + (attackOwner.size()/2 * Math.cos(direction - 0.3)), 
//                position.y + (attackOwner.size()/2 * Math.sin(direction - 0.3)), 
//                position.x + (size*ratio) * Math.cos(direction - 0.3), 
//                position.y + (size*ratio) * Math.sin(direction - 0.3));
        
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
                        hitAlready.add(monster);
                    }
                }
            }
        }
    }
    public boolean intersectsCircle(CircleEntity circle){
        for(Line2D.Double tooth : teeth){
            double distance = tooth.ptSegDist(circle.position().x, circle.position().y);
            if(distance <= circle.size()/2)
                return true;            
        }
        return false;
    }
    public void draw(Graphics2D graphics){
        for(Line2D.Double tooth : teeth){
            graphics.setColor(OUTLINE_COLOR);
            graphics.setStroke(new BasicStroke(4));
            graphics.drawLine((int)tooth.x1, (int)tooth.y1, (int)tooth.x2, (int)tooth.y2);
            graphics.setColor(bulletColor);
            graphics.setStroke(new BasicStroke(2));
            graphics.drawLine((int)tooth.x1, (int)tooth.y1, (int)tooth.x2, (int)tooth.y2);
            
        }
        
    }
    
    
}
