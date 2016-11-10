/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Loot;

import circleland.CircleItem;
import circleland.Weapons.*;
import java.awt.Color;

/**
 *
 * @author Jeff
 */
public class WeaponDiagram extends ItemDiagram{
    String name;
    int itemLevel;
    String weaponType;
    int size;
    int moveSpeed;
    int attackSpeed;
    int attackLife;
    Color color;
    int minDamage;
    int maxDamage;
    int pierce;

    public WeaponDiagram(String name, int itemLevel, String weaponType, int size, int moveSpeed, int attackSpeed, int attackLife, Color color, int minDamage, int maxDamage, int pierce) {
        this.name = name;
        this.itemLevel = itemLevel;
        this.weaponType = weaponType;
        this.size = size;
        this.moveSpeed = moveSpeed;
        this.attackSpeed = attackSpeed;
        this.attackLife = attackLife;
        this.color = color;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.pierce = pierce;
    }
    
    @Override
    public CircleItem buildItem() {
        if(weaponType.equalsIgnoreCase("Rapier")){
            RapierWeapon rapier = new RapierWeapon();
            rapier.itemLevel(itemLevel);
            rapier.name(name);
            rapier.bulletSize(size);
            rapier.attackMoveSpeed(moveSpeed);
            rapier.attackSpeed(attackSpeed);
            rapier.attackLife(attackLife);
            rapier.itemColor(color);
            rapier.minDamage(minDamage);
            rapier.maxDamage(maxDamage);
            rapier.piercing(pierce);
            return rapier;
        }
        return null;
    }
}
