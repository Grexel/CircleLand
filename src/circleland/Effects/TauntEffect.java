/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Effects;

import circleland.CircleEffect;
import circleland.CircleEntity;
import circleland.CircleMonster;

/**
 *
 * @author Jeff
 */
public class TauntEffect extends CircleEffect{
    private CircleEntity focus;
    public TauntEffect(int lf, CircleEntity foc){
        super(lf);
        focus = foc;
    }
    public void update(long deltaTime, CircleEntity owner)
    {
        super.update(deltaTime,owner);
        if(owner instanceof CircleMonster){
           ((CircleMonster) owner).focusedEntity(focus);
        }
    }
}
