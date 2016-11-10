/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import circleland.AI.*;

/**
 *
 * @author Jeff
 */
public class CircleMonster extends CircleEntity{
    protected CircleLandAI artificialIntelligence;
    public CircleLandAI artificialIntelligence(){return artificialIntelligence;}
    public void artificialIntelligence(CircleLandAI ai){artificialIntelligence = ai;}
    
    public CircleMonster(){
        //artificialIntelligence = new StraightChaseAI();
       artificialIntelligence = new AttackFleeAI();
      
    }
    @Override
    public void hitByAttack(CircleAttack cA,CircleMap world)
    {
        super.hitByAttack(cA, world);
        focusedEntity = cA.attackOwner();
    }

    @Override
    public void hitEnemy(CircleAttack cA, CircleEntity enemy) {
        super.hitEnemy(cA, enemy);
        artificialIntelligence.hitEnemy(true);
    }
    
    @Override
    public void update(long deltaTime, CircleMap world){
        artificialIntelligence.update(deltaTime, this, world);
        super.update(deltaTime, world);
    }
}
