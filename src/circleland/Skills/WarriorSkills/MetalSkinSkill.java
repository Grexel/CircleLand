/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Skills.WarriorSkills;

import circleland.CircleEntity;
import circleland.CirclePassiveSkill;
import circleland.CircleSkill;
import static circleland.Skills.WarriorSkills.BashSkill.CENTER_COLOR;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Jeff
 */

public class MetalSkinSkill extends CirclePassiveSkill{
    
    public MetalSkinSkill(){
        super();
        isActive = false;
        name = "MetalSkin";
    }

    @Override
    public void addBonus(CircleEntity owner) {
        owner.attackDefense(owner.attackDefense() + (10 + skillLevel*20));
    }
    public void draw(Graphics2D graphics, int x, int y)
    {
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(x-(int)size/2, y-(int)size/2, (int)size, (int)size/2);
        graphics.fillOval((int)(x - size / 2), (int)(y - size / 2),
            (int)size, (int)size);
        graphics.setStroke(new BasicStroke(4));
        graphics.setColor(Color.BLACK);
        graphics.drawLine(x, y-(int)size/4, x, y+(int)size/4);
        graphics.drawLine(x+(int)size/4, y, x-(int)size/4, y);
    }
}
