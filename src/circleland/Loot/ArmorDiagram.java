/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Loot;

import circleland.CircleEquipment;
import circleland.CircleItem;
import circleland.Equipment.*;
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
        CircleEquipment armor;
        if(armorType.equalsIgnoreCase("armor")){
            armor = new CircleArmor();
        }
        else if(armorType.equalsIgnoreCase("helmet")){
            armor = new CircleHelmet();
        }
        else if(armorType.equalsIgnoreCase("gloves")){
            armor = new CircleGloves();
        }
        else if(armorType.equalsIgnoreCase("boots")){
            armor = new CircleBoots();
        }
        else if(armorType.equalsIgnoreCase("ring")){
            armor = new CircleRing();
        }
        else if(armorType.equalsIgnoreCase("amulet")){
            armor = new CircleAmulet();
        }
        else{
            armor = new CircleArmor();
        }
        
        armor.itemLevel(getItemLevel());
        armor.name(getName());
        armor.specialColor(getColor());
        armor.defense(rand.nextInt(maxDefense - minDefense+1)+minDefense);
        return armor;
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
