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

public class EstocRapier extends RapierWeapon{
    
    public EstocRapier(){
        super();
        name = "Estoc";
        bulletSize(100);
        attackMoveSpeed(200);
        weaponType(0);
        attackLife(500);
        weaponColor(Color.GRAY);
        minDamage = 14;
        maxDamage = 25;
        piercing(1);
    }
}
