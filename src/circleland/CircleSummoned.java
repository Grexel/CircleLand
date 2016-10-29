/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import circleland.Classes.Summons.SummonedBoneWall;
import java.util.Iterator;

/**
 *
 * @author Jeff
 */
public class CircleSummoned extends CircleEntity{
    protected CircleEntity owner;
    public CircleEntity owner(){return owner;}
    public void owner(CircleEntity o){owner = o; ((CircleClass)owner).summoned().add(this);}
    
    public CircleSummoned(){
        super();
    }
    public void update(long deltaTime, CircleMap world){
        super.update(deltaTime, world);
        //regenerate health and mana;
        health += healthRegeneration * deltaTime/1000;
        mana += manaRegeneration * deltaTime/1000;
        if(health > maxHealth) health = maxHealth;
        if(mana > maxMana) mana = maxMana;
    }
    public void collideWithAllCircleEntities(CircleMap world)
    {
        for(CircleEntity cE : world.players())
        {
            //don't collide with players
            if(!(cE instanceof CircleClass) || this instanceof SummonedBoneWall)
                collideWithCircleEntity(cE);
        }
        for(CircleEntity cE : world.monsters())
        {
            collideWithCircleEntity(cE);
        }
    }
    public void removeSummon(){
        //remove from owner
        ((CircleClass)owner).removeSummoned(this);
    }
    public void gainExperience(int exp){
        owner.gainExperience(exp);
    }
    @Override
    public void hitByAttack(CircleAttack cA, CircleMap world)
    {
        super.hitByAttack(cA, world);
        focusedEntity = cA.attackOwner();
    }
}
