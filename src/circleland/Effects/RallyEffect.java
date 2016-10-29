/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Effects;

import circleland.CircleEffect;
import circleland.CircleEntity;

/**
 *
 * @author Jeff
 */
public class RallyEffect extends CircleEffect{
    private int level;
    public int level(){return level;}
    private int bonusHealth;
    private int bonusAttack;
    private int bonusDefense;
    public RallyEffect(int lvl, int lf, int bHealth, int bAttack, int bDefense){
        super(lf);
        level = lvl;
        bonusHealth = bHealth;
        bonusAttack = bAttack;
        bonusDefense = bDefense;
    }
    public void update(long deltaTime, CircleEntity owner)
    {
        super.update(deltaTime,owner);
        owner.maxHealth(owner.maxHealth() + bonusHealth);
        owner.attackDamage(owner.attackDamage()+ bonusAttack);
        owner.attackDefense(owner.attackDefense()+ bonusDefense);
    }
}
