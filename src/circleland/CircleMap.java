/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import circleland.Loot.LootGenerator;
import circleland.Display.Camera;
import circleland.Classes.Summons.SummonedBoneWall;
import circleland.Display.ChatBox;
import circleland.Display.DamageDisplayObject;
import circleland.Display.ParticleDisplayObject;
import circleland.Items.Portal;
import circleland.Monsters.*;
import circleland.Weapons.BombWeapon;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.*;


/**
 *
 * @author Jeff
 */
public class CircleMap {
    private static final Font plainFont = new Font("Times New Roman",Font.PLAIN,14);
    protected Random rand;
    protected String name;
    public void name(String s){name = s;}
    public String name(){return name;}
    protected int level;
    public void level(int s){level = s;}
    public int level(){return level;}
    
    //entity holders
    protected CircleClass player;
    public CircleClass player(){return player;}
    //it's more involved.
    public void player(CircleClass pl){
        players.remove(player);
        player = pl;
        players.add(player);
        camera.setFollowing(player);
        for(CircleSummoned sum : player.summoned())
        {
            players.add(sum);
        }
        player.returnSummoned();
    }
    //Entity Holders
    protected ArrayList<CircleEntity> monsters;
    public ArrayList<CircleEntity> monsters(){return monsters;}
    protected ArrayList<CircleEntity> spawningMonsters;
    public ArrayList<CircleEntity> spawningMonsters(){return spawningMonsters;}
    protected ArrayList<CircleEntity> players;
    public ArrayList<CircleEntity> players(){return players;}
    protected ArrayList<CircleEntity> spawningPlayers;
    public ArrayList<CircleEntity> spawningPlayers(){return spawningPlayers;}
    public ArrayList<CircleEntity> corpses;
    public ArrayList<CircleEntity> corpses(){return corpses;}
    protected ArrayList<CircleEntity> spawningCorpses;
    public ArrayList<CircleEntity> spawningCorpses(){return spawningCorpses;}
    
    //Attack Holders
    protected ArrayList<CircleAttack> monsterAttacks;
    public ArrayList<CircleAttack> monsterAttacks(){return monsterAttacks;}
    protected ArrayList<CircleAttack> playerAttacks;
    public ArrayList<CircleAttack> playerAttacks(){return playerAttacks;}
    protected ArrayList<CircleAttack> spawningMonsterAttacks;
    public ArrayList<CircleAttack> spawningMonsterAttacks(){return spawningMonsterAttacks;}
    protected ArrayList<CircleAttack> spawningPlayerAttacks;
    public ArrayList<CircleAttack> spawningPlayerAttacks(){return spawningPlayerAttacks;}
    
    //Misc holders, items and terrain
    //need tilearray for things like pathfinding and collision
    protected SpriteSheet spriteSheet;
    public void spriteSheet(SpriteSheet s){spriteSheet = s;}
    protected int[][][] tileArray;
    public int[][][] tileArray(){return tileArray;}
    public void tileArray(int[][][] b){tileArray = b;}
    protected int tileWidth;
    public int tileWidth(){return tileWidth;}
    public void tileWidth(int b){tileWidth = b;}
    protected int tileHeight;
    public int tileHeight(){return tileHeight;}
    public void tileHeight(int b){tileHeight = b;}
    protected int width;
    public int width(){return width;}
    public void width(int b){width = b;}
    protected int height;
    public int height(){return height;}
    public void height(int b){height = b;}
    
    protected ArrayList<ColorRectangle> mapTerrain;
    public ArrayList<ColorRectangle> mapTerrain(){return mapTerrain;}
    public ArrayList<RectangleObject> mapCollision;
    public ArrayList<RectangleObject> mapCollision(){return mapCollision;}
    protected ArrayList<CircleItem> itemsOnGround;
    public ArrayList<CircleItem> itemsOnGround(){return itemsOnGround;}
    protected ArrayList<CircleItem> spawningItems;
    public ArrayList<CircleItem> spawningItems(){return spawningItems;}
    protected ArrayList<RectangleItem> rectangleItems;
    public ArrayList<RectangleItem> rectangleItems(){return rectangleItems;}
    protected ArrayList<DamageDisplayObject> damageDisplay;
    public ArrayList<DamageDisplayObject> damageDisplay(){return damageDisplay;}
    protected ArrayList<ParticleDisplayObject> particleDisplay;
    public ArrayList<ParticleDisplayObject> particleDisplay(){return particleDisplay;}
    protected ArrayList<ChatBox> chatBoxes;
    public ArrayList<ChatBox> chatBoxes(){return chatBoxes;}
    
    
    //map details
    protected CircleMouse mouse;
    public CircleMouse mouse(){return mouse;}
    public void mouse(CircleMouse m){mouse = m;}
    protected Camera camera;
    public Camera camera(){return camera;} 
    public void camera(Camera c){camera = c;camera.mapRect(mapBounds);}
    protected Rectangle mapBounds;
    public Rectangle mapBounds(){return mapBounds;}
    protected Portal switchToMap;
    public Portal switchToMap(){return switchToMap;}
    public void switchToMap(Portal p){switchToMap = p;}
    
