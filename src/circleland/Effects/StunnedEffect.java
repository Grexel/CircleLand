/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Effects;

import circleland.CircleEffect;
import circleland.CircleEntity;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Jeff
 */
public class StunnedEffect extends CircleEffect{
    private static final Color STUNCOLOR = new Color(0x33,0x66,0x66,0x66);
    public StunnedEffect(int lf){
        super(lf);
    }
    public void update(long deltaTime, CircleEntity owner)
    {
        super.update(deltaTime,owner);
        owner.attackDisabled(true);
        owner.moveDisabled(true);
        owner.castDisabled(true);
    }
    @Override
    public void draw(Graphics2D graphics, CircleEntity owner){
        graphics.setColor(STUNCOLOR);
        graphics.fillOval(
            (int)(owner.position().x -owner.size()/2- 5), (int)(owner.position().y-owner.size()/2 - 5), (int)owner.size()+10, (int)owner.size() + 10);
    }
}
