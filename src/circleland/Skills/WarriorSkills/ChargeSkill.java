/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Skills.WarriorSkills;

import circleland.*;
import circleland.Attacks.WarriorAttacks.ChargeSkillAttack;
import circleland.Attacks.RapierAttack;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Jeff
 */
public class ChargeSkill extends CircleSkill{
    public static final Color CENTER_COLOR = Color.BLUE; //item type color
    
    public ChargeSkill()
    {
        super();
        name = "Charge";
        castLife = 250;
        castSpeed = 500;
        skillLevel = 10;
    }
    public void attack(CircleEntity owner, ArrayList<CircleAttack> attacks)
    {
        owner.mana(owner.mana() - manaCost);
        double velX = owner.moveSpeed() * 3 * Math.cos(owner.heading());
        double velY = owner.moveSpeed() * 3 * Math.sin(owner.heading());
        int dmg = randomizeDamage(owner);
        ChargeSkillAttack bA = new ChargeSkillAttack(owner,castLife,dmg,
            owner.position().x,owner.position().y,velX,velY,(int)(owner.size() + 8),Color.GRAY);
        
        attacks.add(bA);
    }
    
    @Override
    public void addBonus(CircleEntity circle) {
        circle.minMagicDamage(1);
        circle.maxMagicDamage(this.skillLevel);
    }
    public void draw(Graphics2D graphics, int x, int y)
    {
        graphics.setColor(CENTER_COLOR);
        graphics.fillOval((int)(x + size/8), (int)(y - size/4),
            (int)14, (int)14);
        graphics.drawLine(x, y, x-(int)size/2, y);
        graphics.drawLine(x+(int)size/4, y-(int)size/4, x-(int)size/4, y-(int)size/4);
        graphics.drawLine(x+(int)size/4, y+(int)size/4, x-(int)size/4, y+(int)size/4);
    }
}
