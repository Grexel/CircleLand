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
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Jeff
 */
public class RedImpSpawner extends CircleMonster {
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
    
    private Point2D.Double staticPosition;
    public void staticPosition(Point2D.Double m){staticPosition = m;}
    public Point2D.Double staticPosition(){return staticPosition;}
    
    private ArrayList<CircleEntity> spawnedMonsters;
    public void spawnedMonsters(ArrayList<CircleEntity> m){spawnedMonsters = m;}
    public ArrayList<CircleEntity> spawnedMonsters(){return spawnedMonsters;}
    
    private static final int MAX_SPAWNED = 5;
    
    public RedImpSpawner()
    {
        super();
        name = "Red Imp Spawner";
        size = 50;
        headRadius = 12;
        headSize = 12;
        color = Color.RED;
        staticPosition = new Point2D.Double(0,0);
        spawnedMonsters = new ArrayList<>();
        
        //initialize stats
        team = CircleLandUtility.MONSTER_SIDE;
        level = 5;
        baseMaxHealth = 1000;
        maxHealth = 1000;
        health = 1000;
        baseAttackSpeed = 5000;
        gold = 5;
        equippedWeapon = new BulletWeapon();
        doAttack = true;
    }
    public RedImpSpawner(int x, int y)
    {
        super();
        name = "Red Imp Spawner";
        size = 50;
        headRadius = 12;
        headSize = 12;
        color = Color.RED;
        staticPosition = new Point2D.Double(x,y);
        
        //initialize stats
        team = CircleLandUtility.MONSTER_SIDE;
        level = 5;
        baseMaxHealth = 1000;
        maxHealth = 1000;
        health = 1000;
        baseAttackSpeed = 5000;
        gold = 5;
        equippedWeapon = new BulletWeapon();
        doAttack = true;
    }
    public void update(long deltaTime, CircleMap world) {
        position.x = staticPosition.x;
        position.y = staticPosition.y;
        for (Iterator<CircleEntity> iterator = spawnedMonsters.iterator(); iterator.hasNext();) {
            CircleEntity cE = iterator.next();
            if (cE.health() <= 0) {
            // Remove the current element from the iterator and the list.
                iterator.remove();
            }
        }
        super.update(deltaTime, world);
        
        //debug
        //System.out.println("Position: " + position.x + "," + position.y);
    }
    @Override
    public void attack(CircleMap world){
        if(spawnedMonsters.size() < MAX_SPAWNED)
        {
            int spawnDirection =(int) (Math.random() * 360);
            RedImpMonster imp = new RedImpMonster();
            int x = (int)position.x + (int) Math.round((size+20)
                    * Math.cos(spawnDirection));
            int y = (int)position.y + (int) Math.round((size + 20)
                * Math.sin(spawnDirection));
            imp.position().x = x;
            imp.position().y = y;
            world.spawningMonsters().add(imp);
            spawnedMonsters.add(imp);
            attackTimer = (int)attackSpeed;
        }
    }
}
