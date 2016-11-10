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
    private static Random rand = new Random();

    public static Random getRand() {
        return rand;
    }

    public static void setRand(Random aRand) {
        rand = aRand;
    }
    private String armorType;
    private Color color;
    private int minDefense;
    private int maxDefense;

    public ArmorDiagram(String name, int itemLevel, String armorType, Color color, int minDefense, int maxDefense) {
        super(itemLevel,name);
        this.armorType = armorType;
        this.color = color;
        this.minDefense = minDefense;
        this.maxDefense = maxDefense;
    }
    @Override
    public CircleItem buildItem() {
        if(armorType.equalsIgnoreCase("armor")){
            CircleArmor armor = new CircleArmor();
            armor.itemLevel(getItemLevel());
            armor.name(getName());
            armor.specialColor(color);
            armor.defense(rand.nextInt(maxDefense - minDefense)+minDefense);
            return armor;
        }
        return null;
    }

    public String getArmorType() {
        return armorType;
    }

    public void setArmorType(String armorType) {
        this.armorType = armorType;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getMinDefense() {
        return minDefense;
    }

    public void setMinDefense(int minDefense) {
        this.minDefense = minDefense;
    }

    public int getMaxDefense() {
        return maxDefense;
    }

    public void setMaxDefense(int maxDefense) {
        this.maxDefense = maxDefense;
    }

    
}
