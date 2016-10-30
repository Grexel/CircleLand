/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import circleland.Display.DamageDisplayObject;
import circleland.Equipment.*;
import circleland.Items.Gold;
import circleland.Items.Portal;
import circleland.Weapons.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Jeff
 */
public class CircleEntity extends CircleObject{
    //Properties
    protected Point2D.Double velocity,aim;
    protected int headRadius,headSize;
    protected int team;
    protected Color outlineColor = Color.BLACK;
    protected Color color;
    protected Color headColor = Color.BLACK;
    protected boolean moveLeft,moveRight,moveUp,moveDown,doAttack,doSkill,pickUp;    
    protected double heading; //degrees rotation
    public double heading(){return heading;}
    protected double viewRadius;
    public double viewRadius(){return viewRadius;}
    
    //Inventory and skills
    protected CircleWeapon equippedWeapon;
    protected CircleArmor equippedArmor;
    protected CircleHelmet equippedHelmet;
    protected CircleGloves equippedGloves;
    protected CircleBoots equippedBoots;
    protected CircleRing equippedRing1;
    protected CircleRing equippedRing2;
    protected CircleAmulet equippedAmulet;
    protected CircleSkill equippedSkill;
    
    protected ArrayList<CircleItem> inventory;
    public ArrayList<CircleItem> inventory(){return inventory;}
    protected ArrayList<CircleSkill> skills;
    public ArrayList<CircleSkill> skills(){return skills;}
    protected int skillPoints;
    protected int statPoints;
    
    protected CircleEntity focusedEntity;
    public void focusedEntity(CircleEntity m){focusedEntity = m;}
    public CircleEntity focusedEntity(){return focusedEntity;}
    
    //Stats
    protected int strength, dexterity, magic, fortitude;
    protected int minDamage, maxDamage;
    protected double maxHealth, health,healthRegeneration,maxMana,mana,manaRegeneration,
            attackSpeed,castSpeed,moveSpeed;
    protected int level, experience,experienceToNextLevel,
            attackDamage,magicDamage,attackDefense,magicDefense,
            precision;
    protected int baseMinDamage, baseMaxDamage;
    protected double baseMaxHealth,baseHealthRegeneration,baseMaxMana,baseManaRegeneration,
            baseMoveSpeed,baseAttackSpeed,baseCastSpeed;
    protected int baseAttackDamage,baseMagicDamage,baseAttackDefense,baseMagicDefense,
            basePrecision;
    protected int attackTimer, castTimer;
    protected int gold;
    protected double magicFind, baseMagicFind;
    
    //Effects
    protected ArrayList<CircleEffect> effects;
    public ArrayList<CircleEffect> effects(){return effects;}
    protected boolean attackDisabled, castDisabled, moveDisabled, isFeared, isThorned;
    
    public CircleEntity()
    {
        //initialize properties
        name = "Entity";
        viewRadius = 100;
        position = new Point2D.Double(0,0);
        velocity = new Point2D.Double(0,0);
        aim = new Point2D.Double(0,0);
        size = 25;
        headRadius = (int)size/2;
        headSize = (int)size/2;
        color = Color.BLUE;
        heading = 0;
        team = CircleLandUtility.PLAYER_SIDE;
        inventory = new ArrayList<>();
        effects = new ArrayList<>();
        skills = new ArrayList<>();
        statPoints = 0;
        skillPoints = 0;
        strength = 1;
        dexterity = 1;
        magic = 1;
        fortitude = 1;
        
        moveLeft = false;
        moveRight = false;
        moveUp = false;
        moveDown = false;
        doAttack = false;
        doSkill = false;
        pickUp = false;
        attackDisabled = false;
        castDisabled = false;
        moveDisabled = false;
        isFeared = false;
        isThorned = false;
        
        //initialize stats
        level = 1;
        maxHealth = 100;
        health = 100;
        healthRegeneration = 10;
        maxMana =100;
        mana = 100;
        manaRegeneration = 10;
        experience = 0;
        experienceToNextLevel = 100;
        attackDamage = 20;
        magicDamage = 5;
        attackDefense = 5;
        magicDefense = 5;
        precision = 1;
        attackSpeed = 200;
        castSpeed = 200;
        moveSpeed = 200;
        magicFind = 0.01;
        minDamage = 1;
        maxDamage = 1;
        
        baseMaxHealth = 100;
        baseHealthRegeneration = 10;
        baseMaxMana =100;
        baseManaRegeneration = 10;
        baseAttackDamage = 20;
        baseMagicDamage = 5;
        baseAttackDefense = 5;
        baseMagicDefense = 5;
        basePrecision = 1;
        baseAttackSpeed = 0;
        baseCastSpeed = 200;
        baseMoveSpeed = 200;
        baseMagicFind = 0.01;
        baseMinDamage = 1;
        baseMaxDamage = 1;
        
        gold = 0;
        
        attackTimer = 0;
        castTimer = 0;
    }
    
