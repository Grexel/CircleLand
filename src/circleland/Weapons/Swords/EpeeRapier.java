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
public class EpeeRapier extends RapierWeapon{
    
    public EpeeRapier(){
        super();
        name = "Epée";
        bulletSize(100);
        attackMoveSpeed(200);
        attackSpeed(500);
        weaponType(0);
        attackLife(500);
        weaponColor(Color.GRAY);
        minDamage = 7;
        maxDamage = 14;
        piercing(1);
        itemLevel(3);
    }
}
