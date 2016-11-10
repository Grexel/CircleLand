/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Loot;

import circleland.Affixes.*;
import circleland.CircleContainer;
import circleland.CircleEntity;
import circleland.CircleEquipment;
import circleland.CircleItem;
import circleland.CircleWeapon;
import circleland.Items.Gold;
import circleland.Weapons.*;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jeff
 */
public class LootGenerator {
    private Random rand;
    private DiagramList diagramList;
    public LootGenerator(){
        rand = new Random();
        diagramList = DiagramLoader.loadDiagram(new File("items/itemList.xml"));
    }
    public CircleItem generateLoot(CircleEntity circle)
    {
        int lootLevel = 0;
        double magicFind = .5;
        lootLevel += circle.level();
        return getItem(lootLevel,magicFind);
    }
    public ArrayList<CircleItem> generateLoot(CircleEntity circle,CircleEntity dead)
    {
        ArrayList<CircleItem> newItems = new ArrayList<>();
        int lootLevel = 0;
        double magicFind = circle.magicFind();
        lootLevel += dead.level();
        int numberOfItems = rand.nextInt(4);
        for(int i = 0; i < numberOfItems;i++){
            CircleItem item = getItem(lootLevel,magicFind);
            if(item != null){
                item.setPosition(dead.position().x + Math.random()*3-3, dead.position().y + Math.random()*3-3);
                newItems.add(item);
            }
        }
        return newItems;
    }
    public ArrayList<CircleItem> generateLoot(CircleEntity circle,CircleContainer container)
    {
        ArrayList<CircleItem> newItems = new ArrayList<>();
        int lootLevel = 0;
        double magicFind = circle.magicFind();
        lootLevel += container.level();
        int numberOfItems = rand.nextInt(4);
        for(int i = 0; i < numberOfItems;i++){
            CircleItem item = getItem(lootLevel,magicFind);
            if(item != null){
                item.setPosition(container.position().x + Math.random()*3-3, container.position().y + Math.random()*3-3);
                newItems.add(item);
            }
        }
        return newItems;
    }
    public CircleItem getItem(String itemName){
        return diagramList.getItem(itemName);
    }
    public CircleItem getItem(int lootLevel,double magicFind){
        int itemType = rand.nextInt(3);
        CircleItem holder = diagramList.getItem(lootLevel-10, lootLevel+10);
        //make items magical
        if(holder instanceof CircleEquipment){
            double magicalNess = Math.random();
            int numAffix = 0;
            
            if(magicFind/4 > magicalNess){  // return relic item
                numAffix = rand.nextInt(4) + 5;
                ((CircleEquipment) holder).rarity(4);
            }
            else if(magicFind/2 > magicalNess){  // return rare item
                numAffix = rand.nextInt(3) + 3;
                ((CircleEquipment) holder).rarity(3);
            }
            else if(magicFind > magicalNess){    //return magic item
                numAffix = rand.nextInt(2) + 1;
                ((CircleEquipment) holder).rarity(2);
            }
            for(int i = 0; i < numAffix; i++)
            {
                this.addAffix((CircleEquipment)holder);
            }
            //add affixes to magical item name
            if(holder.rarity() == 2){
                for(int i = 0; i < ((CircleEquipment) holder).affixes().size(); i++){
                    if(i == 0){
                        holder.name(((CircleEquipment) holder).affixes().get(i).prefix() 
                                + " " + holder.name());
                    }
                    else if(i == 1){
                        holder.name(holder.name() + " " + ((CircleEquipment) holder).affixes().get(i).suffix());
                        
                    }
                }
            }
            //TODO add unique name for rares and relics
        }//end magic items
        
        return holder;
    }
    public void addAffix(CircleEquipment item){
        int affixNum = rand.nextInt(2);
        switch(affixNum){
            case 0 : //add mindamageAffix;
                item.affixes().add(new MinDamageAffix(item.itemLevel()));
                break;
            case 1 : //add mindamageAffix;
                item.affixes().add(new ArmorAffix(item.itemLevel()));
                break;
            default:
                item.affixes().add(new MinDamageAffix(item.itemLevel()));
                break;
        }
    }
}
