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

public class ArmorAffix extends CircleAffix{
    public static final int minValue = 2;
    public static final int maxValue = 4;
    
    public ArmorAffix(int level){
        prefix = "Tough";
        suffix = "of Protection";
        value = rand.nextInt(level*maxValue - level*minValue) + level*minValue;
    }
    
    @Override
    public void addBonus(CircleEntity entity) {
      entity.attackDefense(entity.attackDefense()+ (int)value);
    }

    @Override
    public String getDetails() {
        return "Armor Bonus +" + value;
    }
    
}
