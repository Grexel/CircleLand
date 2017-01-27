/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import static circleland.CircleWeapon.rand;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Jeff
 */
public abstract class CircleSkill extends CircleItem{
    protected int manaCost;
    public int manaCost(){return manaCost;}
    public void manaCost(int m){manaCost = m;}
    protected int skillLevel;
    public int skillLevel(){return skillLevel;}
    public void skillLevel(int m){skillLevel = m;}
    //whether skill is active or passive
    protected boolean isActive;
    public boolean isActive(){return isActive;}
    public void isActive(boolean m){isActive = m;}
    protected double castSpeed;
    public void castSpeed(double m){castSpeed = m;}
    public double castSpeed(){ return castSpeed;}
    protected int castLife;
    public void castLife(int m){castLife = m;}
    public int castLife(){ return castLife;}
    
    public CircleSkill(){
        manaCost = 15;
        skillLevel = 0;
        castSpeed = 500;
        castLife = 500;
        isActive = true;
    }

    /**
     *
     * @param owner
     * @param attackList
     */
    public abstract void attack(CircleEntity owner, ArrayList<CircleAttack> attackList);
    public int randomizeDamage(CircleEntity entity){
        //get range, precision curves min up towards max
        int min = entity.minMagicDamage();
        int max = entity.maxMagicDamage();
        return(max-min <= 0)? min : rand.nextInt(max-min + 1) + min;
    }
    public abstract void addBonus(CircleEntity circle);
    @Override
    public void draw(Graphics2D graphics) {
    }

    @Override
    public void draw(Graphics2D graphics, int x, int y) {
    }
    @Override
    public void drawDetails(Graphics2D graphics,int x, int y){
        graphics.setColor(Color.BLACK);
        graphics.fillRect(x-200, y - 200, 200, 200);
        graphics.setColor(Color.WHITE);
        graphics.drawString(name(), x - 190, y - 190);
        graphics.drawString("ManaCost: " + manaCost(), x - 190, y - 170);
        graphics.drawString("SkillLevel: " + skillLevel(), x-190, y-160);
    }
}
