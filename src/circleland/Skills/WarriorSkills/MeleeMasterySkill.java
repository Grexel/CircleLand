/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Skills.WarriorSkills;

import circleland.CircleEntity;
import circleland.CirclePassiveSkill;
import circleland.CircleSkill;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Jeff
 */
public class MeleeMasterySkill extends CirclePassiveSkill{
    
    public MeleeMasterySkill(){
        super();
        isActive = false;
        name = "MeleeMastery";
        skillLevel = 10;
    }

    @Override
    public void addBonus(CircleEntity owner) {
        if(owner.equippedWeapon() != null){
            if(owner.equippedWeapon().weaponType() == 0){
                owner.attackDamage((int)(owner.attackDamage() * (1.05 + .05 * skillLevel)));
            }
        }
    }
    public void draw(Graphics2D graphics, int x, int y)
    {
        graphics.setColor(Color.ORANGE);
        graphics.setStroke(new BasicStroke(5));
        graphics.drawLine(x+(int)size/2, y-(int)size/2, x-(int)size/2, y+(int)size/2);
        graphics.setColor(Color.ORANGE);
        graphics.drawLine(x-(int)size/2, y, x, y+(int)size/2);
        graphics.setStroke(new BasicStroke(3));
        graphics.setColor(Color.BLACK);
        graphics.drawLine(x, y-(int)size/4, x, y+(int)size/4);
        graphics.drawLine(x+(int)size/4, y, x-(int)size/4, y);
        graphics.setStroke(new BasicStroke(2));
        graphics.setColor(Color.RED);
        graphics.drawLine(x, y-(int)size/4, x, y+(int)size/4);
        graphics.drawLine(x+(int)size/4, y, x-(int)size/4, y);
        //
    }
}

