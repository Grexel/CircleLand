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
public class HippoBite extends BiteWeapon{
    
    public HippoBite(){
        super();
        name = "Hippo Jaw";
        bulletSize(100);
        attackMoveSpeed(200);
        attackSpeed(500);
        weaponType(0);
        attackLife(500);
        weaponColor(Color.GRAY);
        minDamage = 23;
        maxDamage = 29;
        piercing(1);
        itemLevel(7);
    }
}
