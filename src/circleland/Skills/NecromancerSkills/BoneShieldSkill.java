/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Skills.NecromancerSkills;

import circleland.Attacks.NecromancerAttacks.BoneShieldSkillAttack;
import circleland.Attacks.WarriorAttacks.TauntSkillAttack;
import circleland.CircleAttack;
import circleland.CircleEntity;
import circleland.CircleSkill;
import static circleland.Skills.WarriorSkills.BashSkill.CENTER_COLOR;
import static circleland.Skills.NecromancerSkills.SummonSkeletonSkill.CENTER_COLOR;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Jeff
 */

public class BoneShieldSkill extends CircleSkill {
    public static final Color CENTER_COLOR = Color.RED; //item type color
    private int shieldSize;
    private ArrayList<CircleAttack> boneShields;
    public BoneShieldSkill()
    {
        super();
        boneShields = new ArrayList<>();
        name = "BoneShield";
        shieldSize = 100;
    }
    public void attack(CircleEntity owner, ArrayList<CircleAttack> attacks)
    {
        if(boneShields.size() > 0){
            CircleAttack bS = boneShields.remove(0);
            bS.life(0);
        }
        owner.mana(owner.mana() - manaCost);
        double velX = owner.moveSpeed() * 2 * Math.cos(owner.heading());
        double velY = owner.moveSpeed() * 2 * Math.sin(owner.heading());
        double damageReduction = 0.20 + skillLevel * 0.03;
        castLife = 60000 + skillLevel*10000;
        int dmg = randomizeDamage(owner);
        BoneShieldSkillAttack bA = new BoneShieldSkillAttack(owner,castLife,dmg,
            owner.position().x,owner.position().y,velX,velY,shieldSize,Color.BLACK,damageReduction);
        attacks.add(bA);
        boneShields.add(bA);
    }
    @Override
    public void addBonus(CircleEntity circle) {
        circle.minMagicDamage(1);
        circle.maxMagicDamage(this.skillLevel);
    }
    public void draw(Graphics2D graphics, int x, int y)
    {
        //shield
        graphics.setColor(rarityColor);
        graphics.fillRect(x-(int)size/2, y-(int)size/2, (int)size, (int)size/2);
        graphics.fillOval((int)(x - size / 2), (int)(y - size / 2),
            (int)size, (int)size);
        graphics.setColor(CENTER_COLOR);
        graphics.fillOval((int)(x - 4), (int)(y - 4),
            (int)8, (int)8);
        graphics.setColor(Color.WHITE);
        //skeleton
        graphics.setStroke(new BasicStroke(3));
        graphics.drawLine(x+(int)size/2, y-(int)size/2, x-(int)size/2, y+(int)size/2);
        graphics.setColor(Color.WHITE);
        graphics.drawLine(x-(int)size/2, y-(int)size/2, x+(int)size/2, y+(int)size/2);
        graphics.setColor(Color.WHITE);
        graphics.fillOval((int)(x - size / 2), (int)(y - size / 2),
            (int)size, (int)size);
        graphics.setColor(CENTER_COLOR);
        graphics.fillOval((int)(x - 8), (int)(y-4),
            (int)6, (int)6);
        graphics.fillOval((int)(x+2), (int)(y-4),
            (int)6, (int)6);
        graphics.setStroke(new BasicStroke(3));
        graphics.drawLine(x+(int)size/4, y+(int)size/4, x-(int)size/4, y+(int)size/4);
    }
    
}