    public void update(long deltaTime, CircleMap world) {
        //this may need to be evented out instead of running every frame
        recalculateBonuses(deltaTime); 
        if(focusedEntity != null && focusedEntity.health() <= 0){
            focusedEntity = null;
        }
        
        //decrement timers;
        attackTimer -= deltaTime;
        castTimer -= deltaTime;
        
        //Right and Left movement
        if(moveLeft){
            velocity.x = -moveSpeed * deltaTime / 1000;
        } else if(moveRight){
            velocity.x = moveSpeed * deltaTime / 1000;
        } else{
            velocity.x = 0;
        }
        //Up and Down movement
        if(moveUp){
            velocity.y = -moveSpeed * deltaTime / 1000;
        } else if(moveDown) {
            velocity.y = moveSpeed * deltaTime / 1000;
        } else {
            velocity.y = 0;
        }
        //Set Position
        if(!moveDisabled)
        {
            position.x += velocity.x;
            position.y += velocity.y;
        }
        
        collideWithAllCircleEntities(world);
        
        //Set Heading
        double deltaY = aim.y - position.y;
        double deltaX = aim.x - position.x;  
        heading = Math.atan2(deltaY, deltaX);
        
        //Attack if able
        if(!attackDisabled && doAttack && attackTimer <= 0  && castTimer <= 0 && health > 0 && equippedWeapon != null)
        {
            attack(world);
        }
        //cast if able;
        if(!castDisabled && doSkill && equippedSkill != null && mana > equippedSkill.manaCost() && castTimer <= 0){
            castSpell(world);
        }
        //pick up items if on them
        if(pickUp){
            pickUp(world);
        }
        pickUp = false;
        //debug
       //System.out.println("Position: " + position.x + "," + position.y);
    }
    public void levelUp(){
        level++;
        skillPoints++;
        baseMaxHealth += 10;
        health = maxHealth;
        baseHealthRegeneration += 1;
        baseMaxMana += 10;
        mana = maxMana;
        baseManaRegeneration += 1;
        experience = 0;
        experienceToNextLevel = experienceToNextLevel + experienceToNextLevel/2 + level * 100;
        baseAttackDamage += 10;
        baseMagicDamage += 5;
        baseAttackDefense += 5;
        baseMagicDefense += 5;
        basePrecision += 1;
        baseAttackSpeed -= 2;
        baseCastSpeed -= 1;
        baseMoveSpeed += 2;
        baseMagicFind += 0.0001;
        
    }
    public void recalculateBonuses(long deltaTime){
        attackDisabled = false;
        castDisabled = false;
        moveDisabled = false;
        isFeared = false;
        isThorned = false;
        
        //start with base stats
        maxHealth = baseMaxHealth;
        healthRegeneration = baseHealthRegeneration;
        maxMana = baseMaxMana;
        manaRegeneration = baseManaRegeneration;
        attackDamage = baseAttackDamage;
        magicDamage = baseMagicDamage;
        attackDefense = baseAttackDefense;
        magicDefense = baseMagicDefense;
        precision = basePrecision;
        attackSpeed = baseAttackSpeed;
        castSpeed = baseCastSpeed;
        moveSpeed = baseMoveSpeed;
        magicFind = baseMagicFind;
        minDamage = baseMinDamage;
        maxDamage = baseMaxDamage;
        
        //add item stats;
        if(equippedWeapon != null)equippedWeapon.addBonus(this);
        if(equippedArmor != null)equippedArmor.addBonus(this);
        if(equippedHelmet != null)equippedHelmet.addBonus(this);
        if(equippedGloves != null)equippedGloves.addBonus(this);
        if(equippedBoots != null)equippedBoots.addBonus(this);
        if(equippedRing1 != null)equippedRing1.addBonus(this);
        if(equippedRing2 != null)equippedRing2.addBonus(this);
        if(equippedAmulet != null)equippedAmulet.addBonus(this);
        
        //add item affixes;
        if(equippedWeapon != null)
        for(CircleAffix affix : equippedWeapon.affixes()){affix.addBonus(this);}
        if(equippedArmor != null)
        for(CircleAffix affix : equippedArmor.affixes()){affix.addBonus(this);}
        if(equippedHelmet != null)
        for(CircleAffix affix : equippedHelmet.affixes()){affix.addBonus(this);}
        if(equippedGloves != null)
        for(CircleAffix affix : equippedGloves.affixes()){affix.addBonus(this);}
        if(equippedBoots != null)
        for(CircleAffix affix : equippedBoots.affixes()){affix.addBonus(this);}
        if(equippedRing1 != null)
        for(CircleAffix affix : equippedRing1.affixes()){affix.addBonus(this);}
        if(equippedRing2 != null)
        for(CircleAffix affix : equippedRing2.affixes()){affix.addBonus(this);}
        if(equippedAmulet != null)
        for(CircleAffix affix : equippedAmulet.affixes()){affix.addBonus(this);}
        
        //add effects stats, remove if expired;
        for (Iterator<CircleEffect> iterator = effects.iterator(); iterator.hasNext();) {
            CircleEffect effect = iterator.next();
            effect.update(deltaTime, this);
            if (effect.life() <= 0) {
                iterator.remove();
            }
        }
        //add passive bonuses from skills
        for(CircleSkill skill : skills){
            if(!skill.isActive())
            {
                ((CirclePassiveSkill)skill).addBonus(this);
            }
        }
    }
    public void draw(Graphics2D graphics)
    {
        if(health <= 0)
        {
            drawDead(graphics);
        }
        else{
            
            drawBody(graphics);
            drawHead(graphics);
            drawEffects(graphics);
        }
    }
    public void drawBody(Graphics2D graphics)
    {
        //draw Circle body
        graphics.setColor(outlineColor);
        graphics.fillOval((int)(position.x - (size+6)/ 2), (int)(position.y - (size+6) / 2),
            (int)size+6, (int)size+6);
        graphics.setColor(color);
        graphics.fillOval((int)(position.x - size / 2), (int)(position.y - size / 2),
            (int)size, (int)size);
        
    }
    public void drawHead(Graphics2D graphics){
        //draw Circle heading
        int x1 = (int)position.x;
        int y1 = (int)position.y;
        int x2 = x1 + (int) Math.round((headRadius)
                * Math.cos(heading)) + headSize/2;
        int y2 = y1 + (int) Math.round((headRadius)
                * Math.sin(heading)) + headSize/2;
        graphics.setColor(outlineColor);
        graphics.fillOval(x2 - headSize, y2 - headSize, headSize, headSize);
        graphics.setColor(headColor);
        graphics.fillOval(x2 - headSize+2, y2 - headSize+2, headSize-4, headSize-4);
    }
    public void drawDead(Graphics2D graphics){
        //draw body
        graphics.setColor(Color.GRAY);
        graphics.fillOval((int)(position.x - (size+6)/ 2), (int)(position.y - (size+6) / 2),
            (int)size+6, (int)size+6);
        graphics.setColor(color.brighter());
        graphics.fillOval((int)(position.x - size / 2), (int)(position.y - size / 2),
            (int)size, (int)size);
        
        //draw head
        int x1 = (int)position.x;
        int y1 = (int)position.y;
        int x2 = x1 + (int) Math.round((headRadius)
                * Math.cos(heading)) + headSize/2;
        int y2 = y1 + (int) Math.round((headRadius)
                * Math.sin(heading)) + headSize/2;
        graphics.setColor(outlineColor);
        graphics.drawLine(x2+(int)headSize/2, y2-(int)headSize/2, x2-(int)headSize/2, y2+(int)headSize/2);
        graphics.drawLine(x2+(int)headSize/2, y2+(int)headSize/2, x2-(int)headSize/2, y2-(int)headSize/2);
        //graphics.fillOval(x2 - headSize, y2 - headSize, headSize, headSize);
        
    }
    public void drawEffects(Graphics2D graphics){
        for (Iterator<CircleEffect> iterator = effects.iterator(); iterator.hasNext();) {
            CircleEffect effect = iterator.next();
            effect.draw(graphics, this);
        }
    }
    public void attack(CircleMap world){
        equippedWeapon.attack(this, (team==1) ? world.playerAttacks():world.monsterAttacks());
        attackTimer = (int)attackSpeed;
    }
    public void castSpell(CircleMap world){
        equippedSkill.attack(this, (team==1) ? world.playerAttacks():world.monsterAttacks());
        castTimer = (int)castSpeed;
        
    }
    public void pickUp(CircleMap world){
        for (Iterator<CircleItem> iterator = world.itemsOnGround().iterator(); iterator.hasNext();) {
            CircleItem item = iterator.next();
            if(intersectsCircle(item)) {
                boolean b = pickUp(item,world);
                //System.out.println("boolean = " + b);
                if(b){
                    //System.out.println("remove");
                    iterator.remove();
                }
                return;
            }
        }
    }
    //remove if true
    public boolean pickUp(CircleItem item,CircleMap world){
        if(item instanceof Portal){
            world.enterNewMap(((Portal)item));
        }
        else if(item instanceof CircleContainer){
            //open up the container
            if(!((CircleContainer) item).isOpened())
                ((CircleContainer) item).openContainer(world, this);
        }
        else if(item instanceof Gold){
            gold += ((Gold) item).goldAmount();
            return true;
        }
        else if(inventory.size() < CircleLandUtility.MAX_INVENTORY)
        {
            //item is not a portal
            
            inventory.add(item);
            return true;
        }
        return false;
    }
    
