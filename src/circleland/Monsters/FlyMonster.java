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
import java.util.Random;

/**
 *
 * @author Jeff
 */
public class FlyMonster extends CircleMonster{
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
    
    private static Random rand = new Random();
    private int viewRadius;
    private int moveChangeTimer;
    public FlyMonster()
    {
        super();
        name = "Fly";
        size = 10;
        headRadius = 2;
        headSize = 2;
        color = Color.GRAY;
        viewRadius = 100;
        position.x = 0;
        position.y = 0;
        
        //initialize stats
        moveChangeTimer = 0;
        team = CircleLandUtility.MONSTER_SIDE;
        level = 5;
        maxHealth = 50;
        health = 50;
        baseAttackSpeed = 1000;
        castSpeed = 100;
        moveSpeed = 100;
        gold = 5;
        equippedWeapon = new BulletWeapon();
    }
    public FlyMonster(int x, int y)
    {
        super();
        name = "Fly";
        size = 10;
        headRadius = 2;
        headSize = 2;
        color = Color.GRAY;
        viewRadius = 100;
        position.x = x;
        position.y = y;
        
        //initialize stats
        moveChangeTimer = 0;
        team = CircleLandUtility.MONSTER_SIDE;
        level = 5;
        maxHealth = 50;
        health = 50;
        baseAttackSpeed = 1000;
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
            if(distanceBetweenPoints(focusedEntity.position(), position) > viewRadius)
            {
                focusedEntity = null;
                doAttack = false;
            }
        } 
        else if(moveChangeTimer <= 0){
            int moveChange = rand.nextInt(4);
            switch(moveChange){
                case 0: moveLeft(!moveLeft());break;
                case 1: moveRight(!moveRight());break;
                case 2: moveUp(!moveUp());break;
                case 3: moveDown(!moveDown());break;
            }
            moveChangeTimer = 500;
        }
        else{
            for(CircleEntity cE : world.players()) {
                if(cE.intersectsCircle(position, viewRadius))
                {
                    //this is our new focus
                    focusedEntity = cE;
                    break;
                }
            }
            
        }
        //decrement timers;
        moveChangeTimer -= deltaTime;
        
        super.update(deltaTime, world);
    }
    
}
