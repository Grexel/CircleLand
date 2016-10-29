/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import circleland.Items.Gold;
import circleland.Weapons.*;
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
//    public ArrayList<CircleItem> generateLoot(CircleEntity circle,RectangleContainer container)
//    {
//        ArrayList<CircleItem> newItems = new ArrayList<>();
//        int lootLevel = 0;
//        double magicFind = circle.magicFind();
//        lootLevel += container.level();
//        int numberOfItems = rand.nextInt(4);
//        for(int i = 0; i < numberOfItems;i++){
//            CircleItem item = getItem(lootLevel,magicFind);
//            if(item != null){
//                item.setPosition(container.rect().x, container.rect().y);
//                newItems.add(item);
//            }
//        }
//        return newItems;
//    }
    public CircleItem getItem(int lootLevel,double magicFind){
        int itemType = rand.nextInt(36);
        switch(itemType){
            case 0: return generateBulletWeapon(lootLevel,magicFind);
            case 1: return generateBombWeapon(lootLevel, magicFind);
            case 2: return generateBounceWeapon(lootLevel, magicFind);
            case 3: return generateBoomerangWeapon(lootLevel, magicFind);
            case 4: return generateFlameWeapon(lootLevel, magicFind);
            case 5: return generateSplittingWeapon(lootLevel, magicFind);
            case 6: return generateRapierWeapon(lootLevel, magicFind);
            case 7: return generateNovaWeapon(lootLevel, magicFind);
            case 8: return generateBiteWeapon(lootLevel, magicFind);
            default: return new Gold(lootLevel,0,0);
        }
    }
    /*
    public CircleItem getItem(int lootLevel,double magicFind){
        int itemClass = lootLevel/5;
        switch(itemClass){
            case 0: return itemClass0(itemClass,magicFind);
            case 1: return itemClass1(lootLevel,magicFind);
            case 2: return itemClass2(lootLevel,magicFind);
            case 3: return itemClass3(lootLevel,magicFind);
            case 4: return itemClass4(lootLevel,magicFind);
            case 5: return itemClass5(lootLevel,magicFind);
            case 6: return itemClass6(lootLevel,magicFind);
        }
        return null;
    }
    */
    public CircleItem itemClass0(int lootLevel,double magicFind){
        int itemType = rand.nextInt(8);
        switch(itemType){
            case 0: return generateBulletWeapon(lootLevel,magicFind);
            case 1: return generateBombWeapon(lootLevel, magicFind);
            case 2: return generateBounceWeapon(lootLevel, magicFind);
            case 3: return generateBoomerangWeapon(lootLevel, magicFind);
            case 4: return generateFlameWeapon(lootLevel, magicFind);
            case 5: return generateSplittingWeapon(lootLevel, magicFind);
            case 6: return generateRapierWeapon(lootLevel, magicFind);
            case 7: return generateNovaWeapon(lootLevel, magicFind);
        }
        return null;
    }
    public CircleItem itemClass1(int lootLevel,double magicFind){
        int itemType = rand.nextInt(8);
        switch(itemType){
            case 0: return generateBulletWeapon(lootLevel,magicFind);
            case 1: return generateBombWeapon(lootLevel, magicFind);
            case 2: return generateBounceWeapon(lootLevel, magicFind);
            case 3: return generateBoomerangWeapon(lootLevel, magicFind);
            case 4: return generateFlameWeapon(lootLevel, magicFind);
            case 5: return generateSplittingWeapon(lootLevel, magicFind);
            case 6: return generateRapierWeapon(lootLevel, magicFind);
            case 7: return generateNovaWeapon(lootLevel, magicFind);
        }
        return null;
    }
    public CircleItem itemClass2(int lootLevel,double magicFind){
        return null;
    }
    public CircleItem itemClass3(int lootLevel,double magicFind){
        return null;
    }
    public CircleItem itemClass4(int lootLevel,double magicFind){
        return null;
    }
    public CircleItem itemClass5(int lootLevel,double magicFind){
        return null;
    }
    public CircleItem itemClass6(int lootLevel,double magicFind){
        return null;
    }
    public CircleWeapon generateBiteWeapon(int lootLevel,double magicFind){
        BiteWeapon bW = new BiteWeapon();
        bW.attackMoveSpeed(BiteWeapon.BASE_ATTACK_MOVESPEED + lootLevel * 5);
        bW.weaponType(rand.nextInt(3));
        bW.attackLife(BiteWeapon.BASE_ATTACK_LIFE + lootLevel * 10);
        bW.weaponColor(CircleLandUtility.randomColor());
        bW.healthBonus(BiteWeapon.BASE_HEALTH_BONUS + lootLevel * 10);
        bW.manaBonus(BiteWeapon.BASE_MANA_BONUS + lootLevel * 10);
        bW.healthRegenBonus(BiteWeapon.BASE_HEALTHREGEN_BONUS + lootLevel * 1);
        bW.manaRegenBonus(BiteWeapon.BASE_MANAREGEN_BONUS + lootLevel * 1);
        bW.attackDamageBonus(BiteWeapon.BASE_ATTACKDAMAGE_BONUS + lootLevel * 1);
        bW.magicDamageBonus(BiteWeapon.BASE_MAGICDAMAGE_BONUS + lootLevel * 1);
        bW.attackDefenseBonus(BiteWeapon.BASE_ATTACKDEFENSE_BONUS + lootLevel * 1);
        bW.magicDefenseBonus(BiteWeapon.BASE_MAGICDEFENSE_BONUS + lootLevel * 1);
        bW.precisionBonus(BiteWeapon.BASE_PRECISION_BONUS + lootLevel * 1);
        bW.attackSpeedBonus(BiteWeapon.BASE_ATTACKSPEED_BONUS + lootLevel * 1);
        bW.castSpeedBonus(BiteWeapon.BASE_CASTSPEED_BONUS + lootLevel * 1);
        bW.moveSpeedBonus(BiteWeapon.BASE_MOVESPEED_BONUS + lootLevel * 1);
        bW.bulletSize(BiteWeapon.BASE_BULLET_SIZE + lootLevel/5);
        if(Math.random() < magicFind){
            addWeaponBonus(bW);
        }
        return bW;
    }
    public CircleWeapon generateBulletWeapon(int lootLevel,double magicFind){
        BulletWeapon bW = new BulletWeapon();
        bW.attackMoveSpeed(BulletWeapon.BASE_ATTACK_MOVESPEED + lootLevel * 5);
        bW.weaponType(rand.nextInt(3));
        bW.attackLife(BulletWeapon.BASE_ATTACK_LIFE + lootLevel * 10);
        bW.weaponColor(CircleLandUtility.randomColor());
        bW.healthBonus(BulletWeapon.BASE_HEALTH_BONUS + lootLevel * 10);
        bW.manaBonus(BulletWeapon.BASE_MANA_BONUS + lootLevel * 10);
        bW.healthRegenBonus(BulletWeapon.BASE_HEALTHREGEN_BONUS + lootLevel * 1);
        bW.manaRegenBonus(BulletWeapon.BASE_MANAREGEN_BONUS + lootLevel * 1);
        bW.attackDamageBonus(BulletWeapon.BASE_ATTACKDAMAGE_BONUS + lootLevel * 1);
        bW.magicDamageBonus(BulletWeapon.BASE_MAGICDAMAGE_BONUS + lootLevel * 1);
        bW.attackDefenseBonus(BulletWeapon.BASE_ATTACKDEFENSE_BONUS + lootLevel * 1);
        bW.magicDefenseBonus(BulletWeapon.BASE_MAGICDEFENSE_BONUS + lootLevel * 1);
        bW.precisionBonus(BulletWeapon.BASE_PRECISION_BONUS + lootLevel * 1);
        bW.attackSpeedBonus(BulletWeapon.BASE_ATTACKSPEED_BONUS + lootLevel * 1);
        bW.castSpeedBonus(BulletWeapon.BASE_CASTSPEED_BONUS + lootLevel * 1);
        bW.moveSpeedBonus(BulletWeapon.BASE_MOVESPEED_BONUS + lootLevel * 1);
        bW.bulletSize(BulletWeapon.BASE_BULLET_SIZE + lootLevel/5);
        if(Math.random() < magicFind){
            addWeaponBonus(bW);
        }
        return bW;
    }
    public CircleWeapon generateBombWeapon(int lootLevel,double magicFind){
        BombWeapon bW = new BombWeapon();
        bW.attackMoveSpeed(BombWeapon.BASE_ATTACK_MOVESPEED + lootLevel * 10);
        bW.blowUpDelay(BombWeapon.BASE_BLOWUP_DELAY + lootLevel);
        bW.weaponType(rand.nextInt(3));
        bW.attackLife(BombWeapon.BASE_ATTACK_LIFE + lootLevel * 10);
        bW.weaponColor(CircleLandUtility.randomColor());
        bW.healthBonus(BombWeapon.BASE_HEALTH_BONUS + lootLevel * 10);
        bW.manaBonus(BombWeapon.BASE_MANA_BONUS + lootLevel * 10);
        bW.healthRegenBonus(BombWeapon.BASE_HEALTHREGEN_BONUS + lootLevel * 10);
        bW.manaRegenBonus(BombWeapon.BASE_MANAREGEN_BONUS + lootLevel * 10);
        bW.attackDamageBonus(BombWeapon.BASE_ATTACKDAMAGE_BONUS + lootLevel * 10);
        bW.magicDamageBonus(BombWeapon.BASE_MAGICDAMAGE_BONUS + lootLevel * 10);
        bW.attackDefenseBonus(BombWeapon.BASE_ATTACKDEFENSE_BONUS + lootLevel * 10);
        bW.magicDefenseBonus(BombWeapon.BASE_MAGICDEFENSE_BONUS + lootLevel * 10);
        bW.precisionBonus(BombWeapon.BASE_PRECISION_BONUS + lootLevel * 10);
        bW.attackSpeedBonus(BombWeapon.BASE_ATTACKSPEED_BONUS + lootLevel * 10);
        bW.castSpeedBonus(BombWeapon.BASE_CASTSPEED_BONUS + lootLevel * 10);
        bW.moveSpeedBonus(BombWeapon.BASE_MOVESPEED_BONUS + lootLevel * 10);
        bW.bulletSize(BombWeapon.BASE_BULLET_SIZE + lootLevel);
        if(Math.random() < magicFind){
            addWeaponBonus(bW);
        }
        return bW;
    }
    public CircleWeapon generateBounceWeapon(int lootLevel,double magicFind){
        BounceWeapon bW = new BounceWeapon();
        bW.bulletSize(BounceWeapon.BASE_BULLET_SIZE + lootLevel);
        bW.attackMoveSpeed(BounceWeapon.BASE_ATTACK_MOVESPEED + lootLevel * 10);
        bW.weaponType(rand.nextInt(3));
        bW.attackLife(BounceWeapon.BASE_ATTACK_LIFE + lootLevel * 10);
        bW.weaponColor(CircleLandUtility.randomColor());
        bW.healthBonus(BounceWeapon.BASE_HEALTH_BONUS + lootLevel * 10);
        bW.manaBonus(BounceWeapon.BASE_MANA_BONUS + lootLevel * 10);
        bW.healthRegenBonus(BounceWeapon.BASE_HEALTHREGEN_BONUS + lootLevel * 10);
        bW.manaRegenBonus(BounceWeapon.BASE_MANAREGEN_BONUS + lootLevel * 10);
        bW.attackDamageBonus(BounceWeapon.BASE_ATTACKDAMAGE_BONUS + lootLevel * 10);
        bW.magicDamageBonus(BounceWeapon.BASE_MAGICDAMAGE_BONUS + lootLevel * 10);
        bW.attackDefenseBonus(BounceWeapon.BASE_ATTACKDEFENSE_BONUS + lootLevel * 10);
        bW.magicDefenseBonus(BounceWeapon.BASE_MAGICDEFENSE_BONUS + lootLevel * 10);
        bW.precisionBonus(BounceWeapon.BASE_PRECISION_BONUS + lootLevel * 10);
        bW.attackSpeedBonus(BounceWeapon.BASE_ATTACKSPEED_BONUS + lootLevel * 10);
        bW.castSpeedBonus(BounceWeapon.BASE_CASTSPEED_BONUS + lootLevel * 10);
        bW.moveSpeedBonus(BounceWeapon.BASE_MOVESPEED_BONUS + lootLevel * 10);
        bW.bulletSize(BounceWeapon.BASE_BULLET_SIZE + lootLevel);
        bW.bounceRadius(BounceWeapon.BASE_BULLET_SIZE + lootLevel*10);
        
        if(Math.random() < magicFind){
            addWeaponBonus(bW);
        }
        return bW;
    }
    public CircleWeapon generateBoomerangWeapon(int lootLevel,double magicFind){
        BoomerangWeapon bW = new BoomerangWeapon();
        bW.bulletSize(BoomerangWeapon.BASE_BULLET_SIZE + lootLevel);
        bW.attackMoveSpeed(BoomerangWeapon.BASE_ATTACK_MOVESPEED + lootLevel * 10);
        bW.weaponType(rand.nextInt(3));
        bW.attackLife(BoomerangWeapon.BASE_ATTACK_LIFE + lootLevel * 10);
        bW.weaponColor(CircleLandUtility.randomColor());
        bW.healthBonus(BoomerangWeapon.BASE_HEALTH_BONUS + lootLevel * 10);
        bW.manaBonus(BoomerangWeapon.BASE_MANA_BONUS + lootLevel * 10);
        bW.healthRegenBonus(BoomerangWeapon.BASE_HEALTHREGEN_BONUS + lootLevel * 10);
        bW.manaRegenBonus(BoomerangWeapon.BASE_MANAREGEN_BONUS + lootLevel * 10);
        bW.attackDamageBonus(BoomerangWeapon.BASE_ATTACKDAMAGE_BONUS + lootLevel * 10);
        bW.magicDamageBonus(BoomerangWeapon.BASE_MAGICDAMAGE_BONUS + lootLevel * 10);
        bW.attackDefenseBonus(BoomerangWeapon.BASE_ATTACKDEFENSE_BONUS + lootLevel * 10);
        bW.magicDefenseBonus(BoomerangWeapon.BASE_MAGICDEFENSE_BONUS + lootLevel * 10);
        bW.precisionBonus(BoomerangWeapon.BASE_PRECISION_BONUS + lootLevel * 10);
        bW.attackSpeedBonus(BoomerangWeapon.BASE_ATTACKSPEED_BONUS + lootLevel * 10);
        bW.castSpeedBonus(BoomerangWeapon.BASE_CASTSPEED_BONUS + lootLevel * 10);
        bW.moveSpeedBonus(BoomerangWeapon.BASE_MOVESPEED_BONUS + lootLevel * 10);
        bW.bulletSize(BoomerangWeapon.BASE_BULLET_SIZE + lootLevel);
        
        if(Math.random() < magicFind){
            addWeaponBonus(bW);
        }
        return bW;
    }
    public CircleWeapon generateFlameWeapon(int lootLevel,double magicFind){
        FlameWeapon bW = new FlameWeapon();
        bW.bulletSize(FlameWeapon.BASE_BULLET_SIZE + lootLevel);
        bW.attackMoveSpeed(FlameWeapon.BASE_ATTACK_MOVESPEED + lootLevel * 10);
        bW.weaponType(rand.nextInt(3));
        bW.attackLife(FlameWeapon.BASE_ATTACK_LIFE + lootLevel * 10);
        bW.weaponColor(CircleLandUtility.randomColor());
        bW.healthBonus(FlameWeapon.BASE_HEALTH_BONUS + lootLevel * 10);
        bW.manaBonus(FlameWeapon.BASE_MANA_BONUS + lootLevel * 10);
        bW.healthRegenBonus(FlameWeapon.BASE_HEALTHREGEN_BONUS + lootLevel * 10);
        bW.manaRegenBonus(FlameWeapon.BASE_MANAREGEN_BONUS + lootLevel * 10);
        bW.attackDamageBonus(FlameWeapon.BASE_ATTACKDAMAGE_BONUS + lootLevel * 2);
        bW.magicDamageBonus(FlameWeapon.BASE_MAGICDAMAGE_BONUS + lootLevel * 10);
        bW.attackDefenseBonus(FlameWeapon.BASE_ATTACKDEFENSE_BONUS + lootLevel * 10);
        bW.magicDefenseBonus(FlameWeapon.BASE_MAGICDEFENSE_BONUS + lootLevel * 10);
        bW.precisionBonus(FlameWeapon.BASE_PRECISION_BONUS + lootLevel * 10);
        bW.attackSpeedBonus(FlameWeapon.BASE_ATTACKSPEED_BONUS + lootLevel * 10);
        bW.castSpeedBonus(FlameWeapon.BASE_CASTSPEED_BONUS + lootLevel * 10);
        bW.moveSpeedBonus(FlameWeapon.BASE_MOVESPEED_BONUS + lootLevel * 10);
        bW.bulletSize(FlameWeapon.BASE_BULLET_SIZE + lootLevel);
        
        if(Math.random() < magicFind){
            addWeaponBonus(bW);
        }
        return bW;
    }
    public CircleWeapon generateSplittingWeapon(int lootLevel,double magicFind){
        SplittingWeapon bW = new SplittingWeapon();
        bW.bulletSize(SplittingWeapon.BASE_BULLET_SIZE + lootLevel);
        bW.attackMoveSpeed(SplittingWeapon.BASE_ATTACK_MOVESPEED + lootLevel * 10);
        bW.weaponType(rand.nextInt(3));
        bW.attackLife(SplittingWeapon.BASE_ATTACK_LIFE + lootLevel * 10);
        bW.weaponColor(CircleLandUtility.randomColor());
        bW.healthBonus(SplittingWeapon.BASE_HEALTH_BONUS + lootLevel * 10);
        bW.manaBonus(SplittingWeapon.BASE_MANA_BONUS + lootLevel * 10);
        bW.healthRegenBonus(SplittingWeapon.BASE_HEALTHREGEN_BONUS + lootLevel * 10);
        bW.manaRegenBonus(SplittingWeapon.BASE_MANAREGEN_BONUS + lootLevel * 10);
        bW.attackDamageBonus(SplittingWeapon.BASE_ATTACKDAMAGE_BONUS + lootLevel * 10);
        bW.magicDamageBonus(SplittingWeapon.BASE_MAGICDAMAGE_BONUS + lootLevel * 10);
        bW.attackDefenseBonus(SplittingWeapon.BASE_ATTACKDEFENSE_BONUS + lootLevel * 10);
        bW.magicDefenseBonus(SplittingWeapon.BASE_MAGICDEFENSE_BONUS + lootLevel * 10);
        bW.precisionBonus(SplittingWeapon.BASE_PRECISION_BONUS + lootLevel * 10);
        bW.attackSpeedBonus(SplittingWeapon.BASE_ATTACKSPEED_BONUS + lootLevel * 10);
        bW.castSpeedBonus(SplittingWeapon.BASE_CASTSPEED_BONUS + lootLevel * 10);
        bW.moveSpeedBonus(SplittingWeapon.BASE_MOVESPEED_BONUS + lootLevel * 10);
        bW.bulletSize(SplittingWeapon.BASE_BULLET_SIZE + lootLevel);
        bW.piercing(SplittingWeapon.BASE_PIERCING + lootLevel);
        
        if(Math.random() < magicFind){
            addWeaponBonus(bW);
        }
        return bW;
    }
    public CircleWeapon generateRapierWeapon(int lootLevel,double magicFind){
        RapierWeapon bW = new RapierWeapon();
        bW.bulletSize(RapierWeapon.BASE_BULLET_SIZE + lootLevel);
        bW.attackMoveSpeed(RapierWeapon.BASE_ATTACK_MOVESPEED + lootLevel * 10);
        bW.weaponType(rand.nextInt(3));
        bW.attackLife(RapierWeapon.BASE_ATTACK_LIFE + lootLevel * 10);
        bW.weaponColor(CircleLandUtility.randomColor());
        bW.healthBonus(RapierWeapon.BASE_HEALTH_BONUS + lootLevel * 10);
        bW.manaBonus(RapierWeapon.BASE_MANA_BONUS + lootLevel * 10);
        bW.healthRegenBonus(RapierWeapon.BASE_HEALTHREGEN_BONUS + lootLevel * 10);
        bW.manaRegenBonus(RapierWeapon.BASE_MANAREGEN_BONUS + lootLevel * 10);
        bW.attackDamageBonus(RapierWeapon.BASE_ATTACKDAMAGE_BONUS + lootLevel * 10);
        bW.magicDamageBonus(RapierWeapon.BASE_MAGICDAMAGE_BONUS + lootLevel * 10);
        bW.attackDefenseBonus(RapierWeapon.BASE_ATTACKDEFENSE_BONUS + lootLevel * 10);
        bW.magicDefenseBonus(RapierWeapon.BASE_MAGICDEFENSE_BONUS + lootLevel * 10);
        bW.precisionBonus(RapierWeapon.BASE_PRECISION_BONUS + lootLevel * 10);
        bW.attackSpeedBonus(RapierWeapon.BASE_ATTACKSPEED_BONUS + lootLevel * 10);
        bW.castSpeedBonus(RapierWeapon.BASE_CASTSPEED_BONUS + lootLevel * 10);
        bW.moveSpeedBonus(RapierWeapon.BASE_MOVESPEED_BONUS + lootLevel * 10);
        bW.bulletSize(RapierWeapon.BASE_BULLET_SIZE + lootLevel);
        bW.piercing(RapierWeapon.BASE_PIERCING + lootLevel);
        
        if(Math.random() < magicFind){
            addWeaponBonus(bW);
        }
        return bW;
    }
      
    public CircleWeapon generateNovaWeapon(int lootLevel,double magicFind){
        NovaWeapon bW = new NovaWeapon();
        bW.bulletSize(NovaWeapon.BASE_BULLET_SIZE + lootLevel);
        bW.attackMoveSpeed(NovaWeapon.BASE_ATTACK_MOVESPEED + lootLevel * 10);
        bW.weaponType(rand.nextInt(3));
        bW.attackLife(NovaWeapon.BASE_ATTACK_LIFE + lootLevel * 10);
        bW.weaponColor(CircleLandUtility.randomColor());
        bW.healthBonus(NovaWeapon.BASE_HEALTH_BONUS + lootLevel * 1);
        bW.manaBonus(NovaWeapon.BASE_MANA_BONUS + lootLevel * 1);
        bW.healthRegenBonus(NovaWeapon.BASE_HEALTHREGEN_BONUS + lootLevel * 1);
        bW.manaRegenBonus(NovaWeapon.BASE_MANAREGEN_BONUS + lootLevel * 1);
        bW.attackDamageBonus(NovaWeapon.BASE_ATTACKDAMAGE_BONUS + lootLevel * 1);
        bW.magicDamageBonus(NovaWeapon.BASE_MAGICDAMAGE_BONUS + lootLevel * 1);
        bW.attackDefenseBonus(NovaWeapon.BASE_ATTACKDEFENSE_BONUS + lootLevel * 1);
        bW.magicDefenseBonus(NovaWeapon.BASE_MAGICDEFENSE_BONUS + lootLevel * 1);
        bW.precisionBonus(NovaWeapon.BASE_PRECISION_BONUS + lootLevel * 1);
        bW.attackSpeedBonus(NovaWeapon.BASE_ATTACKSPEED_BONUS + lootLevel * 1);
        bW.castSpeedBonus(NovaWeapon.BASE_CASTSPEED_BONUS + lootLevel * 1);
        bW.moveSpeedBonus(NovaWeapon.BASE_MOVESPEED_BONUS + lootLevel * 1);
        bW.bulletSize(NovaWeapon.BASE_BULLET_SIZE + lootLevel);
        bW.piercing(NovaWeapon.BASE_PIERCING + lootLevel);
        
        if(Math.random() < magicFind){
            addWeaponBonus(bW);
        }
        return bW;
    }
     
    public void addWeaponBonus(CircleWeapon weapon){
        int numberOfAffixes = 13;
        boolean prefix = false;
        boolean suffix = false;
        int choice = rand.nextInt(3);
        if(choice == 0){prefix = true;}
        if(choice == 1){suffix = true;}
        if(choice == 2){prefix = true; suffix = true;}
        int prefixNumber = rand.nextInt(numberOfAffixes);
        int suffixNumber = rand.nextInt(numberOfAffixes);
        weapon.rarity(2);
        weapon.rarityColor(Color.GREEN);
        if(prefix){
            switch(prefixNumber){ // add string to name, add stats to weapon
                case 0: weapon.name("Healthy " + weapon.name());
                weapon.healthBonus(weapon.healthBonus() + 50); break;//Healthy
                case 1: weapon.name("Wise " + weapon.name());
                weapon.manaBonus(weapon.manaBonus() + 50); break;//Magic
                case 2: weapon.name("Swift " + weapon.name());
                weapon.moveSpeedBonus(weapon.moveSpeedBonus() + 50); break;//Swift
                case 3:weapon.name("Regenerative " + weapon.name());
                weapon.healthRegenBonus(weapon.healthRegenBonus() + 5);
                weapon.manaRegenBonus(weapon.manaRegenBonus() + 5); break;//Regenerative
                case 4: weapon.name("Giant " + weapon.name());
                weapon.bulletSize(weapon.bulletSize() + 10); break;//Giant - bigger bullets
                case 5: weapon.name("Zippy " + weapon.name());
                weapon.attackMoveSpeed(weapon.attackMoveSpeed() + 100); break;//Zippy - faster bullets
                case 6: weapon.name("Frenzied " + weapon.name());
                weapon.attackSpeedBonus(weapon.attackSpeedBonus() - 100); break;//Frenzied - faster attackspeed
                case 7: weapon.name("Brutal " + weapon.name());
                weapon.attackDamageBonus(weapon.attackDamageBonus() + 25); break;//Brutal - attack damage
                case 8: weapon.name("Empowered " + weapon.name());
                weapon.magicDamageBonus(weapon.magicDamageBonus() + 25); break;//Empowered - magic damage
                case 9: weapon.name("Tanky " + weapon.name());
                weapon.attackDefenseBonus(weapon.attackDefenseBonus() + 25); break;//Tanky - att armor
                case 10: weapon.name("Absorbing " + weapon.name());
                weapon.magicDefenseBonus(weapon.magicDefenseBonus() + 25);break;//Absorbing mag armor
                case 11: weapon.name("Caster " + weapon.name());
                weapon.castSpeedBonus(weapon.castSpeedBonus() + 25);break;//Caster cast speed
                case 12: weapon.name("Piercing " + weapon.name());
                weapon.piercing(weapon.piercing() + 2);break;//Piercing 
            }
        }
        if(suffix){
            switch(suffixNumber){ // add string to name, add stats to weapon
                case 0: weapon.name(weapon.name() + " of Health");
                weapon.healthBonus(weapon.healthBonus() + 50); break;//Healthy
                case 1: weapon.name(weapon.name() + " of Wisdom");
                weapon.manaBonus(weapon.manaBonus() + 50); break;//Magic
                case 2: weapon.name(weapon.name() + " of Haste");
                weapon.moveSpeedBonus(weapon.moveSpeedBonus() + 50); break;//Swift
                case 3:weapon.name(weapon.name() + " of Regeneration");
                weapon.healthRegenBonus(weapon.healthRegenBonus() + 5);
                weapon.manaRegenBonus(weapon.manaRegenBonus() + 5); break;//Regenerative
                case 4: weapon.name(weapon.name() + " of Enormity");
                weapon.bulletSize(weapon.bulletSize() + 10); break;//Giant - bigger bullets
                case 5: weapon.name(weapon.name() + " of Flying");
                weapon.attackMoveSpeed(weapon.attackMoveSpeed() + 100); break;//Zippy - faster bullets
                case 6: weapon.name(weapon.name() + " of QuickShot");
                weapon.attackSpeedBonus(weapon.attackSpeedBonus() - 100); break;//Frenzied - faster attackspeed
                case 7: weapon.name(weapon.name() + " of Destruction");
                weapon.attackDamageBonus(weapon.attackDamageBonus() + 25); break;//Brutal - attack damage
                case 8: weapon.name(weapon.name() + " of Sorcery");
                weapon.magicDamageBonus(weapon.magicDamageBonus() + 25); break;//Empowered - magic damage
                case 9: weapon.name(weapon.name() + " of Invincibility");
                weapon.attackDefenseBonus(weapon.attackDefenseBonus() + 25); break;//Tanky - att armor
                case 10: weapon.name(weapon.name() + " of SpellShield");
                weapon.magicDefenseBonus(weapon.magicDefenseBonus() + 25);break;//Absorbing mag armor
                case 11: weapon.name(weapon.name() + " of the Magi");
                weapon.castSpeedBonus(weapon.castSpeedBonus() + 25);break;//Caster cast speed
                case 12: weapon.name(weapon.name() + " of Puncturing");
                weapon.piercing(weapon.piercing() + 2);break;//Piercing 
            }
        }
    }

}
