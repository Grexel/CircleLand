/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Skills.WarriorSkills;

import circleland.Attacks.WarriorAttacks.StunSkillAttack;
import circleland.Attacks.WarriorAttacks.TauntSkillAttack;
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
public class TauntSkill extends CircleSkill {
    public static final Color CENTER_COLOR = Color.RED; //item type color
    private int tauntSize;
    public TauntSkill()
    {
        super();
        name = "Taunt";
        tauntSize = 200;
        skillLevel = 10;
    }
    public void attack(CircleEntity owner, ArrayList<CircleAttack> attacks)
    {
        owner.mana(owner.mana() - manaCost);
        double velX = owner.moveSpeed() * 2 * Math.cos(owner.heading());
        double velY = owner.moveSpeed() * 2 * Math.sin(owner.heading());
        int dmg = randomizeDamage(owner);
        TauntSkillAttack bA = new TauntSkillAttack(owner,castLife,dmg,
            owner.position().x,owner.position().y,velX,velY,tauntSize,Color.PINK);
        attacks.add(bA);
    }
    public void draw(Graphics2D graphics, int x, int y)
    {
        graphics.setColor(Color.RED);
        graphics.fillOval((int)(x - (size+4)/ 2), (int)(y - (size+4) / 2),
            (int)size+4, (int)size+4);
        graphics.setColor(Color.PINK);
        graphics.fillOval((int)(x - 8), (int)(y - 8),
            (int)16, (int)16);
    }

    @Override
    public void addBonus(CircleEntity circle) {
        circle.minMagicDamage(1);
        circle.maxMagicDamage(this.skillLevel);
    }
    
}