    protected LootGenerator lootGenerator;
    public LootGenerator lootGenerator(){return lootGenerator;}
    public void lootGenerator(LootGenerator p){lootGenerator = p;}
    
    public CircleMap(int w, int h)
    {
        name = "";
        level = 1;
        width = w;
        height = h;
        rand = new Random();
        monsters = new ArrayList<>();
        players = new ArrayList<>();
        corpses = new ArrayList<>();
        monsterAttacks = new ArrayList<>();
        playerAttacks = new ArrayList<>();
        mapTerrain = new ArrayList<>();
        itemsOnGround = new ArrayList<>();
        spawningMonsters = new ArrayList<>();
        spawningPlayers = new ArrayList<>();
        spawningCorpses = new ArrayList<>();
        spawningMonsterAttacks = new ArrayList<>();
        spawningPlayerAttacks = new ArrayList<>();
        rectangleItems = new ArrayList<>();
        spawningItems = new ArrayList<>();
        damageDisplay = new ArrayList<>();
        particleDisplay = new ArrayList<>();
        mapCollision = new ArrayList<>();
        chatBoxes = new ArrayList<>();
        tileArray = new int[w][h][5];
        for(int k = 0; k < 5; k++)
        for(int i = 0; i < tileArray.length;i++){
            for(int j = 0; j < tileArray[0].length; j++){
                tileArray[i][j][k] = 0;
            }
        }
        
        player = new CircleClass("Warrior");
        camera = new Camera(0,0,w,h, player);
        mouse = new CircleMouse(camera,player);
        mapBounds = new Rectangle(0,0,2000,2000);
        camera.mapRect(mapBounds);
        camera.setFollowing(player);
        players.add(player);
        lootGenerator = new LootGenerator();
//        randomizeTerrain(1000,mapBounds,60,40);
//        randomEnemies();
    }
    public CircleMap(Camera c, CircleMouse mouse, CircleClass pl,int sizeX, int sizeY)
    {
        name = "";
        level = 1;
        width = sizeX * 32;
        height = sizeY * 32;
        rand = new Random();
        monsters = new ArrayList<>();
        players = new ArrayList<>();
        corpses = new ArrayList<>();
        monsterAttacks = new ArrayList<>();
        playerAttacks = new ArrayList<>();
        mapTerrain = new ArrayList<>();
        itemsOnGround = new ArrayList<>();
        rectangleItems = new ArrayList<>();
        spawningMonsters = new ArrayList<>();
        spawningPlayers = new ArrayList<>();
        spawningCorpses = new ArrayList<>();
        spawningMonsterAttacks = new ArrayList<>();
        spawningPlayerAttacks = new ArrayList<>();
        spawningItems = new ArrayList<>();
        damageDisplay = new ArrayList<>();
        particleDisplay = new ArrayList<>();
        mapCollision = new ArrayList<>();
        chatBoxes = new ArrayList<>();
        tileArray = new int[sizeX][sizeY][5];
        for(int k = 0; k < 5; k++)
        for(int i = 0; i < tileArray[0].length;i++){
            for(int j = 0; j < tileArray[0][0].length; j++){
                tileArray[k][i][j] = 0;
            }
        }
        
        
        this.mouse = mouse;
        camera = c;
        player = pl;
        mapBounds = new Rectangle(0,0,sizeX*32,sizeY*32);
        camera.mapRect(mapBounds);
        camera.setFollowing(player);
        players.add(player);
        lootGenerator = new LootGenerator();
    }
    public void update(long deltaTime){
        camera.update(deltaTime, this);
        mouse.update(deltaTime, this);
        if(mouse.mouseLeftClicked())
        {
            chatBoxes.clear();
        }
        player.focusedEntity(null);
        //camera.update(deltaTime);
        //System.out.println("Camera: " + camera.rect().x + "," + camera.rect().y);
        //update players
        for (Iterator<CircleEntity> iterator = players.iterator(); iterator.hasNext();) {
            CircleEntity circle = iterator.next();
            circle.update(deltaTime, this);
            //System.out.println(circle.name());
            if(circle.collideMouse(mouse))
            {
                player.focusedEntity(circle);
                if(mouse.mouseLeftClicked() && circle instanceof CircleNPC){
                    ((CircleNPC)circle).onClick(player,this);
                    mouse.mouseLeftClicked(false);
                }
            }
            boundEntityToMap(circle);
            if (circle.health() <= 0) {
            // Remove the current element from the iterator and the list.
                iterator.remove();
                    corpses.add(circle);
            }
        }
        //update monsters
        for (Iterator<CircleEntity> iterator = monsters.iterator(); iterator.hasNext();) {
            CircleEntity circle = iterator.next();
            circle.update(deltaTime, this);
            if(circle.collideMouse(mouse))
            {
                player.focusedEntity(circle);
            }
            boundEntityToMap(circle);
            if (circle.health() <= 0) {
            // Remove the current element from the iterator and the list.
                //add items to ground
                //use lootGenerator
                CircleItem cI = lootGenerator.generateLoot(circle);
                if(cI != null){
                    cI.setPosition(circle.position().x, circle.position().y);
                    itemsOnGround.add(cI);
                }
                iterator.remove();
                    corpses.add(circle);
            }
        }
        //update monster Attacks
        for (Iterator<CircleAttack> iterator = monsterAttacks.iterator(); iterator.hasNext();) {
            CircleAttack attack = iterator.next();
            attack.update(deltaTime, this);
            if (attack.life() <= 0 || attack.piercingLeft() <= 0) {
            // Remove the current element from the iterator and the list.
                iterator.remove();
            }
        }
        //update player attacks
        for (Iterator<CircleAttack> iterator = playerAttacks.iterator(); iterator.hasNext();) {
            CircleAttack attack = iterator.next();
            attack.update(deltaTime, this);
            if (attack.life() <= 0 || attack.piercingLeft() <= 0) {
            // Remove the current element from the iterator and the list.
                iterator.remove();
            }
        }
        //update player attacks
        for (Iterator<DamageDisplayObject> iterator = damageDisplay.iterator(); iterator.hasNext();) {
            DamageDisplayObject damageDisp = iterator.next();
            damageDisp.update(deltaTime, this);
            if (damageDisp.life() <= 0) {
            // Remove the current element from the iterator and the list.
                iterator.remove();
            }
        }
        //update player attacks
        for (Iterator<ParticleDisplayObject> iterator = particleDisplay.iterator(); iterator.hasNext();) {
            ParticleDisplayObject particleDisp = iterator.next();
            particleDisp.update(deltaTime, this);
            if (particleDisp.life() <= 0) {
            // Remove the current element from the iterator and the list.
                iterator.remove();
            }
        }
        for(CircleItem item: itemsOnGround){
            item.update(deltaTime,this);
            if(item.collideMouse(mouse))
            {
                mouse.hoveringDetails(item);
            }
        }
        //Do mouse update
        //if mouse pressed
        
        //spawn new entities and attacks
        for(CircleEntity circle : spawningPlayers){
            players.add(circle);
        }
        spawningPlayers.clear();
        for(CircleEntity circle : spawningMonsters){
            monsters.add(circle);
        }
        spawningMonsters.clear();
        for(CircleAttack circle : spawningPlayerAttacks){
            playerAttacks.add(circle);
        }
        spawningPlayerAttacks.clear();
        for(CircleAttack circle : spawningMonsterAttacks){
            monsterAttacks.add(circle);
        }
        spawningMonsterAttacks.clear();
        for(CircleItem item : spawningItems){
            itemsOnGround.add(item);
        }
        spawningItems.clear();
        camera.update(deltaTime, this);
    }
    public void boundEntityToMap(CircleEntity circle){
        if(circle.position().x < mapBounds.x)
            circle.position().x = mapBounds.x;
        if(circle.position().y < mapBounds.y)
            circle.position().y = mapBounds.y;
        if(circle.position().x > mapBounds.x + mapBounds.width)
            circle.position().x = mapBounds.x + mapBounds.width;
        if(circle.position().y > mapBounds.y + mapBounds.height)
            circle.position().y = mapBounds.y + mapBounds.height;
    }
    
