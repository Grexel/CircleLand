/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Jeff
 */
public class CircleWeapon extends CircleItem
{
    public static final double ATTACK_MOVESPEED_CAP = 500;
  
    protected String attackSound;
    protected int weaponType; // 0 warrior,1 archer,2 mage
    protected double attackMoveSpeed;
    protected int attackLife;
    protected int piercing;
    protected Color weaponColor;
    
    protected int  healthBonus, manaBonus, healthRegenBonus, manaRegenBonus, 
            attackDamageBonus, magicDamageBonus, attackDefenseBonus,
            magicDefenseBonus,  precisionBonus, attackSpeedBonus,
            castSpeedBonus, moveSpeedBonus;
    protected int bulletSize;
    
    public CircleWeapon()
    {
        weaponType = 0;
        attackMoveSpeed = 200;
        attackLife = 1000;
        piercing = 1;
        weaponColor = new Color(255,0,0);
        healthBonus = 0;
        healthRegenBonus = 0;
        manaBonus =0;
        manaRegenBonus = 0;
        attackDamageBonus = 0;
        magicDamageBonus = 0;
        attackDefenseBonus = 0;
        magicDefenseBonus = 0;
        precisionBonus = 0;
        attackSpeedBonus = 0;
        castSpeedBonus = 0;
        moveSpeedBonus = 0;
        
        bulletSize = 10;
        attackSound = "sounds/Shoot1.wav";
    }
    public void bulletSize(int m){bulletSize = m;}
    public int bulletSize(){ return bulletSize;}
    public void weaponType(int m){weaponType = m;}
    public int weaponType(){ return weaponType;}
    public void attackMoveSpeed(double m){attackMoveSpeed = m;}
    public double attackMoveSpeed(){ return attackMoveSpeed;}
    public void attackLife(int m){attackLife = m;}
    public int attackLife(){ return attackLife;}
    public void piercing(int m){piercing = m;}
    public int piercing(){ return piercing;}
    public void weaponColor(Color m){weaponColor = m;}
    public Color weaponColor(){ return weaponColor;}
    public void healthBonus(int m){healthBonus = m;}
    public int healthBonus(){ return healthBonus;}
    public void healthRegenBonus(int m){healthRegenBonus = m;}
    public int healthRegenBonus(){ return healthRegenBonus;}
    public void manaBonus(int m){manaBonus = m;}
    public int manaBonus(){ return manaBonus;}
    public void manaRegenBonus(int m){manaRegenBonus = m;}
    public int manaRegenBonus(){ return manaRegenBonus;}
    public void attackDamageBonus(int m){attackDamageBonus = m;}
    public int attackDamageBonus(){ return attackDamageBonus;}
    public void magicDamageBonus(int m){magicDamageBonus = m;}
    public int magicDamageBonus(){ return magicDamageBonus;}
    public void attackDefenseBonus(int m){attackDefenseBonus = m;}
    public int attackDefenseBonus(){ return attackDefenseBonus;}
    public void magicDefenseBonus(int m){magicDefenseBonus = m;}
    public int magicDefenseBonus(){ return magicDefenseBonus;}
    public void precisionBonus(int m){precisionBonus = m;}
    public int precisionBonus(){ return precisionBonus;}
    public void attackSpeedBonus(int m){attackSpeedBonus = m;}
    public int attackSpeedBonus(){ return attackSpeedBonus;}
    public void castSpeedBonus(int m){castSpeedBonus = m;}
    public int castSpeedBonus(){ return castSpeedBonus;}
    public void moveSpeedBonus(int m){moveSpeedBonus = m;}
    public int moveSpeedBonus(){ return moveSpeedBonus;}
    public String attackSound(){return attackSound;}
    public void attackSound(String l){attackSound = l;}
    public void attack(CircleEntity owner, ArrayList<CircleAttack> attacks)
    {
        SoundManager.queueSound(attackSound);
    }
    public void draw(Graphics2D graphics)
    {
        
    }
    public void draw(Graphics2D graphics,int x, int y)
    {
        
    }
    public void drawDetails(Graphics2D graphics,int x, int y){
        graphics.setColor(Color.BLACK);
        graphics.fillRect(x-200, y - 200, 200, 200);
        graphics.setColor(Color.WHITE);
        graphics.drawString(name(), x - 190, y - 190);
        graphics.drawString("WeaponType: " + weaponType(), x - 190, y - 170);
        graphics.drawString("Piercing: " + piercing(), x-190, y-160);
        graphics.drawString("Health: " + healthBonus(), x-190, y-150);
        graphics.drawString("HealthRegen: " + healthRegenBonus(), x-190, y-140);
        graphics.drawString("Mana: " + manaBonus(), x-190, y-130);
        graphics.drawString("ManaRegen: " + manaRegenBonus(), x-190, y-120);
        graphics.drawString("AttackDamage: " + attackDamageBonus(), x-190, y-110);
        graphics.drawString("MagicDamage: " + magicDamageBonus(), x-190, y-100);
        graphics.drawString("AttackDefense: " + attackDefenseBonus(), x-190, y-90);
        graphics.drawString("MagicDefense: " + magicDefenseBonus(), x-190, y-80);
        graphics.drawString("Precision: " + precisionBonus(), x-190, y-70);
        graphics.drawString("AttackSpeed: " + attackSpeedBonus(), x-190, y-60);
        graphics.drawString("CastSpeed: " + castSpeedBonus(), x-190, y-50);
        graphics.drawString("MoveSpeed: " + moveSpeedBonus(), x-190, y-40);
    }
}
