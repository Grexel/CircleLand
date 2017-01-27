/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Skills.NecromancerSkills;

import circleland.Attacks.WarriorAttacks.BashSkillAttack;
import circleland.Attacks.NecromancerAttacks.BoneBlastSkillAttack;
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

public class BoneBlastSkill extends CircleSkill{
    public static final Color CENTER_COLOR = Color.PINK; //item type color
    
    public BoneBlastSkill()
    {
        super();
        name = "Bone Blast";
        skillLevel = 25;
        
    }
    public void attack(CircleEntity owner, ArrayList<CircleAttack> attacks)
    {
        owner.mana(owner.mana() - 5 - skillLevel*2);
        double startingHeading = owner.heading()-Math.PI/8;// - Math.PI/2;//center - max arc = Math.PI
        double headingGap = Math.PI/4/(skillLevel+1);
        int dmg = randomizeDamage(owner);
        for(int i = 1; i <= skillLevel; i++)
        {
            double velX = owner.moveSpeed() * 2 * Math.cos(startingHeading + headingGap*i);
            double velY = owner.moveSpeed() * 2 * Math.sin(startingHeading + headingGap*i);
            BoneBlastSkillAttack bA = new BoneBlastSkillAttack(owner,castLife,dmg,1,
            owner.position().x,owner.position().y,velX,velY,8,Color.GRAY);
            attacks.add(bA);
        }
        
    }
    @Override
    public void addBonus(CircleEntity circle) {
        circle.minMagicDamage(this.skillLevel);
        circle.maxMagicDamage(this.skillLevel*2);
    }
    
    public void draw(Graphics2D graphics, int x, int y)
    {
        graphics.setColor(rarityColor);
        graphics.fillRect(x-(int)size/2, y-(int)size/2, (int)size, (int)size/2);
        graphics.fillOval((int)(x - size / 2), (int)(y - size / 2),
            (int)size, (int)size);
        graphics.setColor(CENTER_COLOR);
        graphics.fillOval((int)(x - 4), (int)(y - 4),
            (int)8, (int)8);
        
    }
    
}
