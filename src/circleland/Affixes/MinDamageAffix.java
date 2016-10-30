/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Affixes;

import circleland.CircleAffix;
import circleland.CircleEntity;

/**
 *
 * @author Jeff
 */
public class MinDamageAffix extends CircleAffix{
    public static final int minValue = 1;
    public static final int maxValue = 5;
    
    public MinDamageAffix(int level){
        prefix = "Precise";
        suffix = "of Exacting";
        value = level;
    }
    
    @Override
    public void addBonus(CircleEntity entity) {
      entity.minDamage(entity.minDamage() + (int)value);
    }

    @Override
    public String getDetails() {
        return "Minimum Damage +" + value;
    }
    
}
