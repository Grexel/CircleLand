/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import circleland.Items.Portal;
import java.util.ArrayList;

/**
 *
 * @author Jeff
 */
public class MapManager {
    private static final String mapFolder = "maps/";
    private static final MapLoader mapLoader = new MapLoader();
    //set up a "map" of circleMaps
    //when a map requests to change to another, load new map.
    //loading should remember who called the load, and add a return portal to the new map
    //each map has a name. should this be unique? probably yes. 
    private static ArrayList<CircleMap> maps = new ArrayList<CircleMap>();
    //need to hold information for : Mapname, level, portals to, portals from.
    
    public static void saveMap(CircleMap cM){
        for(CircleMap cm : maps){
            System.out.println("Maps: " + cm.name());
            if(maps.contains(cM))
                return;
        }
        maps.add(cM);
    }
    public static CircleMap loadMap(String mapName){
        //New map needs to have portal to current map.
        //if portal newmap name equals current map, setplayer at portal
        return mapLoader.loadMap(mapFolder + mapName + ".xml");
        
    }
    public static CircleMap switchMap(CircleMap cM){
        
        //we have the portal the player has chosen to enter.
        //create a new map
        //try to load map from xml file first
        
        Portal p = cM.switchToMap();
        //if map already exists, send them to that
        for(CircleMap map : maps){
            if(map.name().equalsIgnoreCase(p.name()))
            {
                //reset camera, player, mouse mapbounds etc.
                cM.camera().mapRect(map.mapBounds());
                map.player(cM.player());
                cM.removePlayer(cM.player());
                cM.switchToMap(null);
                map.mouse(cM.mouse());
                map.camera(cM.camera());
                for(CircleItem cI : map.itemsOnGround()){
                    if(cI instanceof Portal){
                        System.out.println("Portal name: " + cI.name() + " from name: " + cM.name());
                        if(((Portal)cI).name().equalsIgnoreCase(cM.name()))
                        {
                            map.player().position().x = cI.position().x;
                            map.player().position().y = cI.position().y;
                        }
                    }
                }
                map.player().returnSummoned();
                return map;
            }
        }
        //else try loading the map from xml file
        CircleMap loadedMap = loadMap(p.name());
        if(loadedMap != null){
            //loading was a success, place character on map and return
            loadedMap.player(cM.player());
            loadedMap.player().position().x = 0;
            for(CircleItem cI : loadedMap.itemsOnGround()){
                if(cI instanceof Portal){
                    if(((Portal)cI).name().equalsIgnoreCase(cM.name()))
                    {
                        loadedMap.player().position().x = cI.position().x;
                        loadedMap.player().position().y = cI.position().y;
                    }
                }
            }
            cM.switchToMap(null);
            loadedMap.mouse(cM.mouse());
            loadedMap.camera(cM.camera());
            loadedMap.name(p.name());
            MapGenerator.randomEnemies(loadedMap);
            loadedMap.player().returnSummoned();
            return loadedMap;
        }
        //else generate random map
        
        String newMapName = p.name();
        CircleMap newMap = MapGenerator.generateMap(newMapName, cM);
        //currentMap.itemsOnGround().add(new Portal("ImpLand:1","ImpLand:1",200,200,400,400));
        //String newMapDetails = newMap.name() + ":" + newMap.level();
        newMap.player(cM.player());
        cM.removePlayer(cM.player());
        for(CircleItem cI : newMap.itemsOnGround()){
            if(cI instanceof Portal){
                if(((Portal)cI).name().equalsIgnoreCase(cM.name()))
                {
                    newMap.player().position().x = cI.position().x;
                    newMap.player().position().y = cI.position().y;
                }
            }
        }
        cM.switchToMap(null);
        newMap.player().returnSummoned();
        return newMap;
    }
}
