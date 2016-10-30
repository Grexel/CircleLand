/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Weapons;

import circleland.Attacks.BombAttack;
import circleland.Attacks.BounceAttack;
import circleland.CircleAttack;
import circleland.CircleEntity;
import circleland.CircleMap;
import circleland.CircleWeapon;
import static circleland.Weapons.BombWeapon.CENTER_COLOR;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Jeff
 */
public class BounceWeapon extends CircleWeapon {
    public static final Color CENTER_COLOR = Color.BLUE; //item type color
    public static final int BASE_ATTACK_MOVESPEED = 100;
    public static final int BASE_ATTACK_LIFE = 1000;
    public static final int BASE_BULLET_SIZE = 10;
    public static final int BASE_PIERCING = 1;
    public static final int BASE_BOUNCE_RADIUS = 50;
    
    protected int bounceRadius;
    public void bounceRadius(int m){bounceRadius = m;}
    public int bounceRadius(){ return bounceRadius;}
    public BounceWeapon()
    {
        super();
        name = "BOUNCE";
        bounceRadius = 50;
    }
    public void attack(CircleEntity owner, ArrayList<CircleAttack> attacks)
    {
        super.attack(owner,attacks);
        double velX = attackMoveSpeed * Math.cos(owner.heading());
        double velY = attackMoveSpeed * Math.sin(owner.heading());
        int dmg = randomizeDamage(owner);
        BounceAttack bA = new BounceAttack(owner,attackLife,dmg,
                piercing,owner.position().x,owner.position().y,
                velX,velY,attackMoveSpeed,bounceRadius,bulletSize,weaponColor);
        bA.piercingLeft(5);
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
