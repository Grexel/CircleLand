/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Monsters;

import circleland.*;
import circleland.CircleMap;
import circleland.Weapons.BulletWeapon;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Jeff
 */
public class CadaverMonster extends CircleMonster{
    public static final int BASE_MAXHEALTH= 10;
    public static final int BASE_HEALTHREGEN= 1;
    public static final int BASE_MAXMANA= 10;
    public static final int BASE_MANAREGEN= 10;
    public static final int BASE_ATTACK_DAMAGE= 1;
    public static final int BASE_MAGIC_DAMAGE= 1;
    public static final int BASE_ATTACK_DEFENSE= 2;
    public static final int BASE_MAGIC_DEFENSE= 1;
    public static final int BASE_PRECISION = 1;
    public static final int BASE_ATTACKSPEED= 1000;
    public static final int BASE_CASTSPEED= 1000;
    public static final int BASE_MOVESPEED= 80;
        
    public static final Color OUTLINE_COLOR = Color.BLACK;
    private Point2D.Double staticPosition;
    public void staticPosition(Point2D.Double m){staticPosition = m;}
    public Point2D.Double staticPosition(){return staticPosition;}
    
    public CadaverMonster()
    {
        super();
        name = "Cadaver";
        size = 50;
        headRadius = 12;
        headSize = 12;
        color = Color.WHITE;
        staticPosition = new Point2D.Double(0,0);
        
        //initialize stats
        team = CircleLandUtility.MONSTER_SIDE;
        level = 5;
        baseMaxHealth = 200;
        health = 200;
        attackSpeed = 2000;
        gold = 5;
        equippedWeapon = new BulletWeapon();
        doAttack = true;
    }
    public CadaverMonster(int x, int y)
    {
        super();
        name = "Cadaver";
        size = 50;
        headRadius = 12;
        headSize = 12;
        color = Color.WHITE;
        staticPosition = new Point2D.Double(x,y);
        
        //initialize stats
        team = CircleLandUtility.MONSTER_SIDE;
        level = 5;
        baseMaxHealth = 200;
        health = 200;
        attackSpeed = 2000;
        gold = 5;
        equippedWeapon = new BulletWeapon();
        doAttack = true;
    }
        
    @Override
    public void update(long deltaTime, CircleMap world) {
        attackTimer -= deltaTime;
        position.x = staticPosition.x;
        position.y = staticPosition.y;
        //collide with Other circles
        super.update(deltaTime, world);
        
        //debug
        //System.out.println("Position: " + position.x + "," + position.y);
    }
    public void attack(CircleMap world){
            int spawnDirection =(int) (Math.random() * 2 * Math.PI);
            int x = (int)position.x + (int) Math.round((size + 10)
                    * Math.cos(spawnDirection));
            int y = (int)position.y + (int) Math.round((size + 10)
                * Math.sin(spawnDirection));
            FlyMonster fly = new FlyMonster(x,y);
            world.spawningMonsters().add(fly);
            attackTimer = (int)attackSpeed;
    }
    public void drawBody(Graphics2D graphics)
    {
        //draw Circle body
        graphics.setColor(OUTLINE_COLOR);
        graphics.fillOval((int)(position.x - (size+6)/ 2), (int)(position.y - (size-6) / 2),
            (int)size+6, (int)size-12);
        graphics.setColor(color);
        graphics.fillOval((int)(position.x - size / 2), (int)(position.y - (size-12) / 2),
            (int)size, (int)size-18);
        
    }
    public void drawHead(Graphics2D graphics){
        //draw Circle heading
        int x1 = (int)position.x;
        int y1 = (int)position.y;
        int x2 = x1 + (int) Math.round((headRadius)
                * Math.cos(heading)) + headSize/2;
        int y2 = y1 + (int) Math.round((headRadius)
                * Math.sin(heading)) + headSize/2;
        graphics.setColor(OUTLINE_COLOR);
        graphics.drawOval(x2 - (headSize+2), y2 - (headSize+2), headSize+4, headSize+4);
        graphics.setColor(color);
        graphics.drawOval(x2 - headSize, y2 - headSize, headSize, headSize);
    }
    
    
}
