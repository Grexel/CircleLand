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
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Jeff
 */
public class RedImpMonster extends CircleMonster{
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
    public static final int BASE_CASTSPEED= 1000;
    public static final int BASE_MOVESPEED= 120;
    
    private int stayAwayRange;
    public RedImpMonster()
    {
        super();
        name = "Red Imp";
        size = 15;
        headRadius = 12;
        headSize = 12;
        color = Color.RED;
        stayAwayRange = 100;
        
        //initialize stats
        team = CircleLandUtility.MONSTER_SIDE;
        level = 5;
        baseMaxHealth = BASE_MAXHEALTH * level;
        health = maxHealth;
        baseAttackSpeed = BASE_ATTACKSPEED;
        baseCastSpeed = BASE_CASTSPEED;
        baseMoveSpeed = BASE_MOVESPEED;
        gold = 5;
        equippedWeapon = new BulletWeapon();
    }
}
