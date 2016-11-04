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

public class RevolverBullet extends BulletWeapon{
    
    public RevolverBullet(){
        super();
        name = "Revolver";
        bulletSize(15);
        attackMoveSpeed(400);
        attackSpeed(500);
        weaponType(0);
        attackLife(500);
        weaponColor(Color.GRAY);
        minDamage = 7;
        maxDamage = 12;
        piercing(1);
        itemLevel(3);
    }
}
