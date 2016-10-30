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

public class BeaverBite extends BiteWeapon{
    
    public BeaverBite(){
        super();
        name = "Beaver Jaw";
        bulletSize(60);
        attackMoveSpeed(100);
        weaponType(0);
        attackLife(250);
        weaponColor(Color.GRAY);
        minDamage = 6;
        maxDamage = 9;
        piercing(1);
    }
}
