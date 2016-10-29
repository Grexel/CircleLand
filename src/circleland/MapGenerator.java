/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import circleland.Items.Crate;
import circleland.Items.Portal;
import circleland.Monsters.CadaverMonster;
import circleland.Monsters.FlyMonster;
import circleland.Monsters.HoundMonster;
import circleland.Monsters.InchWormMonster;
import circleland.Monsters.RedImpMonster;
import circleland.Monsters.RedImpSpawner;
import circleland.Monsters.SkeletonRangerMonster;
import circleland.Monsters.SkeletonSwordMonster;
import circleland.Monsters.TurretMonster;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Jeff
 */
public class MapGenerator {
    private static final Random rand = new Random();
    private static final MonsterGenerator monsterGenerator = new MonsterGenerator();
    public static CircleMap generateMap(String mapName, CircleMap caller){
        CircleMap newMap = new CircleMap(caller.camera(),caller.mouse(),caller.player(),rand.nextInt(20)+40,rand.nextInt(20) + 40);
        //break name into name and level
        int mapLevel = 5;
        newMap.name(mapName);
        newMap.level(mapLevel);
        randomEnemies(newMap);
        randomTerrain(newMap,1000,60,40);
        
        Portal p = new Portal(caller.name(), rand.nextInt(newMap.mapBounds().width),rand.nextInt(newMap.mapBounds().height));
        newMap.itemsOnGround().add(p);
        return newMap;
    }
    public static void randomEnemies(CircleMap cM){
        if(cM.name().equalsIgnoreCase("Cave")){
            cM.monsters().addAll(monsterGenerator.spawnGroupInchWorm(cM.level(), 10, cM.mapBounds()));
            cM.monsters().addAll(monsterGenerator.spawnGroupFly(cM.level(), 10, cM.mapBounds()));
            cM.monsters().addAll(monsterGenerator.spawnGroupCadaver(cM.level(), 5, cM.mapBounds()));
        }
        if(cM.name().equalsIgnoreCase("Crypt")){
            cM.monsters().addAll(monsterGenerator.spawnGroupSkeletonSword(cM.level(), 10, cM.mapBounds()));
            cM.monsters().addAll(monsterGenerator.spawnGroupSkeletonRanger(cM.level(), 10, cM.mapBounds()));
        }
        if(cM.name().equalsIgnoreCase("Plains")){
            cM.monsters().addAll(monsterGenerator.spawnGroupHound(cM.level(), 30, cM.mapBounds()));
            cM.monsters().addAll(monsterGenerator.spawnGroupTurret(cM.level(), 10, cM.mapBounds()));
        }
        if(cM.name().equalsIgnoreCase("ImpLand")){
            cM.monsters().addAll(monsterGenerator.spawnGroupRedImp(cM.level(), 50, cM.mapBounds()));
            cM.monsters().addAll(monsterGenerator.spawnGroupRedImpSpawner(cM.level(), 4, cM.mapBounds()));
        }
        if(cM.name().equalsIgnoreCase("ImpLands")){
            cM.monsters().addAll(monsterGenerator.spawnGroupRedImp(cM.level(), 50, cM.mapBounds()));
            cM.monsters().addAll(monsterGenerator.spawnGroupRedImpSpawner(cM.level(), 4, cM.mapBounds()));
        }
        if(cM.name().equalsIgnoreCase("ImpDen")){
            cM.monsters().addAll(monsterGenerator.spawnGroupRedImp(cM.level(), 50, cM.mapBounds()));
            cM.monsters().addAll(monsterGenerator.spawnGroupRedImpSpawner(cM.level(), 4, cM.mapBounds()));
        }
        int newMapName = rand.nextInt(4);
        String newName = "";
        switch(newMapName){
            case 0: newName = "Cave";break;
            case 1: newName = "Crypt";break;
            case 2: newName = "Plains";break;
            case 3: newName = "ImpLand";break;
        }
        
        int xPos = rand.nextInt(cM.mapBounds().width);
        int yPos = rand.nextInt(cM.mapBounds().height);
        cM.itemsOnGround().add(new Portal(newName,xPos,yPos));
        
        for(int i = 0; i < 4; i++){
            xPos = rand.nextInt(cM.mapBounds().width);
            yPos = rand.nextInt(cM.mapBounds().height);
            cM.itemsOnGround.add(new Crate(xPos,yPos));

            //cM.itemsOnGround().add(new Crate(xPos,yPos));
        }
    }
    public static void randomTerrain(CircleMap cM, int numberOfRects,int maxWidth, int maxHeight)
    {
        Color[] colorScheme = new Color[6];
        colorScheme[0] = Color.BLACK;
        colorScheme[1] = Color.BLUE;
        colorScheme[2] = Color.CYAN;
        colorScheme[3] = Color.GRAY;
        colorScheme[4] = Color.MAGENTA;
        colorScheme[5] = Color.DARK_GRAY;
        
        TerrainGenerator tG = new TerrainGenerator(cM.mapBounds(),colorScheme,60,60,20,50,20,50,200,400);
        tG.randomColorScheme();
        tG.generateTerrain();
        cM.mapTerrain().addAll(tG.colorRects());
        //cM.mapCollision().addAll(tG.collidableRects());
    }
}
