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

public class FoilRapier extends RapierWeapon{
    
    public FoilRapier(){
        super();
        name = "Foil";
        bulletSize(100);
        attackMoveSpeed(200);
        weaponType(0);
        attackLife(500);
        weaponColor(Color.GRAY);
        healthBonus(0);
        manaBonus(0);
        healthRegenBonus(0);
        manaRegenBonus(0);
        attackDamageBonus(20);
        magicDamageBonus(0);
        attackDefenseBonus(0);
        magicDefenseBonus(0);
        precisionBonus(0);
        attackSpeedBonus(0);
        castSpeedBonus(0);
        moveSpeedBonus(0);
        piercing(1);
    }
}
