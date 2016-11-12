/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Affixes;

import circleland.CircleAffix;
import static circleland.CircleAffix.rand;
import circleland.CircleEntity;

/**
 *
 * @author Jeff
 */
public class DexterityAffix extends CircleAffix{
    public static final int minValue = 1;
    public static final int maxValue = 2;
    
    public DexterityAffix(int level){
        prefix = "Dextrous";
        suffix = "of Dexterity";
        value = 0;
        value = rand.nextInt(level*maxValue - level*minValue + 1) + level*minValue;
    }
    
    @Override
    public void addBonus(CircleEntity entity) {
      entity.dexterity(entity.dexterity() + (int)value);
    }

    @Override
    public String getDetails() {
        return "Dexterity +" + value;
    }
}

