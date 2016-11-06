/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Equipment;

import circleland.CircleEntity;
import circleland.CircleEquipment;
import static circleland.Weapons.BoomerangWeapon.CENTER_COLOR;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Jeff
 */
public class CircleArmor extends CircleEquipment {

    public CircleArmor(){
        super();
        name = "Armor";
        itemColor = new Color(0x3B3A30);
        
    }
}
