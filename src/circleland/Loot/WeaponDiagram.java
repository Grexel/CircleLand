/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Loot;

import circleland.CircleItem;
import circleland.CircleWeapon;
import circleland.Weapons.*;
import java.awt.Color;

/**
 *
 * @author Jeff
 */
public class WeaponDiagram extends ItemDiagram{

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
        super(itemLevel, name);
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
        CircleWeapon weapon;
        
        if(weaponType.equalsIgnoreCase("Rapier")){
             weapon = new RapierWeapon();
        }
        else if(weaponType.equalsIgnoreCase("Sword")){
             weapon = new SwordWeapon();
        }
        else if(weaponType.equalsIgnoreCase("Bite")){
             weapon = new BiteWeapon();
        }
        else if(weaponType.equalsIgnoreCase("Bullet")){
             weapon = new BulletWeapon();
        }
        else if(weaponType.equalsIgnoreCase("Splitter")){
             weapon = new SplittingWeapon();
        }
        else if(weaponType.equalsIgnoreCase("Nova")){
             weapon = new NovaWeapon();
        }
        else if(weaponType.equalsIgnoreCase("Bounce")){
             weapon = new BounceWeapon();
        }
        else if(weaponType.equalsIgnoreCase("Boomerang")){
             weapon = new BoomerangWeapon();
        }
        else {
             weapon = new BulletWeapon();
        }
            weapon.itemLevel(getItemLevel());
            weapon.name(getName());
            weapon.bulletSize(size);
            weapon.attackMoveSpeed(moveSpeed);
            weapon.attackSpeed(attackSpeed);
            weapon.attackLife(attackLife);
            weapon.specialColor(color);
            weapon.minDamage(minDamage);
            weapon.maxDamage(maxDamage);
            weapon.piercing(pierce);
            return weapon;
    }
}
