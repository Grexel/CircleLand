/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Skills.NecromancerSkills;

import circleland.Attacks.NecromancerAttacks.BoneBlastSkillAttack;
import circleland.Attacks.NecromancerAttacks.BoneWallSkillAttack;
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

public class BoneWallSkill extends CircleSkill{
    public static final Color CENTER_COLOR = Color.PINK; //item type color
    
    public BoneWallSkill()
    {
        super();
        name = "Bone Wall";
        
    }
    public void attack(CircleEntity owner, ArrayList<CircleAttack> attacks)
    {
        owner.mana(owner.mana() - 5 - skillLevel*2);
        double startingHeading = owner.heading();//center - max arc = Math.PI
        double velX = owner.moveSpeed() * 2 * Math.cos(owner.heading());
        double velY = owner.moveSpeed() * 2 * Math.sin(owner.heading());
        BoneWallSkillAttack bA = new BoneWallSkillAttack(owner,castLife,skillLevel,
            owner.position().x,owner.position().y,velX,velY,8,Color.GRAY);
        
        attacks.add(bA);
    }
    @Override
    public void addBonus(CircleEntity circle) {
        circle.minMagicDamage(1);
        circle.maxMagicDamage(this.skillLevel);
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