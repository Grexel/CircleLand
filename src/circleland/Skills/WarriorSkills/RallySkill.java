/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Skills.WarriorSkills;

import circleland.Attacks.WarriorAttacks.RallySkillAttack;
import circleland.Attacks.WarriorAttacks.TauntSkillAttack;
import circleland.CircleAttack;
import circleland.CircleEntity;
import circleland.CircleSkill;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Jeff
 */
public class RallySkill extends CircleSkill {
    public static final Color CENTER_COLOR = Color.RED; //item type color
    private int tauntSize;
    private int duration;
    private int bonusHealth;
    private int bonusAttack;
    private int bonusDefense;
    public RallySkill()
    {
        super();
        name = "Rally";
        tauntSize = 200;
        skillLevel = 10;
    }
    public void attack(CircleEntity owner, ArrayList<CircleAttack> attacks)
    {
        owner.mana(owner.mana() - manaCost);
        double velX = owner.moveSpeed() * 2 * Math.cos(owner.heading());
        double velY = owner.moveSpeed() * 2 * Math.sin(owner.heading());
        int dmg = randomizeDamage(owner);
        RallySkillAttack bA = new RallySkillAttack(owner,castLife,dmg,
            owner.position().x,owner.position().y,velX,velY,tauntSize,Color.PINK,skillLevel);
        attacks.add(bA);
    }
    public void draw(Graphics2D graphics, int x, int y)
    {
        graphics.setStroke(new BasicStroke(4));
        graphics.setColor(Color.RED);
        graphics.fillOval((int)(x - (size+4)/ 2), (int)(y - (size+4) / 2),
            (int)size+4, (int)size+4);
        graphics.setColor(Color.BLACK);
        graphics.drawLine(x, y-(int)size/4, x, y+(int)size/4);
        graphics.drawLine(x+(int)size/4, y, x-(int)size/4, y);
    }
    @Override
    public void addBonus(CircleEntity circle) {
        circle.minMagicDamage(1);
        circle.maxMagicDamage(this.skillLevel);
    }
    
    
}