    public void hitByAttack(CircleAttack cA,CircleMap world)
    {
        SoundManager.queueSound(cA.hitSound());
        double damage = 0;
        for (Iterator<CircleEffect> iterator = effects.iterator(); iterator.hasNext();) {
            CircleEffect effect = iterator.next();
            effect.hitBy(cA, this);
        }
        if(cA.type() == CircleAttack.PHYSICAL)
            damage = cA.damage() * (100.0/(100.0+attackDefense));
        health -= damage;
        world.damageDisplay().add(
                new DamageDisplayObject((int)(position.x - size/2), (int)(position.y - size/2),damage,1000));
        if(cA.attackOwner().isThorned())
        {
            cA.attackOwner().health(cA.attackOwner().health() - cA.damage());
        }
        if(health <= 0)//monsterDead
        {
            cA.attackOwner().gainExperience((int)Math.pow(2, level));
        }
        cA.attackOwner().hitEnemy(cA, this);
        /*
        if(cA.attackOwner() instanceof CircleClass){
            cA.attackOwner().focusedEntity(this);
        }
        */
    }
    
    public void hitEnemy(CircleAttack cA, CircleEntity enemy)
    {
        for (Iterator<CircleEffect> iterator = effects.iterator(); iterator.hasNext();) {
            CircleEffect effect = iterator.next();
            effect.attacked(enemy,this,cA);
        }
    }
    
