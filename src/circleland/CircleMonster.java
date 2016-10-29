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
public class CircleMonster extends CircleEntity{
    @Override
    public void hitByAttack(CircleAttack cA,CircleMap world)
    {
        super.hitByAttack(cA, world);
        focusedEntity = cA.attackOwner();
    }
}
