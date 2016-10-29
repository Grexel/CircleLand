/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Effects;

import circleland.CircleAttack;
import circleland.CircleEffect;
import circleland.CircleEntity;

/**
 *
 * @author Jeff
 */
public class LeechLifeEffect extends CircleEffect{
    public LeechLifeEffect(int lf){
        super(lf);
    }
    @Override
    public void update(long deltaTime, CircleEntity owner)
    {
        super.update(deltaTime,owner);
    }
    @Override
    public void hitBy(CircleAttack attack,CircleEntity owner){
        
        double damage = attack.damage() * (100.0/(100.0+owner.attackDefense()));
        attack.attackOwner().heal(damage/4);
    }
    
}
