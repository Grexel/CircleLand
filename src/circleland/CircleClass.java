/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import circleland.Quests.QuestList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * This is the subclass for character classes such as 
 * Warrior
 * Mage
 * Archer
 * 
 * Demon
 * Necromancer
 * Nymph
 * @author Jeff
 */
public class CircleClass extends CircleEntity{
    private static final Random rand = new Random();
    private String className;
    public String className(){return className;}
    
    private QuestList quests;
    public QuestList quests(){return quests;}
    protected ArrayList<CircleSkill> allSkills;
    public ArrayList<CircleSkill> allSkills(){return allSkills;}
    protected ArrayList<CircleSummoned> summoned;
    public ArrayList<CircleSummoned> summoned(){return summoned;}
    public CircleClass(String className){
        super();
        this.className = className;
        quests = new QuestList();
        allSkills = new ArrayList<>();
        summoned = new ArrayList<>();
    }
    public void addSummoned(CircleSummoned cS){
        summoned.add(cS);
    }
    public void removeSummoned(CircleSummoned cS){
        summoned.remove(cS);
    }
    public void returnSummoned(){
        for(CircleSummoned cS : summoned){
            cS.focusedEntity(null);
            //random direction,away from player
            double dir = rand.nextDouble() * 2 * Math.PI;
            double x2 = position.x + (int) Math.round(size
                * Math.cos(dir));
            double y2 = position.y + (int) Math.round(size
                * Math.sin(dir));
            cS.position().x = x2;
            cS.position().y = y2;
        }
    }
    public void update(long deltaTime, CircleMap world){
        super.update(deltaTime, world);
        //System.out.println("Position:" + position.x);
        //update monster Attacks
        for (Iterator<CircleSummoned> iterator = summoned.iterator(); iterator.hasNext();) {
            CircleSummoned sum = iterator.next();
            
            if (sum.health() <= 0) {
            // Remove the current element from the iterator and the list.
                iterator.remove();
            }
        }
        //level up if enough xp;
        if(experience >= experienceToNextLevel)
        {
            levelUp();
        }
        
        //regenerate health and mana;
        health += healthRegeneration * deltaTime/1000;
        mana += manaRegeneration * deltaTime/1000;
        if(health > maxHealth) health = maxHealth;
        if(mana > maxMana) mana = maxMana;
    }
}