    public void collideWithAllCircleEntities(CircleMap world)
    {
        for(CircleEntity cE : world.players())
        {
            collideWithCircleEntity(cE);
        }
        for(CircleEntity cE : world.monsters())
        {
            collideWithCircleEntity(cE);
        }
        for(CircleItem cI : world.itemsOnGround()){
            if(cI instanceof CircleContainer){
                if(intersectsCircle(cI))
                reverseCollideWithCircleEntity(cI);
            }
        }
        for(RectangleObject rO : world.mapCollision())
        {
            if(intersectsRectangle(rO)){
                reverseCollideWithRectangleEntity(rO);
            }
        }
    }
    
    
    //GETTER AND SETTER METHODS
    
    //ACTION VARIABLES
    public void aim(Point2D.Double point){aim = point;}
    public Point2D.Double aim(){ return aim;}
    public void moveLeft(boolean m){moveLeft = m;}
    public boolean moveLeft(){ return moveLeft;}
    public void moveUp(boolean m){moveUp = m;}
    public boolean moveUp(){ return moveUp;}
    public void moveDown(boolean m){moveDown = m;}
    public boolean moveDown(){ return moveDown;}
    public void moveRight(boolean m){moveRight = m;}
    public boolean moveRight(){ return moveRight;}
    public void doAttack(boolean m){doAttack = m;}
    public boolean doAttack(){ return doAttack;}
    public void doSkill(boolean m){doSkill = m;}
    public boolean doSkill(){ return doSkill;}
    public void pickUp(boolean m){pickUp = m;}
    public boolean pickUp(){ return pickUp;}
    public void attackDisabled(boolean m){attackDisabled = m;}
    public boolean attackDisabled(){ return attackDisabled;}
    public void castDisabled(boolean m){castDisabled = m;}
    public boolean castDisabled(){ return castDisabled;}
    public void moveDisabled(boolean m){moveDisabled = m;}
    public boolean moveDisabled(){ return moveDisabled;}
    public void isFeared(boolean m){isFeared = m;}
    public boolean isFeared(){ return isFeared;}
    public void isThorned(boolean m){isThorned = m;}
    public boolean isThorned(){ return isThorned;}
    
