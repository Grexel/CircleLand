/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Loot;

import circleland.CircleItem;
import circleland.Equipment.CircleArmor;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Jeff
 */
public class ArmorDiagram extends ItemDiagram{
    public static Random rand = new Random();
    String name;
    int itemLevel;
    String armorType;
    Color color;
    int minDefense;
    int maxDefense;

    public ArmorDiagram(String name, int itemLevel, String armorType, Color color, int minDefense, int maxDefense) {
        this.name = name;
        this.itemLevel = itemLevel;
        this.armorType = armorType;
        this.color = color;
        this.minDefense = minDefense;
        this.maxDefense = maxDefense;
    }
    @Override
    public CircleItem buildItem() {
        if(armorType.equalsIgnoreCase("armor")){
            CircleArmor armor = new CircleArmor();
            armor.itemLevel(itemLevel);
            armor.name(name);
            armor.itemColor(color);
            armor.defense(rand.nextInt(maxDefense - minDefense)+minDefense);
            return armor;
        }
        return null;
    }

    
}
