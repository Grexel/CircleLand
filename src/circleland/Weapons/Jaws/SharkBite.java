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

public class SharkBite extends BiteWeapon{
    
    public SharkBite(){
        super();
        name = "Shark Jaw";
        bulletSize(100);
        attackMoveSpeed(200);
        weaponType(0);
        attackLife(500);
        weaponColor(Color.GRAY);
        minDamage = 14;
        maxDamage = 19;
        piercing(1);
    }
}
