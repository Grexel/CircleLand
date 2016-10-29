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
public class FearEffect extends CircleEffect{
    public FearEffect(int lf){
        super(lf);
    }
    public void update(long deltaTime, CircleEntity owner)
    {
        super.update(deltaTime,owner);
        owner.isFeared(true);
        owner.attackDisabled(true);
        owner.moveSpeed(owner.moveSpeed() /2);
    }
}
