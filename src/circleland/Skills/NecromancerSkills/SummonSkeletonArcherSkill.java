/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Skills.NecromancerSkills;

import circleland.Attacks.NecromancerAttacks.SummonSkeletonArcherSkillAttack;
import circleland.Attacks.NecromancerAttacks.SummonSkeletonSkillAttack;
import circleland.CircleAttack;
import circleland.CircleEntity;
import circleland.CircleSkill;
import circleland.CircleSummoned;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Jeff
 */

public class SummonSkeletonArcherSkill extends CircleSkill{
    public static final Color CENTER_COLOR = Color.BLACK; //item type color
    private ArrayList<CircleSummoned> spawnedSkeletons;
    public SummonSkeletonArcherSkill()
    {
        super();
        skillLevel = 10;
        spawnedSkeletons = new ArrayList<>();
        name = "Summon Skeleton Archer";
    }
    public void attack(CircleEntity owner, ArrayList<CircleAttack> attacks)
    {
        owner.mana(owner.mana() - manaCost);
        double velX = owner.moveSpeed() * 2 * Math.cos(owner.heading());
        double velY = owner.moveSpeed() * 2 * Math.sin(owner.heading());
        SummonSkeletonArcherSkillAttack bA = new SummonSkeletonArcherSkillAttack(owner,castLife,skillLevel,
            owner.position().x,owner.position().y,velX,velY,(int)(owner.size() + 8),Color.GRAY,spawnedSkeletons);
        
        attacks.add(bA);
    }
    public void draw(Graphics2D graphics, int x, int y)
    {
        graphics.setColor(Color.WHITE);
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