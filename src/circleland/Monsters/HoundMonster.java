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
    public static final int BASE_ATTACKSPEED= 1000;
    public static final int BASE_CASTSPEED= 1000;
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
    public void update(long deltaTime, CircleMap world) {
        //Go to focusedEntity
        if(focusedEntity != null)
        {
            //moveLeft and right
            if (focusedEntity.position().x > position.x + 10){
                moveRight = true;
                moveLeft = false;
            } else if(focusedEntity.position().x < position.x -10) {
                moveLeft = true;
                moveRight = false;
            } else {
                moveLeft = false;
                moveRight = false;
            }
            //moveUp and down
            if (focusedEntity.position().y > position.y + 10){
                moveDown = true;
                moveUp = false;
            } else if(focusedEntity.position().y < position.y -10) {
                moveUp = true;
                moveDown = false;
            } else {
                moveUp = false;
                moveDown = false;
            }
            //face toward focused entity
            aim.x = focusedEntity.position().x;
            aim.y = focusedEntity.position().y;
            doAttack = true;
            if(distanceBetweenPoints(focusedEntity.position(), position) > viewRadius * 1.5)
            {
                focusedEntity = null;
                doAttack = false;
            }
        } 
        else {
            //do random movements?
            moveLeft = false;
            moveRight = false;
            moveUp = false;
            moveDown = false;
            if(((Math.random() * 10) % 9) == 1)
                moveLeft = true;
            if(((Math.random() * 10) % 9) == 1)
                moveRight = true;
            if(((Math.random() * 10) % 9) == 1)
                moveUp = true;
            if(((Math.random() * 10) % 9) == 1)
                moveDown = true;
            doAttack = false;
            for(CircleEntity cE : world.players()) {
                if(cE.intersectsCircle(position, viewRadius))
                {
                    //this is our new focus
                    focusedEntity = cE;
                    break;
                }
            }
        }
        super.update(deltaTime, world);
    }
}
