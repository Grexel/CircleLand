/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Weapons;

import circleland.Attacks.BombAttack;
import circleland.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Jeff
 */
public class BombWeapon extends CircleWeapon{
    public static final Color CENTER_COLOR = Color.BLACK; //item type color
    public static final int BASE_BLOWUP_DELAY = 750;
    public static final int BASE_ATTACK_MOVESPEED = 150;
    public static final int BASE_ATTACK_LIFE = 500;
    public static final int BASE_BULLET_SIZE = 10;
    public static final int BASE_PIERCING = 1;
    
    protected int blowUpDelay;
    public void blowUpDelay(int m){blowUpDelay = m;}
    public int blowUpDelay(){ return blowUpDelay;}
    public BombWeapon()
    {
        super();
        name = "BOMB";
        rarityColor = Color.WHITE;
    }
    public void attack(CircleEntity owner, ArrayList<CircleAttack> attacks)
    {
        super.attack(owner,attacks);
        double velX = attackMoveSpeed * Math.cos(owner.heading());
        double velY = attackMoveSpeed * Math.sin(owner.heading());
        int dmg = randomizeDamage(owner);
        BombAttack bA = new BombAttack(owner,attackLife,dmg,
                piercing,owner.position().x,owner.position().y,velX,velY,blowUpDelay,bulletSize,weaponColor);
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
