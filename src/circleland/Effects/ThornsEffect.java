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

public class ThornsEffect extends CircleEffect{
    public ThornsEffect(int lf){
        super(lf);
    }
    public void update(long deltaTime, CircleEntity owner)
    {
        super.update(deltaTime,owner);
        owner.isThorned(true);
    }
    public void attacked(CircleEntity hit, CircleEntity owner, CircleAttack cA){
        if(cA.type() == CircleAttack.PHYSICAL){
            double damage = cA.damage() * (100.0/(100.0+hit.attackDefense())/4);
            owner.heal(-damage); //LEET HACKS
        }
    }
}
