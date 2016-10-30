/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jeff
 */
public class CircleWeapon extends CircleEquipment
{   public static final Random rand = new Random();
    public static final double ATTACK_MOVESPEED_CAP = 500;
  
    protected String attackSound;
    protected int weaponType; // 0 warrior,1 archer,2 mage
    protected double attackMoveSpeed;
    protected int attackLife;
    protected int piercing;
    protected Color weaponColor;
    protected int bulletSize;
    protected int minDamage;
    protected int maxDamage;
    
    public CircleWeapon()
    {
        minDamage = 1;
        maxDamage = 1;
        weaponType = 0;
        attackMoveSpeed = 200;
        attackLife = 1000;
        piercing = 1;
        weaponColor = new Color(255,0,0);
        
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
        graphics.drawString("Damage: " + minDamage + " - " + maxDamage, x-190, y-150);
        int yOff = -140;
        for(CircleAffix affix : affixes){
            graphics.drawString(affix.getDetails(), x-190, y+yOff);
            yOff+=10;
        }
    }
    
    public int randomizeDamage(CircleEntity entity){
        //get range, precision curves min up towards max
        int min = entity.minDamage();
        int max = entity.maxDamage();
        return(max-min <= 0)? min: rand.nextInt(max-min) + min;
    }
    @Override
    public void addBonus(CircleEntity entity) {
       entity.minDamage(entity.minDamage() + minDamage);
       entity.maxDamage(entity.maxDamage() + maxDamage);
    }
}
