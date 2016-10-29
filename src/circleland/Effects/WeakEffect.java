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
public class WeakEffect extends CircleEffect{
    public WeakEffect(int lf){
        super(lf);
    }
    public void update(long deltaTime, CircleEntity owner)
    {
        super.update(deltaTime,owner);
        owner.attackDamage(owner.attackDamage()/2);
        owner.attackDefense(owner.attackDefense()/2);
    }
    
}
