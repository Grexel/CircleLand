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
public class FlintPistolBullet extends BulletWeapon{
    
    public FlintPistolBullet(){
        super();
        name = "Flint Pistol";
        bulletSize(15);
        attackMoveSpeed(250);
        attackSpeed(650);
        weaponType(0);
        attackLife(875);
        weaponColor(Color.GRAY);
        minDamage = 4;
        maxDamage = 7;
        piercing(1);
        itemLevel(2);
    }
}

