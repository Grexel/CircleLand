/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Effects;

import circleland.CircleEffect;
import circleland.CircleEntity;

/**
 *
 * @author Jeff
 */
public class SlowEffect extends CircleEffect{
    private double slowMultiplier;
    public SlowEffect(int lf,double slowM){
        super(lf);
        slowMultiplier = slowM;
    }
    public void update(long deltaTime, CircleEntity owner)
    {
        super.update(deltaTime,owner);
        owner.moveSpeed(owner.moveSpeed() * slowMultiplier);
    }
}
