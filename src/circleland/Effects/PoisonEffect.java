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

public class PoisonEffect extends CircleEffect{
    private double damagePerTick;
    public PoisonEffect(double lf, double damage){
        super((int)lf);
        damagePerTick = damage/life;
    }
    public void update(long deltaTime, CircleEntity owner)
    {
        super.update(deltaTime,owner);
        owner.health((owner.health() - damagePerTick*deltaTime));
    }
}