    public void team(int m){team = m;}
    public int team(){ return team;}
    public void level(int m){level = m;}
    public int level(){ return level;}
    public void gainExperience(int exp){
        experience += exp;
    }
    public void skillPoints(int m){skillPoints = m;}
    public int skillPoints(){ return skillPoints;}
    public void experience(int m){experience = m;}
    public int experience(){ return experience;}
    public void experienceToNextLevel(int m){experienceToNextLevel = m;}
    public int experienceToNextLevel(){ return experienceToNextLevel;}
    
    //EQUIPMENT
    public void equippedWeapon(CircleWeapon m){equippedWeapon = m;}
    public CircleWeapon equippedWeapon(){ return equippedWeapon;}
    public void equippedArmor(CircleArmor m){equippedArmor = m;}
    public CircleArmor equippedArmor(){ return equippedArmor;}
    public void equippedHelmet(CircleHelmet m){equippedHelmet = m;}
    public CircleHelmet equippedHelmet(){ return equippedHelmet;}
    public void equippedGloves(CircleGloves m){equippedGloves = m;}
    public CircleGloves equippedGloves(){ return equippedGloves;}
    public void equippedBoots(CircleBoots m){equippedBoots = m;}
    public CircleBoots equippedBoots(){ return equippedBoots;}
    public void equippedRing1(CircleRing m){equippedRing1 = m;}
    public CircleRing equippedRing1(){ return equippedRing1;}
    public void equippedRing2(CircleRing m){equippedRing2 = m;}
    public CircleRing equippedRing2(){ return equippedRing2;}
    public void equippedAmulet(CircleAmulet m){equippedAmulet = m;}
    public CircleAmulet equippedAmulet(){ return equippedAmulet;}
    