    public void removePlayer(CircleClass cE){
        for(CircleSummoned cS : cE.summoned()){
            players.remove(cS);
        }
        players.remove(cE);
        player = null;
    }
    public void addPlayer(CircleEntity cE){
        players.add(cE);
    }
    public void enterNewMap(Portal p){
        
        switchToMap = p;
        //save map here;
    }
    public void draw(Graphics2D graphics)
    {
        graphics.translate(-camera.rect().x,- camera.rect().y);
        // draw bottom of map
         for(int z = 0; z <=2; z++){ //draw bottom two layers
              for(int x = 0; x < tileArray.length; x++){
                  for(int y = 0; y < tileArray[0].length; y++){
                      Rectangle r = new Rectangle(x*tileWidth,y*tileHeight,tileWidth,tileHeight);
                      if(camera.intersects(r))
                          spriteSheet.draw(graphics, x * tileWidth,y * tileHeight, tileArray[x][y][z]);
                  }
              }
         }
        for(ColorRectangle cR : mapTerrain){ if(camera.intersects(cR)) cR.draw(graphics);}
        for(CircleEntity cE : corpses){ if(camera.intersects(cE)) cE.draw(graphics);}
        for(CircleItem cI : itemsOnGround){ if(camera.intersects(cI)) cI.draw(graphics);}
        for(RectangleItem rI : rectangleItems){ rI.draw(graphics);}
        for(CircleEntity cE : monsters){ if(camera.intersects(cE)) cE.draw(graphics);}
        for(CircleEntity cE : players){ if(camera.intersects(cE)) cE.draw(graphics);}
        // draw top of map
         for(int z = 3; z < tileArray[0][0].length; z++){ //draw bottom two layers
              for(int x = 0; x < tileArray.length; x++){
                  for(int y = 0; y < tileArray[0].length; y++){
                      Rectangle r = new Rectangle(x*tileWidth,y*tileHeight,tileWidth,tileHeight);
                      if(camera.intersects(r))
                          spriteSheet.draw(graphics, x * tileWidth,y * tileHeight, tileArray[x][y][z]);
                  }
              }
         }
        for(CircleAttack cE : monsterAttacks){ if(camera.intersects(cE)) cE.draw(graphics);}
        for(CircleAttack cE : playerAttacks){ if(camera.intersects(cE)) cE.draw(graphics);}
        for(DamageDisplayObject dD : damageDisplay){ dD.draw(graphics);}
        for(ParticleDisplayObject pD : particleDisplay){ pD.draw(graphics);}
        for(ChatBox cB : chatBoxes){ cB.draw(graphics);}
        graphics.setFont(plainFont);
        graphics.translate(camera.rect().x, camera.rect().y);
        
        camera.draw(graphics);
        mouse.draw(graphics);
        //draw player focused entity
        if(player.focusedEntity() != null)
        {
            //draw health bar on top, with name
            graphics.setColor(Color.BLACK);
            graphics.fillRect((int)camera.rect.width/2 - 50, 10, 100, 20);
            graphics.setColor(Color.RED);
            
            double healthRatio = player.focusedEntity().health() / player.focusedEntity().maxHealth();
            graphics.fillRect((int)camera.rect.width/2 - 50, 10,
                    (int)(100.0 * healthRatio), 20);
            graphics.setColor(Color.WHITE);
            graphics.drawString(player.focusedEntity().name(),(int)camera.rect.width/2 - 50, 20);
            
        }
        //showHealth and mp
        drawHealth(graphics,250,500);
        drawEquipped(graphics,270,575);
        //show item stats
        if(mouse.hovering() != null){
            mouse.hovering().drawDetails(graphics, (int)mouse.position().x, (int)mouse.position().y);
        }
        
    }
    public void drawEquipped(Graphics2D graphics, int x, int y){
        graphics.setColor(Color.BLACK);
        graphics.fillRect(x, y, 50, 25);
        if(player.equippedWeapon != null)
            player.equippedWeapon.draw(graphics, x+12, y+12);
        if(player.equippedSkill != null)
            player.equippedSkill.draw(graphics, x+37, y+12);
    }
    public void drawHealth(Graphics2D graphics,int x, int y){
        graphics.setColor(Color.BLACK);
        graphics.fillRect(x, y, 20, 100);
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(x+2, y+2, 16, 96);
        graphics.setColor(Color.RED);
        int healthHeight = (int)(96  - (96 * player.health()/player.maxHealth()));
        graphics.fillRect(x+2,y+2+healthHeight,8,96-healthHeight);
        graphics.setColor(Color.BLUE);
        int manaHeight = (int)(96  - (96 * player.mana()/player.maxMana()));
        graphics.fillRect(x+10,y+2+manaHeight,8,96-manaHeight);
    }
}
