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
public class SWShieldBullet extends BulletWeapon{
    
    public SWShieldBullet(){
        super();
        name = "Smith and Wesson Shield";
        bulletSize(20);
        attackMoveSpeed(400);
        attackSpeed(400);
        weaponType(0);
        attackLife(500);
        weaponColor(Color.GRAY);
        minDamage = 21;
        maxDamage = 35;
        piercing(1);
        itemLevel(8);
    }
}
