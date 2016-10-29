/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

/**
 *
 * @author Jeff
 */
public abstract class CirclePassiveSkill extends CircleSkill {
    public CirclePassiveSkill(){
        super();
    }
    public abstract void addBonus(CircleEntity owner);
}
