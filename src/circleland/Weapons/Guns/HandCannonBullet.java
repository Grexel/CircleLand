/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Weapons.Guns;

import circleland.Weapons.*;
import java.awt.Color;

/**
 *
 * @author Jeff
 */
public class HandCannonBullet extends BulletWeapon{
    
    public HandCannonBullet(){
        super();
        name = "HandCannon";
        bulletSize(15);
        attackMoveSpeed(300);
        attackSpeed(750);
        weaponType(0);
        attackLife(666);
        weaponColor(Color.GRAY);
        minDamage = 2;
        maxDamage = 4;
        piercing(1);
        itemLevel(1);
    }
}
