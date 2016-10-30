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

public class AlligatorBite extends BiteWeapon{
    
    public AlligatorBite(){
        super();
        name = "Alligator Jaw";
        bulletSize(100);
        attackMoveSpeed(200);
        attackSpeed(500);
        weaponType(0);
        attackLife(500);
        weaponColor(Color.GRAY);
        minDamage = 31;
        maxDamage = 40;
        piercing(1);
        itemLevel(8);
    }
}
