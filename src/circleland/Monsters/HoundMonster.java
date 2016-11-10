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
import circleland.Weapons.BombWeapon;
import circleland.Weapons.BulletWeapon;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Jeff
 */
public class HoundMonster extends CircleMonster{
    public static final int BASE_MAXHEALTH= 10;
    public static final int BASE_HEALTHREGEN= 1;
    public static final int BASE_MAXMANA= 10;
    public static final int BASE_MANAREGEN= 10;
    public static final int BASE_ATTACK_DAMAGE= 1;
    public static final int BASE_MAGIC_DAMAGE= 1;
    public static final int BASE_ATTACK_DEFENSE= 2;
    public static final int BASE_MAGIC_DEFENSE= 1;
    public static final int BASE_PRECISION = 1;
    public static final int BASE_ATTACKSPEED= 0;
    public static final int BASE_CASTSPEED= 0;
    public static final int BASE_MOVESPEED= 80;
    
    private int viewRadius;
    public HoundMonster()
    {
        super();
        name = "Hound";
        size = 25;
        headRadius = 16;
        headSize = 12;
        color = Color.CYAN;
        viewRadius = 200;
        
        //initialize stats
        team = CircleLandUtility.MONSTER_SIDE;
        level = 5;
        maxHealth = 100;
        health = 100;
        attackSpeed = 1000;
        castSpeed = 100;
        moveSpeed = 100;
        gold = 5;
        equippedWeapon = new BulletWeapon();
    }
    public HoundMonster(int x, int y)
    {
        super();
        name = "Hound";
        size = 25;
        headRadius = 16;
        headSize = 12;
        color = Color.CYAN;
        viewRadius = 200;
        position.x = x;
        position.y = y;
        
        //initialize stats
        team = CircleLandUtility.MONSTER_SIDE;
        level = 5;
        maxHealth = 100;
        health = 100;
        attackSpeed = 1000;
        castSpeed = 100;
        moveSpeed = 100;
        gold = 5;
        equippedWeapon = new BulletWeapon();
    }
}
