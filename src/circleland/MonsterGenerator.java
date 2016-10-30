/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import circleland.Monsters.*;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

/**
 * Monster health is getting messed up. This is because equipping weapons add 
 * stats * and the health doesnt get reset. Make sure all stats are reset at the
 * end of creation
 * @author Jeff
 */
public class MonsterGenerator {
    //depending on map type and level of map, spawn monsters.
    //scale monster stats to level.
    //spawn special monsters with unique weapons.
    private static final Random rand = new Random();
    private double uniqueSpawnChance;
    private LootGenerator weaponGenerator;
    public MonsterGenerator(){
        uniqueSpawnChance = 0.05;
        weaponGenerator = new LootGenerator();
    }
    public ArrayList<CircleEntity> spawnGroupRedImp(int level, int numberOfMonsters,Rectangle rect){
        ArrayList<CircleEntity> group = new ArrayList<CircleEntity>();
        for(int i = 0; i < numberOfMonsters;i++){
            //add some weaker and stronger monsters around level +- 3
            int monsterLevel = (level > 3) ? rand.nextInt(6) - 3: level;
            CircleEntity monster = spawnRedImp(level + monsterLevel,(Math.random() < uniqueSpawnChance));
            monster.position().x = rand.nextInt(rect.width) + rect.x;
            monster.position().y = rand.nextInt(rect.height) + rect.y;
            group.add(monster);
        }
        return group;
    }
    public CircleEntity spawnRedImp(int level,boolean unique){
        RedImpMonster monster = new RedImpMonster();
        monster.level = level;
        monster.baseMaxHealth = RedImpMonster.BASE_MAXHEALTH + level*4;
        monster.maxHealth = monster.baseMaxHealth;
        monster.health = monster.maxHealth;
        monster.baseMaxMana = RedImpMonster.BASE_MAXMANA + level*2;
        monster.baseAttackDamage = RedImpMonster.BASE_ATTACK_DAMAGE + level*2;
        monster.baseMagicDamage = RedImpMonster.BASE_MAGIC_DAMAGE + level*2;
        monster.baseAttackDefense = RedImpMonster.BASE_ATTACK_DEFENSE + level*2;
        monster.baseMagicDefense = RedImpMonster.BASE_MAGIC_DEFENSE + level*2;
        monster.basePrecision = RedImpMonster.BASE_PRECISION + level*1;
        monster.baseAttackSpeed = RedImpMonster.BASE_ATTACKSPEED + level*1;
        monster.baseCastSpeed = RedImpMonster.BASE_CASTSPEED + level*1;
        monster.baseMoveSpeed = RedImpMonster.BASE_MOVESPEED + level*1;
        
        CircleWeapon cW = weaponGenerator.getBiteWeapon(level);
        monster.equippedWeapon(cW);
        //System.out.println("Imp level:" + monster.level);
        return monster;
    }
    public ArrayList<CircleEntity> spawnGroupHound(int level, int numberOfMonsters,Rectangle rect){
        ArrayList<CircleEntity> group = new ArrayList<CircleEntity>();
        for(int i = 0; i < numberOfMonsters;i++){
            //add some weaker and stronger monsters around level +- 3
            int monsterLevel = (level > 3) ? rand.nextInt(6) - 3: level;
            CircleEntity monster = spawnHound(level + monsterLevel,(Math.random() < uniqueSpawnChance));
            monster.position().x = rand.nextInt(rect.width) + rect.x;
            monster.position().y = rand.nextInt(rect.height) + rect.y;
            group.add(monster);
        }
        return group;
    }
    public CircleEntity spawnHound(int level,boolean unique){
        HoundMonster monster = new HoundMonster();
        monster.level = level;
        monster.baseMaxHealth = HoundMonster.BASE_MAXHEALTH + level*4;
        monster.maxHealth = monster.baseMaxHealth;
        monster.health = monster.maxHealth;
        monster.baseMaxMana = HoundMonster.BASE_MAXMANA + level*2;
        monster.baseAttackDamage = HoundMonster.BASE_ATTACK_DAMAGE + level*2;
        monster.baseMagicDamage = HoundMonster.BASE_MAGIC_DAMAGE + level*2;
        monster.baseAttackDefense = HoundMonster.BASE_ATTACK_DEFENSE + level*2;
        monster.baseMagicDefense = HoundMonster.BASE_MAGIC_DEFENSE + level*2;
        monster.basePrecision = HoundMonster.BASE_PRECISION + level*1;
        monster.baseAttackSpeed = HoundMonster.BASE_ATTACKSPEED + level*1;
        monster.baseCastSpeed = HoundMonster.BASE_CASTSPEED + level*1;
        monster.baseMoveSpeed = HoundMonster.BASE_MOVESPEED + level*1;
        
        CircleWeapon cW = weaponGenerator.getBiteWeapon(level);
        cW.bulletSize(30);
        monster.equippedWeapon(cW);
        //System.out.println("Hound level:" + monster.level);
        return monster;
    }
    public ArrayList<CircleEntity> spawnGroupFly(int level, int numberOfMonsters,Rectangle rect){
        ArrayList<CircleEntity> group = new ArrayList<CircleEntity>();
        for(int i = 0; i < numberOfMonsters;i++){
            //add some weaker and stronger monsters around level +- 3
            int monsterLevel = (level > 3) ? rand.nextInt(6) - 3: level;
            CircleEntity monster = spawnFly(level + monsterLevel,(Math.random() < uniqueSpawnChance));
            monster.position().x = rand.nextInt(rect.width) + rect.x;
            monster.position().y = rand.nextInt(rect.height) + rect.y;
            group.add(monster);
        }
        return group;
    }
    public CircleEntity spawnFly(int level,boolean unique){
        FlyMonster monster = new FlyMonster();
        monster.level = level;
        monster.baseMaxHealth = FlyMonster.BASE_MAXHEALTH + level*4;
        monster.maxHealth = monster.baseMaxHealth;
        monster.health = monster.maxHealth;
        monster.baseMaxMana = FlyMonster.BASE_MAXMANA + level*2;
        monster.baseAttackDamage = FlyMonster.BASE_ATTACK_DAMAGE + level*2;
        monster.baseMagicDamage = FlyMonster.BASE_MAGIC_DAMAGE + level*2;
        monster.baseAttackDefense = FlyMonster.BASE_ATTACK_DEFENSE + level*2;
        monster.baseMagicDefense = FlyMonster.BASE_MAGIC_DEFENSE + level*2;
        monster.basePrecision = FlyMonster.BASE_PRECISION + level*1;
        monster.baseAttackSpeed = FlyMonster.BASE_ATTACKSPEED + level*1;
        monster.baseCastSpeed = FlyMonster.BASE_CASTSPEED + level*1;
        monster.baseMoveSpeed = FlyMonster.BASE_MOVESPEED + level*1;
        
        CircleWeapon cW = weaponGenerator.getBiteWeapon(level);
        monster.equippedWeapon(cW);
        //System.out.println("Fly level:" + monster.level);
        return monster;
    }
    public ArrayList<CircleEntity> spawnGroupInchWorm(int level, int numberOfMonsters,Rectangle rect){
        ArrayList<CircleEntity> group = new ArrayList<CircleEntity>();
        for(int i = 0; i < numberOfMonsters;i++){
            //add some weaker and stronger monsters around level +- 3
            int monsterLevel = (level > 3) ? rand.nextInt(6) - 3: level;
            CircleEntity monster = spawnInchWorm(level + monsterLevel,(Math.random() < uniqueSpawnChance));
            monster.position().x = rand.nextInt(rect.width) + rect.x;
            monster.position().y = rand.nextInt(rect.height) + rect.y;
            group.add(monster);
        }
        return group;
    }
    public CircleEntity spawnInchWorm(int level,boolean unique){
        InchWormMonster monster = new InchWormMonster();
        monster.level = level;
        monster.baseMaxHealth = InchWormMonster.BASE_MAXHEALTH + level*4;
        monster.maxHealth = monster.baseMaxHealth;
        monster.health = monster.maxHealth;
        monster.baseMaxMana = InchWormMonster.BASE_MAXMANA + level*2;
        monster.baseAttackDamage = InchWormMonster.BASE_ATTACK_DAMAGE + level*2;
        monster.baseMagicDamage = InchWormMonster.BASE_MAGIC_DAMAGE + level*2;
        monster.baseAttackDefense = InchWormMonster.BASE_ATTACK_DEFENSE + level*2;
        monster.baseMagicDefense = InchWormMonster.BASE_MAGIC_DEFENSE + level*2;
        monster.basePrecision = InchWormMonster.BASE_PRECISION + level*1;
        monster.baseAttackSpeed = InchWormMonster.BASE_ATTACKSPEED + level*1;
        monster.baseCastSpeed = InchWormMonster.BASE_CASTSPEED + level*1;
        monster.baseMoveSpeed = InchWormMonster.BASE_MOVESPEED + level*1;
        
        CircleWeapon cW = weaponGenerator.getBiteWeapon(level);
        cW.bulletSize(30);
        monster.equippedWeapon(cW);
       // System.out.println("InchWorm level:" + monster.level);
        return monster;
    }
    public ArrayList<CircleEntity> spawnGroupSkeletonRanger(int level, int numberOfMonsters,Rectangle rect){
        ArrayList<CircleEntity> group = new ArrayList<CircleEntity>();
        for(int i = 0; i < numberOfMonsters;i++){
            //add some weaker and stronger monsters around level +- 3
            int monsterLevel = (level > 3) ? rand.nextInt(6) - 3: level;
            CircleEntity monster = spawnSkeletonRanger(level + monsterLevel,(Math.random() < uniqueSpawnChance));
            monster.position().x = rand.nextInt(rect.width) + rect.x;
            monster.position().y = rand.nextInt(rect.height) + rect.y;
            group.add(monster);
        }
        return group;
    }
    public CircleEntity spawnSkeletonRanger(int level,boolean unique){
        SkeletonRangerMonster monster = new SkeletonRangerMonster();
        monster.level = level;
        monster.baseMaxHealth = SkeletonRangerMonster.BASE_MAXHEALTH + level*4;
        monster.maxHealth = monster.baseMaxHealth;
        monster.health = monster.maxHealth;
        monster.baseMaxMana = SkeletonRangerMonster.BASE_MAXMANA + level*2;
        monster.baseAttackDamage = SkeletonRangerMonster.BASE_ATTACK_DAMAGE + level*2;
        monster.baseMagicDamage = SkeletonRangerMonster.BASE_MAGIC_DAMAGE + level*2;
        monster.baseAttackDefense = SkeletonRangerMonster.BASE_ATTACK_DEFENSE + level*2;
        monster.baseMagicDefense = SkeletonRangerMonster.BASE_MAGIC_DEFENSE + level*2;
        monster.basePrecision = SkeletonRangerMonster.BASE_PRECISION + level*1;
        monster.baseAttackSpeed = SkeletonRangerMonster.BASE_ATTACKSPEED + level*1;
        monster.baseCastSpeed = SkeletonRangerMonster.BASE_CASTSPEED + level*1;
        monster.baseMoveSpeed = SkeletonRangerMonster.BASE_MOVESPEED + level*1;
        
        CircleWeapon cW = weaponGenerator.getBiteWeapon(level);
        monster.equippedWeapon(cW);
        //System.out.println("SkeletonRanger level:" + monster.level);
        return monster;
    }
    public ArrayList<CircleEntity> spawnGroupSkeletonSword(int level, int numberOfMonsters,Rectangle rect){
        ArrayList<CircleEntity> group = new ArrayList<CircleEntity>();
        for(int i = 0; i < numberOfMonsters;i++){
            //add some weaker and stronger monsters around level +- 3
            int monsterLevel = (level > 3) ? rand.nextInt(6) - 3: level;
            CircleEntity monster = spawnSkeletonSword(level + monsterLevel,(Math.random() < uniqueSpawnChance));
            monster.position().x = rand.nextInt(rect.width) + rect.x;
            monster.position().y = rand.nextInt(rect.height) + rect.y;
            group.add(monster);
        }
        return group;
    }
    public CircleEntity spawnSkeletonSword(int level,boolean unique){
        SkeletonSwordMonster monster = new SkeletonSwordMonster();
        monster.level = level;
        monster.baseMaxHealth = SkeletonSwordMonster.BASE_MAXHEALTH + level*4;
        monster.maxHealth = monster.baseMaxHealth;
        monster.health = monster.maxHealth;
        monster.baseMaxMana = SkeletonSwordMonster.BASE_MAXMANA + level*2;
        monster.baseAttackDamage = SkeletonSwordMonster.BASE_ATTACK_DAMAGE + level*2;
        monster.baseMagicDamage = SkeletonSwordMonster.BASE_MAGIC_DAMAGE + level*2;
        monster.baseAttackDefense = SkeletonSwordMonster.BASE_ATTACK_DEFENSE + level*2;
        monster.baseMagicDefense = SkeletonSwordMonster.BASE_MAGIC_DEFENSE + level*2;
        monster.basePrecision = SkeletonSwordMonster.BASE_PRECISION + level*1;
        monster.baseAttackSpeed = SkeletonSwordMonster.BASE_ATTACKSPEED + level*1;
        monster.baseCastSpeed = SkeletonSwordMonster.BASE_CASTSPEED + level*1;
        monster.baseMoveSpeed = SkeletonSwordMonster.BASE_MOVESPEED + level*1;
        
        CircleWeapon cW = weaponGenerator.getBiteWeapon(level);
        monster.equippedWeapon(cW);
        //System.out.println("SkeletonSword level:" + monster.level);
        return monster;
    }
    public ArrayList<CircleEntity> spawnGroupTurret(int level, int numberOfMonsters,Rectangle rect){
        ArrayList<CircleEntity> group = new ArrayList<CircleEntity>();
        for(int i = 0; i < numberOfMonsters;i++){
            //add some weaker and stronger monsters around level +- 3
            int monsterLevel = (level > 3) ? rand.nextInt(6) - 3: level;
            CircleEntity monster = spawnTurret(level + monsterLevel,(Math.random() < uniqueSpawnChance));
            monster.position().x = rand.nextInt(rect.width) + rect.x;
            monster.position().y = rand.nextInt(rect.height) + rect.y;
            ((TurretMonster)monster).staticPosition().x = monster.position().x;
            ((TurretMonster)monster).staticPosition().y = monster.position().y;
            group.add(monster);
        }
        return group;
    }
    public CircleEntity spawnTurret(int level,boolean unique){
        TurretMonster monster = new TurretMonster();
        monster.level = level;
        monster.baseMaxHealth = TurretMonster.BASE_MAXHEALTH + level*4;
        monster.maxHealth = monster.baseMaxHealth;
        monster.health = monster.maxHealth;
        monster.baseMaxMana = TurretMonster.BASE_MAXMANA + level*2;
        monster.baseAttackDamage = TurretMonster.BASE_ATTACK_DAMAGE + level*2;
        monster.baseMagicDamage = TurretMonster.BASE_MAGIC_DAMAGE + level*2;
        monster.baseAttackDefense = TurretMonster.BASE_ATTACK_DEFENSE + level*2;
        monster.baseMagicDefense = TurretMonster.BASE_MAGIC_DEFENSE + level*2;
        monster.basePrecision = TurretMonster.BASE_PRECISION + level*1;
        monster.baseAttackSpeed = TurretMonster.BASE_ATTACKSPEED + level*1;
        monster.baseCastSpeed = TurretMonster.BASE_CASTSPEED + level*1;
        monster.baseMoveSpeed = TurretMonster.BASE_MOVESPEED + level*1;
        
        CircleWeapon cW = weaponGenerator.getBiteWeapon(level);
        monster.equippedWeapon(cW);
        //System.out.println("Turret level:" + monster.level);
        return monster;
    }
    public ArrayList<CircleEntity> spawnGroupCadaver(int level, int numberOfMonsters,Rectangle rect){
        ArrayList<CircleEntity> group = new ArrayList<CircleEntity>();
        for(int i = 0; i < numberOfMonsters;i++){
            //add some weaker and stronger monsters around level +- 3
            int monsterLevel = (level > 3) ? rand.nextInt(6) - 3: level;
            CircleEntity monster = spawnCadaver(level + monsterLevel,(Math.random() < uniqueSpawnChance));
            monster.position().x = rand.nextInt(rect.width) + rect.x;
            monster.position().y = rand.nextInt(rect.height) + rect.y;
            ((CadaverMonster)monster).staticPosition().x = monster.position().x;
            ((CadaverMonster)monster).staticPosition().y = monster.position().y;
            group.add(monster);
        }
        return group;
    }
    public CircleEntity spawnCadaver(int level,boolean unique){
        CadaverMonster monster = new CadaverMonster();
        monster.level = level;
        monster.baseMaxHealth = CadaverMonster.BASE_MAXHEALTH + level*4;
        monster.maxHealth = monster.baseMaxHealth;
        monster.health = monster.maxHealth;
        monster.baseMaxMana = CadaverMonster.BASE_MAXMANA + level*2;
        monster.baseAttackDamage = CadaverMonster.BASE_ATTACK_DAMAGE + level*2;
        monster.baseMagicDamage = CadaverMonster.BASE_MAGIC_DAMAGE + level*2;
        monster.baseAttackDefense = CadaverMonster.BASE_ATTACK_DEFENSE + level*2;
        monster.baseMagicDefense = CadaverMonster.BASE_MAGIC_DEFENSE + level*2;
        monster.basePrecision = CadaverMonster.BASE_PRECISION + level*1;
        monster.baseAttackSpeed = CadaverMonster.BASE_ATTACKSPEED + level*1;
        monster.baseCastSpeed = CadaverMonster.BASE_CASTSPEED + level*1;
        monster.baseMoveSpeed = CadaverMonster.BASE_MOVESPEED + level*1;
        
        CircleWeapon cW = weaponGenerator.getBiteWeapon(level);
        monster.equippedWeapon(cW);
        //System.out.println("Cadaver level:" + monster.level);
        return monster;
    }
    public ArrayList<CircleEntity> spawnGroupRedImpSpawner(int level, int numberOfMonsters,Rectangle rect){
        ArrayList<CircleEntity> group = new ArrayList<CircleEntity>();
        for(int i = 0; i < numberOfMonsters;i++){
            //add some weaker and stronger monsters around level +- 3
            int monsterLevel = (level > 3) ? rand.nextInt(6) - 3: level;
            CircleEntity monster = spawnRedImpSpawner(level + monsterLevel,(Math.random() < uniqueSpawnChance));
            monster.position().x = rand.nextInt(rect.width) + rect.x;
            monster.position().y = rand.nextInt(rect.height) + rect.y;
            ((RedImpSpawner)monster).staticPosition().x = monster.position().x;
            ((RedImpSpawner)monster).staticPosition().y = monster.position().y;
            group.add(monster);
        }
        return group;
    }
    public CircleEntity spawnRedImpSpawner(int level,boolean unique){
        RedImpSpawner monster = new RedImpSpawner();
        monster.level = level;
        monster.baseMaxHealth(RedImpSpawner.BASE_MAXHEALTH + level*4);
        monster.maxHealth(monster.baseMaxHealth());
        monster.health(monster.maxHealth());
        monster.baseMaxMana = RedImpSpawner.BASE_MAXMANA + level*2;
        monster.baseAttackDamage = RedImpSpawner.BASE_ATTACK_DAMAGE + level*2;
        monster.baseMagicDamage = RedImpSpawner.BASE_MAGIC_DAMAGE + level*2;
        monster.baseAttackDefense = RedImpSpawner.BASE_ATTACK_DEFENSE + level*2;
        monster.baseMagicDefense = RedImpSpawner.BASE_MAGIC_DEFENSE + level*2;
        monster.basePrecision = RedImpSpawner.BASE_PRECISION + level*1;
        monster.baseAttackSpeed = RedImpSpawner.BASE_ATTACKSPEED + level*1;
        monster.baseCastSpeed = RedImpSpawner.BASE_CASTSPEED + level*1;
        monster.baseMoveSpeed = RedImpSpawner.BASE_MOVESPEED + level*1;
        
        CircleWeapon cW = weaponGenerator.getBiteWeapon(level);
        monster.equippedWeapon(cW);
        monster.recalculateBonuses(0);
        monster.health(monster.maxHealth());
       // System.out.println("RedImpSpawner level:" + monster.level);
        return monster;
    }
}
