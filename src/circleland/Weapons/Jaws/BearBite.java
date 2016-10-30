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
public class BearBite extends BiteWeapon{
    
    public BearBite(){
        super();
        name = "Bear Jaw";
        bulletSize(100);
        attackMoveSpeed(200);
        weaponType(0);
        attackLife(500);
        weaponColor(Color.GRAY);
        minDamage = 18;
        maxDamage = 25;
        piercing(1);
    }
}
