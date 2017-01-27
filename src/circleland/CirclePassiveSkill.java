/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import java.util.ArrayList;

/**
 *
 * @author Jeff
 */
public abstract class CirclePassiveSkill extends CircleSkill {
    public CirclePassiveSkill(){
        super();
    }
    
    @Override
    public void attack(CircleEntity owner, ArrayList<CircleAttack> attackList){
        //no use for attacks in passive skills
    }
}
