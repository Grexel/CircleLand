/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import circleland.Affixes.*;
import circleland.Items.Gold;
import circleland.Weapons.*;
import circleland.Weapons.Jaws.*;
import circleland.Weapons.Swords.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jeff
 */
public class LootGenerator {
    private Random rand;
    public LootGenerator(){
        rand = new Random();
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
    public CircleItem getItem(int lootLevel,double magicFind){
        int itemType = rand.nextInt(3);
        CircleItem holder;
        switch(itemType){
            case 0: holder = getBiteWeapon(lootLevel); break;
            case 1: holder = getRapierWeapon(lootLevel); break;
            default: holder = new Gold(lootLevel,0,0);
        }
        
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
            if(holder.rarity == 2){
                for(int i = 0; i < ((CircleEquipment) holder).affixes().size(); i++){
                    if(i == 0){
                        holder.name(((CircleEquipment) holder).affixes().get(i).prefix() 
                                + " " + holder.name);
                    }
                    else if(i == 1){
                        holder.name(holder.name + " " + ((CircleEquipment) holder).affixes().get(i).suffix());
                        
                    }
                }
            }
            //TODO add unique name for rares and relics
        }//end magic items
        
        return holder;
    }
    public CircleWeapon getBiteWeapon(int level){
        if(level < 3){
            return new MouseBite();
        }
        if(level < 6){
            return new RatBite();
        }
        if(level < 9){
            return new BeaverBite();
        }
        if(level < 12){
            return new WolfBite();
        }
        if(level < 15){
            return new SharkBite();
        }
        if(level < 18){
            return new BearBite();
        }
        if(level < 21){
            return new HippoBite();
        }
        if(level < 24){
            return new AlligatorBite();
        }else
            return new MouseBite();
    }
    public CircleWeapon getRapierWeapon(int level){
        
        if(level < 3){
            return new SabreRapier();
        }
        if(level < 6){
            return new FoilRapier();
        }
        if(level < 9){
            return new EpeeRapier();
        }
        if(level < 12){
            return new EstocRapier();
        }
        if(level < 15){
            return new VerdunRapier();
        }
        if(level < 18){
            return new CourtSwordRapier();
        }
        else
            return new SabreRapier();
        
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
