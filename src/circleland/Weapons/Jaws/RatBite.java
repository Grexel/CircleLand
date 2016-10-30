/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Weapons.Jaws;

import circleland.Weapons.BiteWeapon;
import java.awt.Color;

/**
 *
 * @author Jeff
 */

public class RatBite extends BiteWeapon{
    
    public RatBite(){
        super();
        name = "Rat Jaw";
        bulletSize(40);
        attackMoveSpeed(100);
        attackSpeed(500);
        weaponType(0);
        attackLife(250);
        weaponColor(Color.GRAY);
        minDamage = 4;
        maxDamage = 6;
        piercing(1);
        itemLevel(2);
    }
}
