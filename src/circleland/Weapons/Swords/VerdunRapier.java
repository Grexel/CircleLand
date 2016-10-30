/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Weapons.Swords;

import circleland.Weapons.RapierWeapon;
import java.awt.Color;

/**
 *
 * @author Jeff
 */
public class VerdunRapier extends RapierWeapon{
    
    public VerdunRapier(){
        super();
        name = "Verdun";
        bulletSize(200);
        attackMoveSpeed(500);
        weaponType(0);
        attackLife(750);
        weaponColor(Color.GRAY);
        minDamage = 35;
        maxDamage = 50;
        piercing(1);
    }
}
