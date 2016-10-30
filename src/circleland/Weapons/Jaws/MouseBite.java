/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Weapons.Jaws;

import circleland.Weapons.BiteWeapon;
import circleland.Weapons.RapierWeapon;
import java.awt.Color;

/**
 *
 * @author Jeff
 */
public class MouseBite extends BiteWeapon{
    
    public MouseBite(){
        super();
        name = "Mouse Jaw";
        bulletSize(40);
        attackMoveSpeed(100);
        attackSpeed(500);
        weaponType(0);
        attackLife(250);
        weaponColor(Color.GRAY);
        minDamage = 2;
        maxDamage = 4;
        piercing(1);
        itemLevel(1);
    }
}
