/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Weapons.Guns;

import circleland.Weapons.BulletWeapon;
import java.awt.Color;

/**
 *
 * @author Jeff
 */
public class GlockBullet extends BulletWeapon{
    
    public GlockBullet(){
        super();
        name = "Glock";
        bulletSize(18);
        attackMoveSpeed(300);
        attackSpeed(400);
        weaponType(0);
        attackLife(666);
        weaponColor(Color.GRAY);
        minDamage = 11;
        maxDamage = 16;
        piercing(1);
        itemLevel(4);
    }
}
