/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Equipment;

import circleland.CircleEntity;
import circleland.CircleEquipment;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Jeff
 */
public class CircleRing  extends CircleEquipment {
    public CircleRing(){
        super();
        name = "Ring";
        itemColor = new Color(0xFFCC5C);
    }
}
