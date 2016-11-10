/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Monsters;

import circleland.CircleAttack;
import circleland.CircleEntity;
import circleland.CircleLandUtility;
import circleland.CircleMap;
import circleland.CircleMonster;
import circleland.Weapons.BulletWeapon;
import circleland.Weapons.RapierWeapon;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

/**
 *
 * @author Jeff
 */
public class SkeletonRangerMonster extends CircleMonster{
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
        
    private static final Color BONE_COLOR = Color.BLACK;
    private static final Random rand = new Random();
    private int viewRadius;
    private int moveChangeTimer;
    private int stayAwayRange;
    
    public SkeletonRangerMonster()
    {
        super();
        name = "Skeleton Ranger";
        size = 25;
        headRadius = 16;
        headSize = 12;
        color = Color.WHITE;
        viewRadius = 250;
        stayAwayRange = 80;
        //initialize stats
        team = CircleLandUtility.MONSTER_SIDE;
        level = 7;
        maxHealth = 100;
        health = 100;
        attackSpeed = 500;
        castSpeed = 100;
        moveSpeed = 140;
        gold = 5;
        moveChangeTimer = 0;
        equippedWeapon = new BulletWeapon();
        equippedWeapon.bulletSize(15);
        equippedWeapon.attackMoveSpeed(200);
    }
    public SkeletonRangerMonster(int x, int y)
    {
        super();
        name = "Skeleton Ranger";
        size = 25;
        headRadius = 16;
        headSize = 12;
        color = Color.WHITE;
        viewRadius = 250;
        position.x = x;
        position.y = y;
        stayAwayRange = 80;
        //initialize stats
        team = CircleLandUtility.MONSTER_SIDE;
        level = 7;
        maxHealth = 100;
        health = 100;
        attackSpeed = 500;
        castSpeed = 100;
        moveSpeed = 140;
        gold = 5;
        moveChangeTimer = 0;
        equippedWeapon = new BulletWeapon();
        equippedWeapon.bulletSize(15);
        equippedWeapon.attackMoveSpeed(200);
    }
    @Override
    public void draw(Graphics2D graphics){
        super.draw(graphics);
        //draw X
        graphics.setColor(BONE_COLOR);
        graphics.setStroke(new BasicStroke(4));
        int cX = (int)position.x;
        int cY = (int)position.y;
        int x1 = cX + (int) Math.round((size/2)
                * Math.cos(heading + (Math.PI/4)));
        int y1 = cY + (int) Math.round((size/2)
                * Math.sin(heading + (Math.PI/4)));
        int x2 = cX + (int) Math.round((size/2)
                * Math.cos(heading - (Math.PI * 3 / 4)));
        int y2 = cY + (int) Math.round((size/2)
                * Math.sin(heading - (Math.PI * 3 / 4)));
        int x3 = cX + (int) Math.round((size/2)
                * Math.cos(heading - (Math.PI/4)));
        int y3 = cY + (int) Math.round((size/2)
                * Math.sin(heading - (Math.PI/4)));
        int x4 = cX + (int) Math.round((size/2)
                * Math.cos(heading + (Math.PI * 3 / 4)));
        int y4 = cY + (int) Math.round((size/2)
                * Math.sin(heading + (Math.PI * 3 / 4)));
        graphics.drawLine(x1, y1, x2, y2);
        graphics.drawLine(x3, y3, x4, y4);
    }
    
}
