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

public class Colt45Bullet extends BulletWeapon{
    
    public Colt45Bullet(){
        super();
        name = "Colt .45";
        bulletSize(20);
        attackMoveSpeed(400);
        attackSpeed(600);
        weaponType(0);
        attackLife(500);
        weaponColor(Color.GRAY);
        minDamage = 21;
        maxDamage = 35;
        piercing(2);
        itemLevel(7);
    }
}

