/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Skills.WarriorSkills;

import circleland.Attacks.WarriorAttacks.BashSkillAttack;
import circleland.Attacks.WarriorAttacks.StunSkillAttack;
import circleland.CircleAttack;
import circleland.CircleEntity;
import circleland.CircleSkill;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Jeff
 */
public class StunSkill extends CircleSkill {
    public static final Color CENTER_COLOR = Color.RED; //item type color
    
    public StunSkill()
    {
        super();
        name = "Stun";
    }
    public void attack(CircleEntity owner, ArrayList<CircleAttack> attacks)
    {
        owner.mana(owner.mana() - manaCost);
        double velX = owner.moveSpeed() * 2 * Math.cos(owner.heading());
        double velY = owner.moveSpeed() * 2 * Math.sin(owner.heading());
        StunSkillAttack bA = new StunSkillAttack(owner,(int)owner.castSpeed(),owner.magicDamage(),
            owner.position().x,owner.position().y,velX,velY,(int)(owner.size() + 8),Color.GRAY);
        
        attacks.add(bA);
    }
    public void draw(Graphics2D graphics, int x, int y)
    {
        graphics.setColor(Color.WHITE);
        graphics.fillOval((int)(x - (size+4)/ 2), (int)(y - (size+4) / 2),
            (int)size+4, (int)size+4);
        graphics.setColor(CENTER_COLOR);
        graphics.fillOval((int)(x - 4), (int)(y - 4),
            (int)8, (int)8);
        graphics.setColor(Color.BLACK);
        graphics.drawLine(x+(int)size/2, y-(int)size/2, x-(int)size/2, y+(int)size/2);
    }
    
}
