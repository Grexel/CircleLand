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
    public static final int BASE_ATTACKSPEED= 1000;
    public static final int BASE_CASTSPEED= 1000;
    public static final int BASE_MOVESPEED= 80;
    
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
    public void update(long deltaTime, CircleMap world) {
        //Go to focusedEntity
        if(focusedEntity != null)
        {
            //moveLeft and right
            if (focusedEntity.position().x > position.x + stayAwayRange){
                moveRight = true;
                moveLeft = false;
            } else if(focusedEntity.position().x < position.x - stayAwayRange) {
                moveLeft = true;
                moveRight = false;
            } else {
                moveLeft = false;
                moveRight = false;
            }
            //moveUp and down
            if (focusedEntity.position().y > position.y + stayAwayRange){
                moveDown = true;
                moveUp = false;
            } else if(focusedEntity.position().y < position.y - stayAwayRange) {
                moveUp = true;
                moveDown = false;
            } else {
                moveUp = false;
                moveDown = false;
            }
            if(isFeared)
            {
                moveLeft = !moveLeft;
                moveRight = !moveRight;
                moveUp = !moveUp;
                moveDown = !moveDown;
            }
            //face toward focused entity
            aim.x = focusedEntity.position().x;
            aim.y = focusedEntity.position().y;
            doAttack = true;
            if(focusedEntity.health()<=0){
                focusedEntity = null;
                doAttack = false;
            }
        }
        
        super.update(deltaTime, world);
        //debug
        //System.out.println("Position: " + position.x + "," + position.y);
    }
}
