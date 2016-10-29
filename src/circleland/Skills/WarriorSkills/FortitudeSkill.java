/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Skills.WarriorSkills;

import circleland.CircleEntity;
import circleland.CirclePassiveSkill;
import circleland.CircleSkill;
import static circleland.Skills.WarriorSkills.CorpseLootSkill.CENTER_COLOR;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Jeff
 */
public class FortitudeSkill extends CirclePassiveSkill{
    
    public FortitudeSkill(){
        super();
        isActive = false;
        name = "Fortitude";
    }

    @Override
    public void addBonus(CircleEntity owner) {
        owner.maxHealth(owner.maxHealth() + (10 + skillLevel*20));
    }
    public void draw(Graphics2D graphics, int x, int y)
    {
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(x-(int)size/2, y, (int)size, (int)size/2);
        graphics.fillOval((int)(x - size / 2), (int)(y - size / 2),
            (int)size, (int)size);
        graphics.setColor(Color.RED);
        graphics.fillOval((int)(x - 4), (int)(y - 4),
            (int)8, (int)8);
        graphics.drawLine(x, y-(int)size/4, x, y+(int)size/4);
        graphics.drawLine(x+(int)size/4, y, x-(int)size/4, y);
    }
}
