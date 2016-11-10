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
import circleland.Weapons.*;
import java.awt.Color;
import java.awt.geom.Point2D;

/**
 *
 * @author Jeff
 */
public class TurretMonster extends CircleMonster{
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
        
    private double turretRotation;
    private double rotationSpeed;
    private double viewRadius;
    
    private Point2D.Double staticPosition;
    public void staticPosition(Point2D.Double m){staticPosition = m;}
    public Point2D.Double staticPosition(){return staticPosition;}
    
    public TurretMonster()
    {
        super();
        name = "Turret";
        size = 50;
        headRadius = (int)size/2;
        headSize = 20;
        turretRotation = 0;
        rotationSpeed = Math.PI/2;
        viewRadius = 150;
        color = Color.MAGENTA;
        staticPosition = new Point2D.Double(0,0);
        
        //initialize stats
        team = CircleLandUtility.MONSTER_SIDE;
        level = 5;
        maxHealth = 200;
        health = 200;
        attackSpeed = 1000;
        castSpeed = 100;
        moveSpeed = 100;
        gold = 5;
        equippedWeapon = new BulletWeapon();
    }
    public TurretMonster(int x, int y)
    {
        super();
        name = "Turret";
        size = 50;
        headRadius = (int)size/2;
        headSize = 20;
        turretRotation = 0;
        rotationSpeed = Math.PI/2;
        viewRadius = 150;
        color = Color.MAGENTA;
        staticPosition = new Point2D.Double(x,y);
        
        //initialize stats
        team = CircleLandUtility.MONSTER_SIDE;
        level = 5;
        maxHealth = 200;
        health = 200;
        attackSpeed = 1000;
        castSpeed = 100;
        moveSpeed = 100;
        gold = 5;
        equippedWeapon = new BulletWeapon();
    }
    @Override
    public void update(long deltaTime, CircleMap world) {
        super.update(deltaTime, world);
        position.x = staticPosition.x;
        position.y = staticPosition.y;
        //collide with Other circles
    }
    
}
