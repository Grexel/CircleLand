/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Classes.Summons;

import circleland.CircleEntity;
import circleland.CircleLandUtility;
import circleland.CircleMap;
import circleland.CircleSummoned;
import circleland.Weapons.RapierWeapon;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 *
 * @author Jeff
 */
public class SummonedBoneWall extends CircleSummoned{
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
        
    private static final Color BONE_COLOR = Color.BLACK;
    private static final Random rand = new Random();
    private int viewRadius;
    private int moveChangeTimer;
    private int stayAwayRange;
    
    private Point2D.Double staticPosition;
    public void staticPosition(Point2D.Double m){staticPosition = m;}
    public Point2D.Double staticPosition(){return staticPosition;}
    
    @Override
    public void position(Point2D.Double pos){
        staticPosition.x = pos.x;
        staticPosition.y = pos.y;
        position = pos;
    }
    private int lifeLeft;
    public SummonedBoneWall(int skillLevel)
    {
        super();
        staticPosition = new Point2D.Double(0,0);
        lifeLeft = 10000 + skillLevel * 5000;
        baseHealthRegeneration = 0;
        name = "BoneWall";
        size = 40;
        headRadius = 0;
        headSize = 12;
        color = Color.WHITE;
        viewRadius = 250;
        stayAwayRange = (int) (size + 5);
        //initialize stats
        team = CircleLandUtility.MONSTER_SIDE;
        level = 1;
        baseMaxHealth = 50 + skillLevel *20;
        health = maxHealth;
        baseAttackSpeed = 0;
        baseCastSpeed = 0;
        baseMoveSpeed = BASE_MOVESPEED  + level * 10;
        baseAttackDamage = BASE_ATTACK_DAMAGE + level * 20;
        baseAttackDefense = BASE_ATTACK_DEFENSE + level * 15;
        baseMagicDamage = BASE_MAGIC_DAMAGE + level * 2;
        baseMagicDefense = BASE_MAGIC_DEFENSE + level * 4;
        
        gold = 5;
        moveChangeTimer = 0;
        equippedWeapon = new RapierWeapon();
        equippedWeapon.bulletSize(40);
        equippedWeapon.attackMoveSpeed(200);
    }
    public SummonedBoneWall(int x, int y)
    {
        super();
        staticPosition = new Point2D.Double(0,0);
        lifeLeft = 1000;
        name = "BoneWall";
        size = 40;
        headRadius = 0;
        headSize = 12;
        color = Color.WHITE;
        viewRadius = 250;
        position.x = x;
        position.y = y;
        stayAwayRange = 10;
        //initialize stats
        team = CircleLandUtility.MONSTER_SIDE;
        level = 1;
        baseMaxHealth = baseMaxHealth * level;
        health = maxHealth;
        baseAttackSpeed = 0;
        baseCastSpeed = 0;
        baseMoveSpeed = BASE_MOVESPEED  + level * 10;
        baseAttackDamage = BASE_ATTACK_DAMAGE + level * 20;
        baseAttackDefense = BASE_ATTACK_DEFENSE + level * 15;
        baseMagicDamage = BASE_MAGIC_DAMAGE + level * 2;
        baseMagicDefense = BASE_MAGIC_DEFENSE + level * 4;
        gold = 5;
        moveChangeTimer = 0;
        equippedWeapon = new RapierWeapon();
        equippedWeapon.bulletSize(40);
        equippedWeapon.attackMoveSpeed(200);
    }
    public void update(long deltaTime, CircleMap world) {
        lifeLeft -= deltaTime;
        if(lifeLeft <=0)
            health = -1;
        position.x = staticPosition.x;
        position.y = staticPosition.y;
        super.update(deltaTime, world);
        position.x = staticPosition.x;
        position.y = staticPosition.y;
    }
    @Override
    public void draw(Graphics2D graphics){
        super.draw(graphics);
        //draw X
    }
    
}
