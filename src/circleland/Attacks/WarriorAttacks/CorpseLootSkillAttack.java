/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Attacks.WarriorAttacks;

import circleland.CircleAttack;
import circleland.CircleEntity;
import circleland.CircleItem;
import circleland.CircleLandUtility;
import circleland.CircleMap;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;

/**
 *
 * @author Jeff
 */

public class CorpseLootSkillAttack extends CircleAttack {
    private static final Color OUTLINE_COLOR = Color.WHITE;
    public CorpseLootSkillAttack(CircleEntity owner, int life, int dmg, double x, double y,
            double velX, double velY, int bSize, Color bColor){
        super(owner,life,dmg,1,x,y,velX,velY,bSize,bColor);
    }
    
    public void update(long deltaTime, CircleMap world){
        //move attack;
        //check for collision with player or monster
        for (Iterator<CircleEntity> iterator = world.corpses().iterator(); iterator.hasNext();) {
            CircleEntity corpse = iterator.next();
            if(intersectsCircle(corpse))
            {
                //remove corpse from world;
                //add items to world
                //destroy attack
                iterator.remove();
                CircleItem item = world.lootGenerator().generateLoot(this.attackOwner());
                item.position().x = attackOwner.position().x;
                item.position().y = attackOwner.position().y;
                world.spawningItems().add(item);
            }
        }
        life = 0;
    }
    public void draw(Graphics2D graphics){
    }
}