    public void equippedSkill(CircleSkill m){equippedSkill = m;}
    public CircleSkill equippedSkill(){ return equippedSkill;}
    
    //AUGMENTED STATS
    public void heal(double h){health+=h;}
    public void maxHealth(double m){maxHealth = m;}
    public double maxHealth(){ return maxHealth;}
    public void health(double m){health = m;}
    public double health(){ return health;}
    public void healthRegeneration(double m){healthRegeneration = m;}
    public double healthRegeneration(){ return healthRegeneration;}
    public void maxMana(double m){maxMana = m;}
    public double maxMana(){ return maxMana;}
    public void mana(double m){mana = m;}
    public double mana(){ return mana;}
    public void manaRegeneration(double m){manaRegeneration = m;}
    public double manaRegeneration(){ return manaRegeneration;}
    public void attackDamage(int m){attackDamage = m;}
    public int attackDamage(){ return attackDamage;}
    public void magicDamage(int m){magicDamage = m;}
    public int magicDamage(){ return magicDamage;}
    public void attackDefense(int m){attackDefense = m;}
    public int attackDefense(){ return attackDefense;}
    public void magicDefense(int m){magicDefense = m;}
    public int magicDefense(){ return magicDefense;}
    public void precision(int m){precision = m;}
    public int precision(){ return precision;}
    public void attackSpeed(double m){attackSpeed = m;}
    public double attackSpeed(){ return attackSpeed;}
    public void castSpeed(double m){castSpeed = m;}
    public double castSpeed(){ return castSpeed;}
    public void moveSpeed(double m){moveSpeed = m;}
    public double moveSpeed(){ return moveSpeed;}
    public void magicFind(double m){magicFind = m;}
    public double magicFind(){ return magicFind;}
    
    public void minDamage(int m){minDamage = m;}
    public int minDamage(){ return minDamage;}
    public void maxDamage(int m){maxDamage = m;}
    public int maxDamage(){ return maxDamage;}
    
    //BASE STATS
    public void baseMaxHealth(double m){baseMaxHealth = m;}
    public double baseMaxHealth(){ return baseMaxHealth;}
    public void baseHealthRegeneration(double m){healthRegeneration = m;}
    public double baseHealthRegeneration(){ return healthRegeneration;}
    public void baseMaxMana(double m){maxMana = m;}
    public double baseMaxMana(){ return maxMana;}
    public void baseManaRegeneration(double m){manaRegeneration = m;}
    public double baseManaRegeneration(){ return manaRegeneration;}
    public void baseAttackDamage(int m){attackDamage = m;}
    public int baseAttackDamage(){ return attackDamage;}
    public void baseMagicDamage(int m){magicDamage = m;}
    public int baseMagicDamage(){ return magicDamage;}
    public void baseAttackDefense(int m){attackDefense = m;}
    public int baseAttackDefense(){ return attackDefense;}
    public void baseMagicDefense(int m){magicDefense = m;}
    public int baseMagicDefense(){ return magicDefense;}
    public void basePrecision(int m){precision = m;}
    public int basePrecision(){ return precision;}
    public void baseAttackSpeed(double m){attackSpeed = m;}
    public double baseAttackSpeed(){ return attackSpeed;}
    public void baseCastSpeed(double m){castSpeed = m;}
    public double baseCastSpeed(){ return castSpeed;}
    public void baseMoveSpeed(double m){moveSpeed = m;}
    public double baseMoveSpeed(){ return moveSpeed;}
    public void baseMagicFind(double m){magicFind = m;}
    public double baseMagicFind(){ return magicFind;}
    
    public void baseMinDamage(int m){baseMinDamage = m;}
    public int baseMinDamage(){ return baseMinDamage;}
    public void baseMaxDamage(int m){baseMaxDamage = m;}
    public int baseMaxDamage(){ return baseMaxDamage;}
    
    //GOLD :}
    public void gold(int m){gold = m;}
    public int gold(){ return gold;}
}



