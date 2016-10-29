/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Weapons;

import circleland.Attacks.BulletAttack;
import circleland.Attacks.SplittingAttack;
import circleland.CircleAttack;
import circleland.CircleEntity;
import circleland.CircleWeapon;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Jeff
 */
public class SplittingWeapon extends CircleWeapon{
    public static final Color CENTER_COLOR = Color.LIGHT_GRAY; //item type color
    public static final int BASE_ATTACK_MOVESPEED = 100;
    public static final int BASE_ATTACK_LIFE = 1000;
    public static final int BASE_HEALTH_BONUS = 100;
    public static final int BASE_MANA_BONUS = 100;
    public static final int BASE_HEALTHREGEN_BONUS = 1;
    public static final int BASE_MANAREGEN_BONUS = 1;
    public static final int BASE_ATTACKDAMAGE_BONUS = 5;
    public static final int BASE_MAGICDAMAGE_BONUS = 0;
    public static final int BASE_ATTACKDEFENSE_BONUS = 2;
    public static final int BASE_MAGICDEFENSE_BONUS = 0;
    public static final int BASE_PRECISION_BONUS = 1;
    public static final int BASE_ATTACKSPEED_BONUS = 100;
    public static final int BASE_CASTSPEED_BONUS = 0;
    public static final int BASE_MOVESPEED_BONUS = 20;
    public static final int BASE_BULLET_SIZE = 10;
    public static final int BASE_PIERCING = 2;
    
    public SplittingWeapon()
    {
        super();
        name = "SPLITTER";
    }
    public void attack(CircleEntity owner, ArrayList<CircleAttack> attacks)
    {
        super.attack(owner,attacks);
        double velX = attackMoveSpeed * Math.cos(owner.heading());
        double velY = attackMoveSpeed * Math.sin(owner.heading());
        SplittingAttack bA = new SplittingAttack(owner,attackLife,owner.attackDamage(),
                piercing,owner.position().x,owner.position().y,velX,velY,bulletSize,weaponColor);
        
        attacks.add(bA);
    }
    public void draw(Graphics2D graphics)
    {
        draw(graphics,(int)position.x,(int)position.y);
    }
    public void draw(Graphics2D graphics, int x, int y)
    {
        graphics.setColor(rarityColor);
        graphics.fillOval((int)(x - (size+4)/ 2), (int)(y - (size+4) / 2),
            (int)size+4, (int)size+4);
        graphics.setColor(Color.YELLOW);
        graphics.fillOval((int)(x - size / 2), (int)(y - size / 2),
            (int)size, (int)size);
        graphics.setColor(CENTER_COLOR);
        graphics.fillOval((int)(x - 4), (int)(y - 4),
            (int)8, (int)8);
    }
    
}
